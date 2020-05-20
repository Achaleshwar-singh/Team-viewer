package echo;

import java.awt.Image;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class explorer extends javax.swing.JFrame {

    String s;
    DataInputStream dis;
    public ArrayList<String> al = new ArrayList<>();
    DataOutputStream dos;
    MyTableModel tm = new MyTableModel();
    String path = "";

    public explorer(DataInputStream DIS, DataOutputStream DOS) {
        initComponents();
  
        dis = DIS;
        dos = DOS;
        jtfiles.setModel(tm);
        path = "senddrives";
        filechooserlogic obj = new filechooserlogic();
        Thread th = new Thread(obj);
        th.start();
        setSize(600, 500);
        setVisible(true);

    }

    class filechooserlogic implements Runnable {

        @Override
        public void run() {
            try {
                if (path.equals("senddrives")) {
                    al.clear();
                    dos.writeBytes(path + "\r\n");

                    String s = dis.readLine();
                    String ar[] = s.split("~~");
                    for (int i = 0; i < ar.length; i++) {
                        al.add(ar[i]);
                    }
                    tm.fireTableDataChanged();
                } else if (path.equals("sendfilesorfolders")) {

                    dos.writeBytes(path + "\r\n");
                    dos.flush();
                    int index = jtfiles.getSelectedRow();
                    String drive = al.get(index);
                    File f = new File(drive);
                    if (f.isDirectory()) {
                        dos.writeBytes("directory\r\n");
                        dos.writeBytes(drive + "\r\n");

                        String s = dis.readLine();
                        String ar[] = s.split("~~");
                        al.clear();
                        for (int i = 0; i < ar.length; i++) {
                            al.add(ar[i]);
                        }
                        tm.fireTableDataChanged();
                    } else if (f.isFile()) {
                        int i = JOptionPane.showConfirmDialog(rootPane, "click yes to  download and cancel otherwise");
                        if (JOptionPane.YES_OPTION == i) {
                            dos.writeBytes("file\r\n");
                            dos.writeBytes(drive + "\r\n");
                            dos.flush();
                            String path = System.getProperty("user.home");
                            File fnew = new File(path + "\\pcscreen\\downloadedfiles");
                            if (!fnew.exists()) {
                                fnew.mkdir();
                            }
                            String extension = getextension(f);
                            File screen = new File(fnew, extension);
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

                            fos.flush();
                            fos.close();
                            JOptionPane.showMessageDialog(rootPane, "file downloaded successfully");

                        }

                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private String getextension(File f) {
            String path = f.getPath();
            int indexoflastdot = path.lastIndexOf(".");
            String extension = path.substring(indexoflastdot, path.length());
            System.out.println("extension  " + extension);
            return extension;
        }

    }

    class MyTableModel extends AbstractTableModel {

        String title[] = {"serial no", "Drive Name"};

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
                return al.get(rowIndex);
            } else {
                return null;
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtfiles = new javax.swing.JTable();
        btgetfilesfolders = new javax.swing.JButton();
        btreset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jtfiles.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtfiles);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 10, 490, 220);

        btgetfilesfolders.setText("Get FileFolders/Download");
        btgetfilesfolders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btgetfilesfoldersActionPerformed(evt);
            }
        });
        getContentPane().add(btgetfilesfolders);
        btgetfilesfolders.setBounds(20, 320, 190, 50);

        btreset.setText("See Drives");
        btreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btresetActionPerformed(evt);
            }
        });
        getContentPane().add(btreset);
        btreset.setBounds(300, 323, 170, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btgetfilesfoldersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btgetfilesfoldersActionPerformed
        try {
            int i = jtfiles.getSelectedRow();

            if (i != -1) {
            
                    path = "sendfilesorfolders";
                    filechooserlogic obj = new filechooserlogic();
                    Thread th = new Thread(obj);
                    th.start();
                

            } else {
                JOptionPane.showMessageDialog(rootPane, "select drive first");
            }

        } catch (Exception e) {
        }


    }//GEN-LAST:event_btgetfilesfoldersActionPerformed

    private void btresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btresetActionPerformed
        try {
            path = "senddrives";
            filechooserlogic obj = new filechooserlogic();
            Thread th = new Thread(obj);
            th.start();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btresetActionPerformed
//
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new explorer().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btgetfilesfolders;
    private javax.swing.JButton btreset;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtfiles;
    // End of variables declaration//GEN-END:variables
}
