package src;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Sprite {

    int x;
    int y;
    int imageWidth;
    int imageHeight;
    private Image image;

    public Sprite(){

    }
    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }


    protected void setX(int x) {

        this.x = x;
    }

    int getX() {

        return x;
    }

    protected void setY(int y) {

        this.y = y;
    }

    int getY() {

        return y;
    }

    int getImageWidth() {

        return imageWidth;
    }

    int getImageHeight() {

        return imageHeight;
    }

    void setImage(Image image) {
        this.image = image;
        getImageDimensions();
    }

    Image getImage() {

        return image;
    }

    Rectangle getRect() {

        return new Rectangle(x, y,
                image.getWidth(null), image.getHeight(null));
    }

    void getImageDimensions() {

        imageWidth = image.getWidth(null);
        imageHeight = image.getHeight(null);
    }
}
