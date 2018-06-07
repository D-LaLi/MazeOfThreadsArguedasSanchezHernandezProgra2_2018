/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Character;
import domain.Coin;
import domain.Fast;
import domain.Furious;
import domain.Maze;
import domain.Smart;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Eduardo
 */
public class EasyMazePanel extends javax.swing.JPanel {

    Maze myMaze;
    Timer timer;
    ArrayList<Character> characterList;

    int rectWidth, rectHeight;
    int contador;

    public EasyMazePanel() {
        initComponents();
        myMaze = Maze.getInstance();
        setLocation(200, 200);
        characterList = new ArrayList<>();
        this.setOpaque(false);

        contador = 0;
        rectWidth = 50;
        rectHeight = 50;

        timer = new Timer(0, (ActionEvent ae) -> {
            long time = 1000 / 60;
            long start = System.nanoTime();
            long elapsed = System.nanoTime() - start;
            long wait = time - elapsed / 1000000;
            timer.setDelay((int) wait);

            repaint();

        });

        timer.start();

    }//fin constructor
    
    public void addCharacters(int fast, int smart, int furious) {        
        for (int i = 0; i < fast; i++) {
            characterList.add(new Fast(0, 250, myMaze.getMaze()));
        }    
        for (int i = 0; i < smart; i++) {
            characterList.add(new Smart(5, 0));
        }       
        for (int i = 0; i < furious; i++) {
            characterList.add(new Furious(5, 0));
        } 
        characterList.add(new Coin(1,5));
        characterList.add(new Coin(4,4));
    }

    //Método que dibuja los cuadros segun la matriz del laberinto
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        addBlock();
        for (int i = 0; i < myMaze.getMaze().length; i++) {
            for (int j = 0; j < myMaze.getMaze()[0].length; j++) {
                if (myMaze.getMaze()[i][j] == 1) {
                    g.fillRect(rectWidth * j, rectHeight * i, rectWidth, rectHeight);
                }
            }
        }

        //Esto es para que los hilos se inicien solamente una vez
        if (contador == 0) {
            for (int i = 0; i < characterList.size(); i++) {
                if (characterList.get(i) instanceof Fast) {
                    Fast aux = (Fast) characterList.get(i);
                    aux.setBlockHeight(rectHeight);
                    aux.setBlockWidth(rectWidth);
                    aux.start();
                }else{
                    characterList.get(i).start();
                }
            }//fin for
            
        }//fin if
    contador++;
        

        drawCharacters(g);

    } //fin metodo paintComponent

    private void drawCharacters(Graphics g) {
        //for sugerido por netBeans
        characterList.forEach((characterList1) -> {//pinta los characters
            if (characterList1 instanceof Fast) {
                g.drawImage(characterList1.getImage(), characterList1.getX(), characterList1.getY(), this);
            }else
                g.drawImage(characterList1.getImage(), (characterList1.getY() * rectWidth)/100, 
                (characterList1.getX() * rectHeight)/100, this);
        });
    }

    private void addBlock(){
        //hace el cambio en la matriz de unos y ceros para que aparesca y desaparescaslas paredes
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int mX = e.getX() / rectWidth;
                int mY = e.getY() / rectHeight;
                myMaze.addRemoveWall(mY, mX);
            }
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(900, 600));

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
