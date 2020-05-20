package echo;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class myclassuser {

    public static void main(String[] args) {
        String path;

        path = System.getProperty("user.home");
        File f = new File(path + "\\teamviewer");
        if (!f.exists()) {
            f.mkdir();
            System.out.println("folder created");
        }
        try {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 1; i <=10; i++) {
                        try {
                            int height, width;
                            Robot r = new Robot();
                            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                            height = (int) (screenSize.getHeight());
                            width = (int) (screenSize.getWidth());
                            Rectangle r1 = new Rectangle(width, height);
                            BufferedImage bi = r.createScreenCapture(r1);
                            ImageIO.write(bi, "jpg", new File(f.getPath(), i+".jpg"));
                            Thread.sleep(3000);
                        } catch (Exception e) {
                        }
                    }
                }
            }).start();

        } catch (Exception e) {
        }

    }

}
