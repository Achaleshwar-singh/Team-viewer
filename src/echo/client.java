package echo;

import java.net.*;
import java.io.*;

public class client {

    Socket sock;
    BufferedReader brn;
    //PrintWriter pw;
   // BufferedReader br;
//InputStreamReader isr;
    DataInputStream dis;
    DataOutputStream dos;

    public client() {
        try {
            sock = new Socket("127.0.0.1", 4200);
            dis = new DataInputStream(sock.getInputStream());
            dos = new DataOutputStream(sock.getOutputStream());
            // isr=new InputStreamReader(sock.getInputStream());

            String msg = dis.readLine();
            System.out.println("message from server "+msg);
            System.out.println("connection established");
        } catch (Exception e) {
        }

    }

    public static void main(String args[]) throws IOException {
client obj =new client();
    }

}
