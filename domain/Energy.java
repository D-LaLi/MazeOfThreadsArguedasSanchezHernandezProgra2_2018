package domain;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduardo
 */
public class Energy extends Thread {

    private BufferedImage image;
    private int percentage;
    private boolean flag;
    ArrayList<BufferedImage> sprite;

    public Energy() {
        super("Energy");
        flag = true;
        percentage = 100;
    }

    @Override
    public void run() {
        while (true) {
            try {
                while (percentage > 0) {
                    flag = true;
                    sleep(50);
                    percentage--;
                }//fin while

                while (percentage < 100) {
                    flag = false;
                    sleep(40);
                    percentage++;
                }//fin while
            }//fin try 
            catch (InterruptedException e) {
                System.err.println(e.getCause());
            }
        }//fin run
    }

    //Metodos de acceso
    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
