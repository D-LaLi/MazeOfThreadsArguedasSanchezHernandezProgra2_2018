package domain;

import gui.MazePanel;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Furious extends Character {

    Maze myMaze;
    int x, y;//ejes por donde se mueve
    int sleepTime;
    ArrayList<BufferedImage> attackImages;

    //constructor
    public Furious(int x, int y) {
        super(x * 100, y * 100);
        myMaze = Maze.getInstance();
        sleepTime = 200;
        attackImages = new ArrayList<>();
        setSprite();
    }

    //agrega las imagenes al array de characters
    public void setSprite() {
        try {
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/WALK_000.png")));
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/WALK_001.png")));
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/WALK_002.png")));
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/WALK_003.png")));
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/WALK_004.png")));
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/WALK_005.png")));
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/WALK_006.png")));
            attackImages.add(ImageIO.read(getClass().getResourceAsStream("/Assets/ATTAK_000.png")));
            attackImages.add(ImageIO.read(getClass().getResourceAsStream("/Assets/ATTAK_001.png")));
            attackImages.add(ImageIO.read(getClass().getResourceAsStream("/Assets/ATTAK_002.png")));
            attackImages.add(ImageIO.read(getClass().getResourceAsStream("/Assets/ATTAK_003.png")));
            attackImages.add(ImageIO.read(getClass().getResourceAsStream("/Assets/ATTAK_004.png")));
            attackImages.add(ImageIO.read(getClass().getResourceAsStream("/Assets/ATTAK_005.png")));
            attackImages.add(ImageIO.read(getClass().getResourceAsStream("/Assets/ATTAK_006.png")));
        } catch (IOException ex) {
            System.err.println("Error al cargar las imagenes del ogro.");
        }
    }

    @Override
    public void run() {

        x = super.getX() / 100;//acomoda los ejes de la imagen en terminos de la matriz
        y = super.getY() / 100;//acomoda los ejes de la imagen en terminos de la matriz

        while (true) {//ciclo para que si queda atrapado siga moviendose despues
            if (moveCharacter(0)) {
                System.out.println("Llego a la meta");
                break;
            }
        }

    }

    //es el primer metodo que se invoca
    //y contiene el caso base
    //el parametro es cuando es invocado otra vez, para que no haga el movimiento contrario al hecho anteriormente
    private boolean moveCharacter(int n) {
        if (y == myMaze.getMaze()[0].length - 1) {//caso base, cuando llego a la posicion final
            return true;
        }
        int random = (int) (Math.random() * 4);//numero que determina donde se va a mover primero
        return randomMove(n, random);
    }

    //invoca cada movimiento de forma aleatoria dependiendo del numero pasado
    private boolean randomMove(int n, int random) {
        //ciclo para que considere todos los movimientos 
        for (int i = 0; i < 4; i++) {
            switch (random) {
                case 0:
                    if (moveUp(n)) {
                        return true;
                    }
                    break;
                case 1:
                    if (moveForward(n)) {
                        return true;
                    }
                    break;
                case 2:
                    if (moveDown(n)) {
                        return true;
                    }
                    break;
                case 3:
                    if (moveBack(n)) {
                        return true;
                    }
            }
            random++;
            if (random == 4) {
                random = 0;
            }
        }

        return false;
    }

    /*los siguientes metodos hacen los siguiente
    *La primera condicion verifica que no hay pared en el movimiento que hara
    *si es verdad mueve al perosnaje a esa posicion, de lo contrario retorna falso y sigue con las demas
    *si movio el personaje invoca nuevamente el metodo principal, que si encuentra la salida comienza a retornar true.
    *sino encontro la salida devuelve el character a la posicion anterior, pero primero pregunta,
    *si al espacio no se le a dibujado una pared, de ser asi simplemente invoca al metodo principal otra vez
     */
    //mueve para adelante
    public boolean moveForward(int n) {
        if (myMaze.getMaze()[x][y + 1] != 1 && n != 1) {//codicion para mover hacia delante
            setY(++y);
            findCoin(x, y, 0);
            if (moveCharacter(4)) {
                return true;
            }
            if (y - 1 >= 0 && myMaze.getMaze()[x][y - 1] != 1) {
                setY(--y);
                findCoin(x, y, 0);
            } else if (moveCharacter(0)) {
                return true;
            }
        }
        return false;
    }

    //mueve para abajo
    public boolean moveDown(int n) {
        if (myMaze.getMaze()[x + 1][y] != 1 && n != 2) {//codicion para mover hacia abajo
            setX(++x);
            findCoin(x, y, 0);
            if (moveCharacter(3)) {
                return true;
            }
            if (myMaze.getMaze()[x - 1][y] != 1) {
                setX(--x);
                findCoin(x, y, 0);
            } else if (moveCharacter(0)) {
                return true;
            }
        }
        return false;
    }

    //mueve para arriba
    public boolean moveUp(int n) {
        if (myMaze.getMaze()[x - 1][y] != 1 && n != 3) {//codicion para mover hacia arriba
            setX(--x);
            findCoin(x, y, 0);
            if (moveCharacter(2)) {
                return true;
            }
            if (myMaze.getMaze()[x + 1][y] != 1) {
                setX(++x);
                findCoin(x, y, 0);
            } else if (moveCharacter(0)) {
                return true;
            }
        }
        return false;
    }

    //mueve para atras
    public boolean moveBack(int n) {

        if (y - 1 >= 0 && myMaze.getMaze()[x][y - 1] != 1 && n != 4) {//codicion para mover hacia atras
            setY(--y);
            findCoin(x, y, 0);
            if (moveCharacter(1)) {
                return true;
            }
            if (myMaze.getMaze()[x][y + 1] != 1) {
                setY(++y);
                findCoin(x, y, 0);
            } else if (moveCharacter(0)) {
                return true;
            }
        }
        return false;
    }

    //se sobre escribe el metodp setY para que el personaje aparente moverse por
    //el laberinto
    @Override
    public void setY(int newY) {
        newY *= 100;
        try {
            while (super.ejeY != newY) {
                super.imgNum++;
                if (super.imgNum >= super.sprite.size()) {
                    super.imgNum = 0;
                }
                super.image = super.sprite.get(imgNum);
                if (super.ejeY < newY) {
                    super.ejeY += 5;
                } else {
                    super.ejeY -= 5;
                }
                Thread.sleep(sleepTime);
            }
        } catch (InterruptedException ex) {
            System.err.println("Error al modificar el ejeX");
        }
    }

    //se sobre escribe el metodp setX para que el personaje aparente moverse por
    //el laberinto
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
                    super.ejeX += 5;
                } else {
                    super.ejeX -= 5;
                }
                Thread.sleep(sleepTime);
            }
        } catch (InterruptedException ex) {
            System.err.println("Error al modificar el ejeX");
        }
    }

    //pregunta si hay una moneda en la posicion y lo hace mas rapido de ser verdadero
    public void findCoin(int auxX, int auxY, int time) {
        if (myMaze.getMaze()[auxX][y] == 4) {
            myMaze.removeCoin(auxX, auxY);
            showAttack();

        }
        if (auxY - 1 >= 0 && auxY - 2 >= 0 && myMaze.getMaze()[auxX][y - 1] == 4) {
            myMaze.removeCoin(auxX, auxY - 1);
            showAttack();

        }
        if (auxX - 1 >= 0 && auxX - 1 > myMaze.getMaze().length && myMaze.getMaze()[auxX - 1][auxY] == 4) {
            myMaze.removeCoin(auxX - 1, auxY);
            showAttack();

        }
        if (y + 1 > myMaze.getMaze()[0].length && myMaze.getMaze()[auxX][auxY + 1] == 4) {
            myMaze.removeCoin(auxX, auxY + 1);
            showAttack();

        }
        if (auxX + 1 > myMaze.getMaze().length && myMaze.getMaze()[auxX + 1][auxY] == 4) {
            myMaze.removeCoin(auxX + 1, auxY);
            showAttack();

        }
        if (time == 0) {//lo llama nuevamente para intentar que reconosca la posicion de la moneda
            findCoin(++auxX, ++auxY, 1);
            findCoin(--auxX, --auxY, 1);
        }
    }

    private void showAttack() {
        try {
            for (int i = 0; i < attackImages.size(); i++) {
                super.setImageAttack(attackImages.get(i));
                Thread.sleep(sleepTime);
            }
        } catch (InterruptedException ex) {
            System.err.println("Error en el spleep del ataque");
        }
    }
}
