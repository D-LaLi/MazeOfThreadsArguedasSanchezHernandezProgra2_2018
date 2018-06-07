/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import util.Utilidades;

/**
 *
 * @author Eduardo
 */
public class Fast extends Character {

    //direcciones que se manejan: 0(arriba), 1(abajo), 2(atras), 3(adelante)
    private int direction = 3; //siempre camina hacia adelante
    private int sleepTime;
    private Energy energy;
    private ArrayList<Integer> direcctions;
    private short[][] binaryMatrix;
    int contador = 0;// contador para las animaciones
    boolean isWinner;

    // estas son las variables que indican el tamaño de los bloques del laberinto
    private int blockHeight;
    private int blockWidth;

    public Fast() {
        
    } 
    //Constructor
    public Fast(int x, int y, short[][] binaryMatrix) {
        super(x, y);
        isWinner = false;
        setSprite();
        this.setName("Fast");
        sleepTime = 12;
        energy = new Energy();
        this.binaryMatrix = binaryMatrix;
    }

    //Este método se encarga de verficar las direcciones libres que tiene el bicho
    //sin tomar en cuenta la direccion de la que viene, es decir, solo verfica 
    //los costados y el frente, evitando así, que se devuelva de manera innecesaria
    //por la direccion donde venía.
    public void isClear() {

        this.direcctions = new ArrayList<>();

        //caso 1: cuando hay un espacio libre al frente       
        if (direction == 0 || direction == 3 || direction == 1) {
            // if (centerY % (blockHeight / 2) == 0) {
            if (binaryMatrix[ejeY / blockHeight][(ejeX / blockWidth) + 1] == 0 || 
                    binaryMatrix[ejeY / blockHeight][(ejeX / blockWidth) + 1] == 3) {
                direcctions.add(3);
            } else if((binaryMatrix[ejeY / blockHeight][(ejeX / blockWidth)] == 3)){
                isWinner = true;
                interrupt();
            }
            //  }//fin if
        }//fin if

        //caso 1: cuando hay un espacio libre atras 
        if (direction == 0 || direction == 2 || direction == 1) {
            // if (centerY % (blockHeight / 2) == 0) {
            if (binaryMatrix[ejeY / blockHeight][(ejeX / blockWidth) - 1] == 0) {
                direcctions.add(2);
            }
            // }//fin if
        }//fin if

        //caso 3: cuando hay un espacio arriba
        if (direction == 0 || direction == 3 || direction == 2) {
            if (binaryMatrix[(ejeY / blockHeight) - 1][(ejeX / blockWidth)] == 0) {
                direcctions.add(0);
            }
        }

        //caso 2: cuando hay un espacio abajo 
        if (direction == 1 || direction == 3 || direction == 2) {
            if (binaryMatrix[(ejeY / blockHeight) + 1][(ejeX / blockWidth)] == 0) {
                direcctions.add(1);
            }
        }

        //En caso de que los costados y el frente esten bloqueados lo devuleve por
        //la direccion por donde venia.
        if (direcctions.isEmpty()) {
            switch (direction) {
                case 0:
                    direcctions.add(1);
                    break;
                case 1:
                    direcctions.add(0);
                    break;
                case 2:
                    direcctions.add(3);
                    break;
                case 3:
                    direcctions.add(2);
                    break;
                default:
                    break;
            }//fin switch
        }//fin if 
        
    }

