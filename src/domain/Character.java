package domain;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Character extends Thread {

    protected int ejeX;
    protected int ejeY;
    protected int imgNum;
    protected BufferedImage image;
    //array que contiene representaciones graficas de esta clase
    protected ArrayList<BufferedImage> sprite;
    //array que contiene las direcciones que puede recorre el personaje

    //Constructores
    public Character(int x, int y) {
        this.ejeX = x;
        this.ejeY = y;
        this.imgNum = 0;
        this.sprite = new ArrayList<>();
    }

    public Character() {
        ejeX = 0;
        ejeY = 0;
        imgNum = 0;
        sprite = null;
    }
    
    //Método de acceso
    public int getX() {
        return ejeX;
    }

    public void setX(int x) {
        this.ejeX = x;
    }

    public int getY() {
        return ejeY;
    }

    public void setY(int y) {
        this.ejeY = y;
    }

    public int getImgNum() {
        return imgNum;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(int position) {
        if(position < sprite.size()){
            this.image = sprite.get(position);
        }
    }

    public void setImgNum(int imgNum) {
        this.imgNum = imgNum;
    }

    public ArrayList<BufferedImage> getSprite() {
        return sprite;
    }

    public void setSprite(ArrayList<BufferedImage> sprite) {
        this.sprite = sprite;
    }

    //agrega una imgen al arraylist 
    public void setArrayImages(BufferedImage i) {
        this.sprite.add(i);
    }

    public void addToArray(BufferedImage image) {
        sprite.add(image);
    }    
}
