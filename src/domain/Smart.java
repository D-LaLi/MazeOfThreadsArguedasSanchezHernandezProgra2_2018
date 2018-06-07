package domain;

import gui.EasyMazePanel;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Smart extends Character {

    Maze myMaze;
    int ejeX, ejeY;

    //constructor
    public Smart(int x, int y) {
        super(x * 100, y * 100);
        myMaze = Maze.getInstance();
        setSprite();
    }

    //agrega las imagenes al array de characters
    public void setSprite() {
        try {
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/terror0.png")));
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/terror1.png")));
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/terror2.png")));
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/terror3.png")));
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/terror4.png")));
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/terror5.png")));
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/terror6.png")));
            super.addToArray(ImageIO.read(getClass().getResourceAsStream("/Assets/terror7.png")));
        } catch (IOException ex) {
            System.err.println("Error al cargar las imagenes del terror.");
        }
    }

    @Override
    public void run() {
        ejeX = super.getX() / 100;//acomoda los ejes de la imagen en terminos de la matriz
        ejeY = super.getY() / 100;//acomoda los ejes de la imagen en terminos de la matriz
        System.out.println("Inicio ejes " + ejeX + " - " + ejeY);
        int i = 0;// controla la imagen que se mueve
        super.setImage(i);

        //invoca el metodo recursivo que mueva al character que solo retornara si es true
        if (moveTerrorist(0)) {
            System.out.println("Llego a la meta");
        }

    }

    //es el primer metodo que se invoca
    //y contiene el caso base
    //el parametro es cuando es invocado otra vez, para que no haga el movimiento contrario al hecho anteriormente
    private boolean moveTerrorist(int n) {
        if (ejeY == myMaze.getMaze()[0].length - 1) {//caso base, cuando llego a la posicion final
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
        if (myMaze.getMaze()[ejeX][ejeY + 1] == 0 && n != 1) {//codicion para mover hacia delante
            setY(++ejeY);
            if (moveTerrorist(4)) {
                return true;
            }
            if (ejeY - 1 >= 0 && myMaze.getMaze()[ejeX][ejeY - 1] == 0) {
                setY(--ejeY);
            } else if (moveTerrorist(0)) {
                return true;
            }
        }
        return false;
    }

    //mueve para abajo
    public boolean moveDown(int n) {
        if (myMaze.getMaze()[ejeX + 1][ejeY] == 0 && n != 2) {//codicion para mover hacia abajo
            setX(++ejeX);
            if (moveTerrorist(3)) {
                return true;
            }
            if (myMaze.getMaze()[ejeX - 1][ejeY] == 0) {
                setX(--ejeX);
            } else if (moveTerrorist(0)) {
                return true;
            }
        }
        return false;
    }

    //mueve para arriba
    public boolean moveUp(int n) {
        if (myMaze.getMaze()[ejeX - 1][ejeY] == 0 && n != 3) {//codicion para mover hacia arriba
            setX(--ejeX);
            if (moveTerrorist(2)) {
                return true;
            }
            if (myMaze.getMaze()[ejeX + 1][ejeY] == 0) {
                setX(++ejeX);
            } else if (moveTerrorist(0)) {
                return true;
            }
        }
        return false;
    }

    //mueve para atras
    public boolean moveBack(int n) {
        if (ejeY - 1 >= 0 && myMaze.getMaze()[ejeX][ejeY - 1] == 0 && n != 4) {//codicion para mover hacia atras
            setY(--ejeY);
            if (moveTerrorist(1)) {
                return true;
            }
            if (myMaze.getMaze()[ejeX][ejeY + 1] == 0) {
                setY(++ejeY);
            } else if (moveTerrorist(0)) {
                return true;
            }
        }
        return false;
    }

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
                    super.ejeX += 5;
                } else {
                    super.ejeX -= 5;
                }
                Thread.sleep(67);
            }
        } catch (InterruptedException ex) {
            System.err.println("Error al modificar el ejeX");
        }
    }
}
