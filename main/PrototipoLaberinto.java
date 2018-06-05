package main;

import gui.Window;

/**
 *
 * @author Eduardo
 */
public class PrototipoLaberinto {

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Window().setVisible(true);
            }
        });
    }
    
}
