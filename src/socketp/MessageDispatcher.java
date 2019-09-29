package socketp;

import java.io.PrintWriter;

public class MessageDispatcher extends Thread {

    @Override

    public void run() {
        while (true) {
            try {
                String str = (String) Server.mq.dequeue();

                for (PrintWriter o : Server.al) {
                    o.println(str);
                }
            } catch (Exception e) {
            }
        }
    }

}
