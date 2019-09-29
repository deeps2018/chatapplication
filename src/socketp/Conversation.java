package socketp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.StringTokenizer;
import static socketp.Server.pwempty;

class Conversation extends Thread {

    Socket soc;
    BufferedReader nis;
    PrintWriter nos;

    Conversation(Socket soc) {
        this.soc = soc;

    }

    @Override
    public void run() {

        try {
            nis = new BufferedReader(
                    new InputStreamReader(
                            soc.getInputStream()
                    )
            );
            nos = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    soc.getOutputStream()
                            )
                    ), true
            );
            Server.al.add(nos);
            BufferedReader brLogs = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(Server.filePath)
                    )
            );
            String myLine = brLogs.readLine();

            while (myLine != "\n" && myLine != null) {
                nos.println(myLine);
                myLine = brLogs.readLine();
            }

            String msg = nis.readLine();

            while (!msg.contains("end")) {
                Server.pwempty.println(msg);

                System.out.println(Thread.currentThread().getName() + " -> " + msg);

                Server.mq.enqueue(msg.replaceAll(":chat", "").replaceAll(":Chat", ""));

                msg = nis.readLine();

            }

            Server.al.remove(this.nos);

        } catch (IOException ex) {
            Logger.getLogger(Conversation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
