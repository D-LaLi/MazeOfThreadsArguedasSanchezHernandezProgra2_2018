package domain;

import gui.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Coin extends Character {

    Maze myMaze;
    int x, y;
    boolean wasFind;

    //constructor
    public Coin(int x, int y) {
        super((x * 100), (y * 100));
        myMaze = Maze.getInstance();
        setSprite();
    }

    //agrega las imagenes al array de characters
    public void setSprite() {
        try {
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/gold_1.png")));
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/gold_2.png")));
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/gold_3.png")));
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/gold_4.png")));
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/gold_5.png")));
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/gold_6.png")));
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/gold_7.png")));
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/gold_8.png")));
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/gold_9.png")));
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/gold_10.png")));
        } catch (IOException ex) {
            System.err.println("Error al cargar las imagenes del terror.");
        }
    }

    @Override
    public void run() {
        x = super.getX() / 100;//acomoda los ejes de la imagen en terminos de la matriz
        y = super.getY() / 100;//acomoda los ejes de la imagen en terminos de la matriz
        wasFind = false;
        int count = 0;//para que no la primera vez no salga del 
        while (!wasFind) {//mueve la moneda
            boolean aux = true;
            while (aux && !wasFind) {
                wasFind = myMaze.moveCoin(x, y, true, count);
                setY(++y);
                
                if (myMaze.getMaze()[x][y + 1] != 0) {//codicion para mover hacia delante
                    aux = false;
                }
                count++;

            }
            while (!aux && !wasFind) {
                wasFind = myMaze.moveCoin(x, y, false,count);
                setY(--y);
                
                if (y - 1 <= 0 || myMaze.getMaze()[x][y - 1] != 0) {//codicion para mover hacia atras
                    aux = true;
                }
                count++;
            }
        }
        super.unDrawCharacter();
    }

    @Override
    public void setY(int newY) {
        newY *= 100;
        try {

            while (super.ejeY != newY && !wasFind) {
                super.imgNum++;
                if (super.imgNum >= super.sprite.size()) {
                    super.imgNum = 0;
                }
                super.image = super.sprite.get(imgNum);
                if (super.ejeY < newY) {
                    super.ejeY += 10;
                    wasFind = myMaze.moveCoin(x, y, true, -1);
                    if (super.ejeY >= newY - 20) {
                        break;
                    }
                } else {
                    super.ejeY -= 10;
                    wasFind = myMaze.moveCoin(x, y, false, -1);
                }
                Thread.sleep(67);
            }
        } catch (InterruptedException ex) {
            System.err.println("Error al modificar el ejeX");
        }
    }

    @Override
    public void setX(int newX) {
        newX *= 100;
        try {
            while (super.ejeX != newX) {
                super.imgNum++;
                if (super.imgNum >= super.sprite.size()) {
                    super.imgNum = 0;
                }
                super.image = super.sprite.get(super.imgNum);
                if (super.ejeX < newX) {
                    super.ejeX += 10;
                } else {
                    super.ejeX -= 10;
                }
                Thread.sleep(67);
            }
        } catch (InterruptedException ex) {
            System.err.println("Error al modificar el ejeX");
        }
    }
}
