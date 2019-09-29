package socketp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    public static ArrayList<PrintWriter> al = new ArrayList();
    public static MessageQueue mq = new MessageQueue();
    public static String filePath = "C:\\Users\\deeps\\Desktop\\MyLogs.txt";
    public static BufferedReader brLogs;
    public static PrintWriter pwempty;

    static {
        try {
            pwempty = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter(filePath)
                    ), true
            );
        } catch (Exception e) {
        }

    }

    public static void main(String[] args) throws Exception {
        System.out.println("Server Signing ON");
        MessageDispatcher md = new MessageDispatcher();

        ServerSocket ss = new ServerSocket(9081);
        md.start();
        for (int i = 0; i < 10; i++) {
            Socket soc = ss.accept();
            Conversation c = new Conversation(soc);
            c.start();

        }
        System.out.println("Server signing OFF");
        System.exit(0);
    }
}
