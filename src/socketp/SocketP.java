package socketp;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import java.net.Socket;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class SocketP {

    public static void main(String[] args) throws Exception {
        Socket soc = new Socket("10.219.157.197", 9081);
        PrintWriter nos = new PrintWriter(
                new BufferedWriter(
                        new OutputStreamWriter(
                                soc.getOutputStream()
                        )
                ), true
        );

        BufferedReader nis = new BufferedReader(
                new InputStreamReader(
                        soc.getInputStream()
                )
        );
        System.out.println("Client connection established");
        JFrame f1 = new JFrame("Client");
        JButton b1 = new JButton("Send");
        f1.setSize(400, 400);
        f1.setLocation(600, 100);
        JTextArea ta = new JTextArea(10, 10);
        f1.add(ta);
        ta.setEditable(false);
        JTextField tf = new JTextField(20);
        JTextField user = new JTextField(30);
        f1.add(user, BorderLayout.NORTH);
        JPanel p1 = new JPanel();
        p1.add(tf);
        p1.add(b1);
        f1.add(p1, BorderLayout.SOUTH);
        f1.setVisible(true);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        L1 l1 = new L1(ta, tf, nos);
        User u = new User(b1, tf, l1, user);
        user.addActionListener(u);

        String str = nis.readLine();
        while (true) {
            System.out.println(str);
            ta.append(str + "\n");
            str = nis.readLine();

        }

    }

}

class L1 implements ActionListener {

    JTextArea ta;
    JTextField tf;
    PrintWriter nos;

    public L1(JTextArea ta, JTextField tf, PrintWriter nos) {
        this.ta = ta;
        this.tf = tf;
        this.nos = nos;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Button Clicked");
        String str = tf.getText();

        Date now = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss' '", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String d = dateFormat.format(now);
        nos.println(User.uname + " > " + d + ":" + str);
        tf.setText("");
        if (str.equalsIgnoreCase("end")) {
            System.exit(0);
        }
    }

}

class User implements ActionListener {

    JButton b1;
    JTextField tf;
    L1 l1;
    JTextField user;
    static String uname = "";

    public User(JTextField user) {
        this.user = user;
    }

    public User(JButton b1, JTextField tf, L1 l1, JTextField user) {
        this.b1 = b1;
        this.tf = tf;
        this.l1 = l1;
        this.user = user;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (uname.equals("")) {
            uname = user.getText();
            b1.addActionListener(l1);
            tf.addActionListener(l1);
            user.setEditable(false);
        }
    }
}
