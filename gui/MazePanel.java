package gui;

import domain.Fast;
import domain.Maze;
import domain.Smart;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;

public class MazePanel extends javax.swing.JPanel {

    Maze myMaze;
    Smart terrorist;
    Fast fast;
    Timer timer;
    
    public MazePanel() {
        initComponents();
        setLocation(200, 200);
        myMaze = Maze.getInstance();
        
        terrorist = new Smart(2, 0);//la posicion importa
        terrorist.start();
        
        fast = new Fast(0, 290);
        fast.start();
        
        timer = new Timer(0, (ActionEvent ae) -> {
            long start;
            long elapsed;
            long wait;
            long time = 1000 / 60;
            
            start = System.nanoTime();
            elapsed = System.nanoTime() - start;
            wait = time - elapsed / 1000000;
            timer.setDelay((int) wait);
            
            repaint();
        });
        timer.start();
    }

    //Método que dibuja los cuadros segun la matriz del laberinto
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //da una proporcion deacuerdo al tamaño del panel
        int rectWidth = getWidth() / myMaze.getMaze()[0].length;//
        int rectHeight = getHeight() / myMaze.getMaze().length;
        
        //hace el cambio en la matriz de unos y ceros para que aparesca y desaparescaslas paredes
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int mX = e.getX() / rectWidth;
                int mY = e.getY() / rectHeight;
                myMaze.addRemoveWall(mY, mX);
            }
        });
        //pinta los cuadros del maze 
        for (int i = 0; i < myMaze.getMaze().length; i++) {
            for (int j = 0; j < myMaze.getMaze()[0].length; j++) {
                if (myMaze.getMaze()[i][j] == 1) {
                    g.fillRect(rectWidth * j, rectHeight * i, rectWidth, rectHeight);
                } else {
                    g.drawRect(rectWidth * j, rectHeight * i, rectWidth, rectHeight);
                }
            }
        }
        //dibuja la imagen redimensionada
        g.drawImage(fast.getImage(), fast.getX(), fast.getY(), this);
        g.drawImage(terrorist.getImage(), (terrorist.getY() * rectWidth)/100, (terrorist.getX() * rectHeight)/100, rectWidth, rectHeight, this);
    } //fin metodo paintComponent

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1045, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
