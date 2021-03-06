/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domain.Fast;
import java.applet.AudioClip;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Paula
 */
public class WindowNormal extends javax.swing.JFrame {

   AudioClip sonido;
    ChoosePanel chooPanel;
    int i=3;
    boolean state=true;
    
    
    public WindowNormal() {
        initComponents();
         setExtendedState(JFrame.MAXIMIZED_BOTH);
         pack();
        sonido = java.applet.Applet.newAudioClip(getClass().getResource("/assets/Sonidos Pacman.wav"));
        Icon m = new ImageIcon(getClass().getResource("/assets/music.png"));
        lblMusic.setIcon(m);
        start();
        lblStop.setEnabled(false);
        crono.start();
    }
    
    Thread crono = new Thread(){
    @Override
    public void run(){
        int hours=0, min=0, sec=0;
        
        for(;;){
            try{
            
                sec++;
                
                if(sec>59){
                    sec=0;
                    min++;
                }
                if(min>59){
                    min=0;
                    hours++;
                }
                if(sec<10){
                lblcrono.setText(hours+":"+min+":0"+sec);
                }else{
                 lblcrono.setText(hours+":"+min+":"+sec);
                }
                Thread.sleep(999);
            }catch(InterruptedException e){
                
            }
        }
    }
    };
    
     public void start(){
        int i=0;
        while(i!=100){
        i++;
        sonido.loop();
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        normalMazePanel1 = new gui.NormalMazePanel();
        lblHome = new javax.swing.JLabel();
        lblplay = new javax.swing.JLabel();
        lblpause = new javax.swing.JLabel();
        lblStop = new javax.swing.JLabel();
        lblMusic = new javax.swing.JLabel();
        lblchronometer = new javax.swing.JLabel();
        lblcrono = new javax.swing.JLabel();
        lblfondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        normalMazePanel1.setPreferredSize(new java.awt.Dimension(1045, 1045));

        javax.swing.GroupLayout normalMazePanel1Layout = new javax.swing.GroupLayout(normalMazePanel1);
        normalMazePanel1.setLayout(normalMazePanel1Layout);
        normalMazePanel1Layout.setHorizontalGroup(
            normalMazePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 810, Short.MAX_VALUE)
        );
        normalMazePanel1Layout.setVerticalGroup(
            normalMazePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
        );

        getContentPane().add(normalMazePanel1);
        normalMazePanel1.setBounds(310, 50, 810, 630);

        lblHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/home.png"))); // NOI18N
        lblHome.setToolTipText("Home");
        lblHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHomeMouseClicked(evt);
            }
        });
        getContentPane().add(lblHome);
        lblHome.setBounds(10, 10, 50, 50);

        lblplay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/iniciar.png"))); // NOI18N
        lblplay.setToolTipText("Play");
        lblplay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblplay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblplayMouseClicked(evt);
            }
        });
        getContentPane().add(lblplay);
        lblplay.setBounds(10, 70, 50, 50);

        lblpause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pause.png"))); // NOI18N
        lblpause.setToolTipText("Pause");
        lblpause.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblpause.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblpauseMouseClicked(evt);
            }
        });
        getContentPane().add(lblpause);
        lblpause.setBounds(10, 130, 50, 50);

        lblStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/stop.png"))); // NOI18N
        lblStop.setToolTipText("Stop");
        lblStop.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblStop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblStopMouseClicked(evt);
            }
        });
        getContentPane().add(lblStop);
        lblStop.setBounds(10, 190, 50, 50);

        lblMusic.setToolTipText("Music");
        lblMusic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMusic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMusicMouseClicked(evt);
            }
        });
        getContentPane().add(lblMusic);
        lblMusic.setBounds(10, 250, 50, 50);

        lblchronometer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/cronometro.png"))); // NOI18N
        getContentPane().add(lblchronometer);
        lblchronometer.setBounds(1120, 20, 50, 60);

        lblcrono.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblcrono.setText("0:0:0");
        getContentPane().add(lblcrono);
        lblcrono.setBounds(1180, 30, 70, 30);

        lblfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/fm.jpg"))); // NOI18N
        getContentPane().add(lblfondo);
        lblfondo.setBounds(0, -160, 1580, 1120);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHomeMouseClicked
        this.setVisible(false);
       try {
           chooPanel = new ChoosePanel();
           chooPanel.setVisible(true);
        sonido.stop();
       } catch (FileNotFoundException ex) {
           Logger.getLogger(WindowNormal.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    }//GEN-LAST:event_lblHomeMouseClicked

    private void lblMusicMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMusicMouseClicked
        if(i%2==0){
            Icon m= new ImageIcon(getClass().getResource("/assets/music.png"));
            lblMusic.setIcon(m);
            start();
            i++;
        }else{
            Icon m= new ImageIcon(getClass().getResource("/assets/musicOff.png"));
            lblMusic.setIcon(m);
            sonido.stop();
            i++;
        }
    }//GEN-LAST:event_lblMusicMouseClicked

    private void lblplayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblplayMouseClicked
      lblHome.setEnabled(false);
        lblStop.setEnabled(true);
      if (state == false && i % 2 != 0) {
            crono.resume();
            start();

        } else {
            crono.resume();
        }
        state = false;
    }//GEN-LAST:event_lblplayMouseClicked

    private void lblpauseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblpauseMouseClicked
       sonido.stop();
       crono.suspend();
      
    }//GEN-LAST:event_lblpauseMouseClicked

    private void lblStopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStopMouseClicked
         int dialog = JOptionPane.YES_NO_OPTION;
        JOptionPane.showConfirmDialog(null, "do you want to stop the game?", "Warning", dialog);
        
        if(dialog==JOptionPane.YES_OPTION){
            crono.suspend();
            lblHome.setEnabled(true);
            lblStop.setEnabled(false);
        }
    }//GEN-LAST:event_lblStopMouseClicked

    public NormalMazePanel getNormalMazePanel1() {
        return normalMazePanel1;
    }

    public void setNormalMazePanel1(NormalMazePanel normalMazePanel1) {
        this.normalMazePanel1 = normalMazePanel1;
    }

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblHome;
    private javax.swing.JLabel lblMusic;
    private javax.swing.JLabel lblStop;
    private javax.swing.JLabel lblchronometer;
    private javax.swing.JLabel lblcrono;
    private javax.swing.JLabel lblfondo;
    private javax.swing.JLabel lblpause;
    private javax.swing.JLabel lblplay;
    private gui.NormalMazePanel normalMazePanel1;
    // End of variables declaration//GEN-END:variables
}
