package echo;

import java.awt.Dimension;
import java.awt.Image;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class remotetask extends javax.swing.JFrame {

    DataInputStream dis;
    DataOutputStream dos;
    String servernn;
    String ipss;
    Socket socket;

    public remotetask(String name, String ip) {
        initComponents();
        this.setVisible(true);
        this.setSize(700, 500);

        servernn = name;
        ipss = ip;
        GetScreen obj = new GetScreen(ip);
        Thread th = new Thread(obj);
        th.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tasklabel = new javax.swing.JLabel();
        bt_shutdown = new javax.swing.JButton();
        bt_reset = new javax.swing.JButton();
        bt_handelsystem = new javax.swing.JButton();
        bt_sendmessage = new javax.swing.JButton();
        btexplorer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        tasklabel.setText("jLabel1");
        tasklabel.setMaximumSize(new java.awt.Dimension(700, 700));
        tasklabel.setMinimumSize(new java.awt.Dimension(500, 500));
        jScrollPane1.setViewportView(tasklabel);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 0, 540, 280);

        bt_shutdown.setText("shut down");
        bt_shutdown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_shutdownActionPerformed(evt);
            }
        });
        getContentPane().add(bt_shutdown);
        bt_shutdown.setBounds(0, 290, 150, 80);

        bt_reset.setText("reset");
        bt_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_resetActionPerformed(evt);
            }
        });
        getContentPane().add(bt_reset);
        bt_reset.setBounds(380, 290, 160, 80);

        bt_handelsystem.setText("Handel System");
        bt_handelsystem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_handelsystemActionPerformed(evt);
            }
        });
        getContentPane().add(bt_handelsystem);
        bt_handelsystem.setBounds(170, 290, 200, 80);

        bt_sendmessage.setText("Send Message");
        bt_sendmessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_sendmessageActionPerformed(evt);
            }
        });
        getContentPane().add(bt_sendmessage);
        bt_sendmessage.setBounds(10, 383, 260, 40);

        btexplorer.setText("Explorer");
        btexplorer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btexplorerActionPerformed(evt);
            }
        });
        getContentPane().add(btexplorer);
        btexplorer.setBounds(300, 383, 230, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_shutdownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_shutdownActionPerformed
        try {
            int i = JOptionPane.showConfirmDialog(rootPane, "are you sure you want to shutdown");
            if (i == JOptionPane.YES_OPTION) {
                dos.writeBytes("shutdown\r\n");
                JOptionPane.showMessageDialog(rootPane, "your pc will restart in 0 seconds");

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_bt_shutdownActionPerformed

    private void bt_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_resetActionPerformed
        try {
            int i = JOptionPane.showConfirmDialog(rootPane, "are you sure you want to reset");
            if (i == JOptionPane.YES_OPTION) {
                dos.writeBytes("reset\r\n");
                JOptionPane.showMessageDialog(rootPane, "your pc will restart in 0 seconds");

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_bt_resetActionPerformed

    private void bt_handelsystemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_handelsystemActionPerformed

        clientscreen cls = new clientscreen(servernn, ipss);
        dispose();
    }//GEN-LAST:event_bt_handelsystemActionPerformed

    private void bt_sendmessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_sendmessageActionPerformed
        try {
            String showInputDialog = JOptionPane.showInputDialog(rootPane, "write your message");
            if (showInputDialog != null);
            {
                dos.writeBytes("messagefromclient\r\n");
                dos.writeBytes(showInputDialog + "\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_bt_sendmessageActionPerformed

    private void btexplorerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btexplorerActionPerformed
      
            explorer obj=new explorer(dis,dos);
            obj.setVisible(true);
            obj.setAlwaysOnTop(true);
      
    }//GEN-LAST:event_btexplorerActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    class GetScreen implements Runnable {

        String ip;

        GetScreen(String ipp) {
            ip = ipp;
        }

        @Override
        public void run() {

            try {
                socket = new Socket(ip, 4200);
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                dos.writeBytes("senddimensions\r\n");
                dos.flush();
                int h = dis.readInt();
                int w = dis.readInt();
                tasklabel.setPreferredSize(new Dimension(w, h));
//                System.out.println("mytest " + lbscreen.getWidth() + "  " + lbscreen.getHeight());

                photoclient ph = new photoclient(ip);
                Thread t = new Thread(ph);
                t.start();
            } catch (IOException ex) {

            }

        }

    }

    class photoclient implements Runnable {

        String ip;

        public photoclient(String ip) {
            this.ip = ip;
        }

        @Override
        public void run() {
            DataInputStream dis;
            DataOutputStream dos;
            try {
                Socket sock = new Socket(ipss, 4200);
                dis = new DataInputStream(sock.getInputStream());
                dos = new DataOutputStream(sock.getOutputStream());
                dos.writeBytes("sendscreen\r\n");
                dos.flush();
                String path = System.getProperty("user.home");
                File f = new File(path + "\\pcscreen");
                if (!f.exists()) {
                    f.mkdir();
                }

                while (true) {
                    String msg = dis.readLine();
                    // System.out.println(msg);
                    if (msg == null) {
                        break;
                    }
                    if (msg.equals("receiveimage")) {
                        File screen = new File(f, "2.jpg");
                        FileOutputStream fos = new FileOutputStream(screen);
                        long filesize = dis.readLong();
                        byte b[] = new byte[10000];

                        long count = 0;
                        while (true) {
                            int r = dis.read(b, 0, b.length);
                            count = count + r;
                            fos.write(b, 0, r);
                            if (count == filesize) {
                                break;
                            }
                        }
                        try {
                            Image image = ImageIO.read(screen).getScaledInstance(tasklabel.getWidth(), tasklabel.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon imageicon = new ImageIcon(image);
                            tasklabel.setIcon(imageicon);
                            tasklabel.requestFocus();
                        } catch (Exception e) {
                        }
                        fos.flush();
                        fos.close();
                        dos.writeBytes("sendnext\r\n");
                        dos.flush();
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_handelsystem;
    private javax.swing.JButton bt_reset;
    private javax.swing.JButton bt_sendmessage;
    private javax.swing.JButton bt_shutdown;
    private javax.swing.JButton btexplorer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel tasklabel;
    // End of variables declaration//GEN-END:variables
}
