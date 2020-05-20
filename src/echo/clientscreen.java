package echo;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class clientscreen extends javax.swing.JFrame implements MouseMotionListener, KeyListener {

    DataInputStream dis;
    DataOutputStream dos;
    String servernn;
    String ipss;
    Socket socket;

    public clientscreen(String name, String ip) {
        initComponents();

        servernn = name;
        ipss = ip;
        GetScreen obj = new GetScreen(ip);
        Thread th = new Thread(obj);
        th.start();
        this.setTitle("Connected to " + servernn);
        this.setSize(1220, 575);

        lbscreen.addMouseMotionListener(this);
        lbscreen.addKeyListener(this);

//        Dimension dm=Toolkit.getDefaultToolkit().getScreenSize();
//        this.setSize((int)dm.getWidth(),(int)dm.getHeight());
//      lbscreen.setSize((int)dm.getWidth(),(int)dm.getHeight());
        this.setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        try {
            dos.writeBytes("mousedragged\r\n");
            dos.writeInt(e.getX());
            dos.writeInt(e.getY());
            dos.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        try {
            dos.writeBytes("mousemoved\r\n");
            dos.writeInt(e.getX());
            dos.writeInt(e.getY());
            dos.flush();
        } catch (IOException ex) {

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        try {
            int i = e.getKeyCode();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            dos.writeBytes("keytyped\r\n");
            dos.writeInt(e.getKeyCode());
            dos.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("keyPressed " + e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int i = e.getKeyCode();
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
                lbscreen.setPreferredSize(new Dimension(w, h));
//                System.out.println("mytest " + lbscreen.getWidth() + "  " + lbscreen.getHeight());

                photoclient ph = new photoclient(ip);
                Thread t = new Thread(ph);
                t.start();
            } catch (IOException ex) {

            }

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lbscreen = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lbscreen.setText("jLabel1");
        lbscreen.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lbscreenMouseMoved(evt);
            }
        });
        lbscreen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbscreenMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lbscreenMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(lbscreen);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 0, 1210, 540);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lbscreenMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbscreenMouseMoved
        int i = evt.getButton();
        // TODO add your handling code here:
    }//GEN-LAST:event_lbscreenMouseMoved

    private void lbscreenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbscreenMousePressed
        try {
//                       System.out.println("pressed called");
//            this.mouseMoved(evt);
            dos.writeBytes("mousepressed\r\n");
            dos.writeInt(evt.getX());
            dos.writeInt(evt.getY());
            dos.writeInt(evt.getButton());
            dos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_lbscreenMousePressed

    private void lbscreenMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbscreenMouseReleased
        try {

//            this.mouseMoved(evt);
            dos.writeBytes("mousereleased\r\n");
            dos.writeInt(evt.getX());
            dos.writeInt(evt.getY());
            dos.writeInt(evt.getButton());
            dos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_lbscreenMouseReleased

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(clientscreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(clientscreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(clientscreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(clientscreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new clientscreen("", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbscreen;
    // End of variables declaration//GEN-END:variables

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
                            Image image = ImageIO.read(screen).getScaledInstance(lbscreen.getWidth(), lbscreen.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon imageicon = new ImageIcon(image);
                            lbscreen.setIcon(imageicon);
                            lbscreen.requestFocus();
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

}