    //Método para definir la secunencia de imagenes para la animacion.
    private void setSprite() {
        try {
            super.addToArray(ImageIO.read(getClass().getResource("/assets/runningWolf1.png")));
            super.addToArray(ImageIO.read(getClass().getResource("/assets/runningWolf2.png")));
            super.addToArray(ImageIO.read(getClass().getResource("/assets/runningWolf3.png")));
            super.addToArray(ImageIO.read(getClass().getResource("/assets/runningWolf4.png")));
            super.addToArray(ImageIO.read(getClass().getResource("/assets/runningWolf5.png")));
            super.addToArray(ImageIO.read(getClass().getResource("/assets/sleepingwolf1.png")));
            super.addToArray(ImageIO.read(getClass().getResource("/assets/sleepingwolf2.png")));
            super.addToArray(ImageIO.read(getClass().getResource("/assets/sleepingwolf3.png")));
            super.addToArray(ImageIO.read(getClass().getResource("/assets/sleepingwolf4.png")));
            super.addToArray(ImageIO.read(getClass().getResource("/assets/runningwolfiz1.png")));
            super.addToArray(ImageIO.read(getClass().getResource("/assets/runningwolfiz2.png")));
            super.addToArray(ImageIO.read(getClass().getResource("/assets/runningwolfiz3.png")));
            super.addToArray(ImageIO.read(getClass().getResource("/assets/runningwolfiz4.png")));
            super.addToArray(ImageIO.read(getClass().getResource("/assets/runningwolfiz5.png")));
        } catch (IOException | IllegalArgumentException ex) {
            System.err.println("Error leyendo las imagenes.");
        }
    }

    //metodo que elige una direccion aleatoriamente 
    public int getDirectionToGo() {
        isClear();
        int randomDirection = Utilidades.getRandom(getDirecctions().size());
        return getDirecctions().get(randomDirection);
    }

    @Override
    public void run() {

        energy.start();
        setImage(0);

        while (true) {
            try {

                sleep(sleepTime);

                if (energy.getFlag()) {
                    contador = 0; 
                    switch (getDirectionToGo()) {
                        case 0:
                            moveUp();
                            break;
                        case 1:
                            moveDown();
                            break;
                        case 2:
                            moveBack();
                            break;
                        case 3:
                            moveForward();
                            break;
                    }//fin switch
                } else {
                    contador++;
                    startSleeping();
                }
            } catch (InterruptedException ex) {
                System.err.println(ex.getMessage());
            }
        }//fin while
    } //fin run

    //Se mueven por bloques de acuerdo al tamaño de los bloques del laberinto
    public void moveForward() throws InterruptedException {
        for (int i = 0, j = 0, k = 0; i < blockWidth; i++, j++) {
            sleep(sleepTime);
            if (j > (blockWidth / sprite.size())) {
                setImage(k);
                k = (k == 4) ? 0 : (k + 1);
                j = 0;
            }
            setX(getX() + 1);
        }//fin for
        direction = 3;
    }//fin moveForward

    public void moveDown() throws InterruptedException {
        for (int i = 0; i < blockHeight; i++) {
            sleep(sleepTime);
            setY(getY() + 1);
        }
        direction = 1;
    }//fin moveDown

    public void moveBack() throws InterruptedException {
        for (int i = 0, j = 0, k = 9; i < blockWidth; i++, j++) {
            sleep(sleepTime);
            if (j > (blockWidth / sprite.size())) {
                setImage(k);
                k = (k == 13) ? 9 : (k + 1);
                j = 0;
            }
            setX(getX() - 1);
        }
        direction = 2;
    }//moveBack

    public void moveUp() throws InterruptedException {
        for (int i = 0; i < blockHeight; i++) {
            sleep(sleepTime);
            setY(getY() - 1);
        }
        direction = 0;
    }//fin moveUp

    //El lobo empieza a dormir(recuperar energia)
    public synchronized void startSleeping() throws InterruptedException {
        //pone el bicho a dormir
        if (contador <= 1) {
            for (int i = 5; i < 9; i++) {
                //animaciones
                sleep(sleepTime);
                setImage(i);
            }
        }
        sleep(1);
    }

    public short[][] getBinaryMatrix() {
        return binaryMatrix;
    }

    public void setBinaryMatrix(short[][] binaryMatrix) {
        this.binaryMatrix = binaryMatrix;
    }

    //Métodos de acceso
    public ArrayList<Integer> getDirecctions() {
        return direcctions;
    }

    public void setDirecctions(ArrayList<Integer> direcctions) {
        this.direcctions = direcctions;
    }

    public int getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(int blockHeight) {
        this.blockHeight = blockHeight;
    }

    public int getBlockWidth() {
        return blockWidth;
    }

    public void setBlockWidth(int blockWith) {
        this.blockWidth = blockWith;
    }

}
