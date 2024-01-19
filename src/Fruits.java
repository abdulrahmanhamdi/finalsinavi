package src;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;


public class Fruits extends Sprite{

    public static ArrayList<Fruits> fruits = new ArrayList<>();
    private Image fruitsimage= new ImageIcon("src/resources/ball1.png").getImage();
    Random random= new Random();



    public Fruits(int X, int Y){
        super(X, Y);

        setImage( getRandomImage());
    }

    Image getRandomImage(){
        Image image = new ImageIcon(Commons.frutes[random.nextInt(4)]).getImage();
        return image;
    }
     void drawFruits(Graphics g){
        g.drawImage(getImage(),getX(), getY(), (ImageObserver) this);
     }
    // void activateFeature();

    public void move(){
        this.y += random.nextInt(0,4);
    }

    Rectangle getRect(){
        return new Rectangle(x,y,Commons.FEATURE_WIDTH,Commons.FEATURE_WIDTH);
    }

}





