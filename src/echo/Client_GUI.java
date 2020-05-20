package echo;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author VMM
 */
public class Client_GUI extends javax.swing.JFrame {

    String path;
    public ArrayList<server> al = new ArrayList<>();
    my_ip_model model = new my_ip_model();

    public Client_GUI(String email) {
        initComponents();
        jTable1.setModel(model);
        setSize(710, 575);
        setLocationRelativeTo(null);
        setResizable(false);

        setTitle("Welcome " + email);
        new Thread(new myserver()).start();
        new Thread(new slideshow()).start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btscan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        sspics = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(100, 100, 300, 300));
        getContentPane().setLayout(null);

        btscan.setText("SCAN ONLINE SERVER");
        btscan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btscanActionPerformed(evt);
            }
        });
        getContentPane().add(btscan);
        btscan.setBounds(470, 360, 200, 70);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 350, 420, 190);

        jButton1.setText("Get Screen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(470, 460, 200, 70);

        sspics.setText("jLabel2");
        getContentPane().add(sspics);
        sspics.setBounds(0, 0, 700, 300);

        jLabel1.setFont(new java.awt.Font("Georgia", 2, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 51, 255));
        jLabel1.setText("Online Server List");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 304, 680, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btscanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btscanActionPerformed
        new Thread(new Runnable() {

            @Override
            public void run() {
                String Ip = "";
                try {
                    InetAddress localHost = InetAddress.getLocalHost();
                    String IPP = localHost.getHostAddress();
                    System.out.println(IPP);

                    Ip = IPP.substring(0, IPP.lastIndexOf(".") + 1);
                    //System.out.println(Ip);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(Client_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

                int count = 0;

                for (int i = 0; i < 5; i++) {
                    // System.out.println("Slot " + (i + 1) + " is scanning");
                    try {
                        myclient mc1[] = new myclient[51];
                        Thread t3[] = new Thread[51];
                        for (int j = 0; j < mc1.length; j++) {
                            mc1[j] = new myclient(Ip + count);
                            t3[j] = new Thread(mc1[j]);
                            t3[j].start();
                            count++;
                        }

                        for (int j = 0; j < t3.length; j++) {
                            Thread thread = t3[j];
                            thread.join();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                // System.out.println("Scanning completed");
                //System.out.println(al.size());
            }
        }).start();
        btscan.setEnabled(false);
    }//GEN-LAST:event_btscanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(rootPane, "please select a row");

        } else {

            //  System.out.println("success");
            int ii = jTable1.getSelectedRow();
            String Servern = al.get(ii).ServerName;
            String ips = al.get(ii).Ip1;

            // clientscreen obj1 = new clientscreen(Servern, ips);
            remotetask obj2 = new remotetask(Servern, ips);

        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Client_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client_GUI("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btscan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel sspics;
    // End of variables declaration//GEN-END:variables

    class myclient implements Runnable {

        String ip;
        DataInputStream dis;

        DataOutputStream dos;

        myclient(String IP) {
            ip = IP;
        }

        @Override
        public void run() {
            try {
                InetSocketAddress address = new InetSocketAddress(InetAddress.getByName(ip), 4200);
                Socket s = new Socket();
                s.connect(address, 1000);
                System.out.println("connected to server " + ip);

                dis = new DataInputStream(s.getInputStream());
                dos = new DataOutputStream(s.getOutputStream());
                dos.writeBytes("sendname\r\n");
                dos.flush();
                String Servername = dis.readLine();
                if (!Servername.equals("")) {
                    server obj = new server(ip, Servername);
                    al.add(obj);
                    model.fireTableDataChanged();
                }
            } catch (Exception e) {
            }
        }
    }

    class server {

        String Ip1;
        String ServerName;

        server(String ip, String server) {
            Ip1 = ip;

            ServerName = server;
        }
    }

    class my_ip_model extends AbstractTableModel {

        String title[] = {"serial no", "ip", "hostname"};

        @Override
        public int getRowCount() {
            return al.size();
        }

        @Override
        public int getColumnCount() {
            return title.length;
        }

        @Override
        public String getColumnName(int i) {
            return title[i];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (columnIndex == 0) {
                return ++rowIndex;
            } else if (columnIndex == 1) {
                return al.get(rowIndex).Ip1;
            } else {
                return al.get(rowIndex).ServerName;
            }
        }

    }

    class myserver implements Runnable {

        @Override
        public void run() {

            try {
                ServerSocket sersock = new ServerSocket(4200);
                //System.out.println("server started at port 4200");
                Socket sock;
                while (true) {

                    sock = sersock.accept();
                    clienthandler ch = new clienthandler(sock);
                    Thread th = new Thread(ch);
                    th.start();
                }

            } catch (Exception e) {
            }

        }
    }

    class clienthandler implements Runnable {

        Socket socket;
        DataInputStream dis;
        DataOutputStream dos;
        Robot r;

        public clienthandler(Socket sock) {
            this.socket = sock;
        }

        @Override
        public void run() {
            try {
                r = new Robot();
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                String message;
                while (true) {
                    message = dis.readLine();
                    if (message == null) {
                        break;
                    } else if (message.equals("sendname")) {
                        sendname();
                    } else if (message.equals("senddimensions")) {
                        int height, width;
                        Robot r = new Robot();
                        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                        height = (int) (screenSize.getHeight());
                        width = (int) (screenSize.getWidth());
                        dos.writeInt(height);
                        dos.writeInt(width);
                        dos.flush();

                    } else if (message.equals("sendscreen")) {
                        //System.out.println("send screen started");
                        startSendingScreen obj = new startSendingScreen(dis, dos);
                        Thread t1 = new Thread(obj);
                        t1.start();
                        Thread.sleep(100);
                        break;
                    } else if (message.equals("mousemoved")) {
                        int x = dis.readInt();
                        int y = dis.readInt();
                        r.mouseMove(x, y);

                    } else if (message.equals("mousepressed")) {
                        int x = dis.readInt();
                        int y = dis.readInt();
                        int buttontype = dis.readInt();

                        r.mouseMove(x, y);
                        int mask;
                        if (buttontype == 1) {
                            mask = MouseEvent.BUTTON1_MASK;
                        } else if (buttontype == 2) {
                            mask = MouseEvent.BUTTON2_MASK;
                        } else {
                            mask = MouseEvent.BUTTON3_MASK;
                        }
                        r.mousePress(mask);
                    } else if (message.equals("mousereleased")) {
                        int mask;
                        int x = dis.readInt();
                        int y = dis.readInt();
                        int buttontype = dis.readInt();

                        if (buttontype == 1) {
                            mask = MouseEvent.BUTTON1_MASK;
                        } else if (buttontype == 2) {
                            mask = MouseEvent.BUTTON2_MASK;
                        } else {
                            mask = MouseEvent.BUTTON3_MASK;
                        }
                        r.mouseMove(x, y);
                        r.mouseRelease(mask);
                    } else if (message.equals("mousedragged")) {
                        int x = dis.readInt();
                        int y = dis.readInt();

                        r.mouseMove(x, y);

                        // r.mousePress(MouseEvent.BUTTON1_MASK);
                    } else if (message.equals("keytyped")) {
                        int keycode = dis.readInt();
                        r.keyPress(keycode);
                        r.keyRelease(keycode);

                    } else if (message.equals("shutdown")) {
                        try {
                            // System.out.println("stutdown message reached");
                            Runtime runtime = Runtime.getRuntime();
                            Process process = runtime.exec("stutdown -s -t 0");
                            System.exit(0);
                        } catch (Exception e) {
                        }
                    } else if (message.equals("reset")) {
                        try {
                            // System.out.println("stutdown message reached");
                            Runtime runtime = Runtime.getRuntime();
                            Process process = runtime.exec("reset -s -t 0");
                            System.exit(0);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (message.equals("messagefromclient")) {
                        String clientmessage = dis.readLine();
                        JOptionPane.showMessageDialog(rootPane, clientmessage);
                    } else if (message.equals("senddrives")) {
                        try {
                            String s = "";
                            File[] roots = File.listRoots();
                            for (int i = 0; i < roots.length; i++) {
                                s = s + roots[i] + "~~";
                            }
                            dos.writeBytes(s + "\r\n");

                            dos.flush();
                        } catch (Exception e) {
                        }
                    } else if (message.equals("sendfilesorfolders")) {
                        try {
                            String isfileordirectory = dis.readLine();
                            if (isfileordirectory.equals("directory")) {
                                String s = "";
                                String drive = dis.readLine();
                                File folder = new File(drive);
                                File[] roots = folder.listFiles();
                                for (int i = 0; i < roots.length; i++) {
                                    s = s + roots[i] + "~~";
                                }
                                dos.writeBytes(s + "\r\n");
                                dos.flush();
                            } else if (isfileordirectory.equals("file")) {
                                String filepath = dis.readLine();
                                File filenew = new File(filepath);
                                try {
                                    FileInputStream fis = new FileInputStream(filenew);
                                    long filesize = filenew.length();
                                    byte b[] = new byte[10000];
                                    dos.writeLong(filesize);
                                    long count = 0;
                                    
                                    while (true) {
                                        int r = fis.read(b, 0, b.length);
                                        dos.write(b, 0, r);
                                        count += r;
                                        if (count == filesize) {
                                            break;
                                        }
                                    }
                       

                                    fis.close();
                                } catch (Exception e) {
                                }
                            }

                        } catch (Exception e) {
                        }
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void sendname() {
            try {
                InetAddress localHost = InetAddress.getLocalHost();
                String ServerName = localHost.getHostName();

                dos.writeBytes(ServerName + "\r\n");
                dos.flush();
            } catch (Exception e) {
            }
        }

        class startSendingScreen implements Runnable {

            DataInputStream dis;
            DataOutputStream dos;

            public startSendingScreen(DataInputStream dis, DataOutputStream dos) {
                this.dis = dis;
                this.dos = dos;
            }

            @Override
            public void run() {
                try {
                    String path = System.getProperty("user.home");
                    //System.out.println(path);
                    File f = new File(path + "\\pcscreen");
                    if (!f.exists()) {
                        f.mkdir();
                    }
                    File screen = new File(f, "1.jpg");
                    Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
                    Rectangle rc = new Rectangle(dm);
                    Robot psrc = new Robot();

                    while (true) {
                        dos.writeBytes("receiveimage\r\n");
                        BufferedImage bm = psrc.createScreenCapture(rc);
                        ImageIO.write(bm, "jpg", screen);
                        FileInputStream fis = new FileInputStream(screen);
                        long filesize = screen.length();
                        byte b[] = new byte[10000];
                        dos.writeLong(filesize);
                        long count = 0;
                        //System.out.println("Sending");

                        while (true) {
                            int r = fis.read(b, 0, b.length);
                            dos.write(b, 0, r);
                            count += r;
                            if (count == filesize) {
                                break;
                            }
                        }
                        //System.out.println("Sended");

                        fis.close();

                        String waitingmsg = dis.readLine();
//                        System.out.println(waitingmsg);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
    int i = 1;

    class slideshow implements Runnable {

        @Override
        public void run() {
            try {
                String path = System.getProperty("user.home");
                File folder = new File(path, "images");

                int i = 1;
                while (true) {
                    try {
                        if (i == 3) {
                            i = 1;
                        }
                        File f = new File(folder, i + ".jpg");
                        Image bi = ImageIO.read(f).getScaledInstance(sspics.getWidth(), sspics.getHeight(), Image.SCALE_SMOOTH);
                        ImageIcon icon = new ImageIcon(bi);
                        sspics.setIcon(icon);
                        i++;
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
