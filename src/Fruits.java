package src;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;


public class Fruits extends Sprite implements ImageObserver {

    public static ArrayList<Fruits> fruits = new ArrayList<>();
    Random random= new Random();
    int numberOfİmage;
    int speed;



    public Fruits(int X, int Y){
        super(X, Y);
        numberOfİmage=random.nextInt(4);
        speed=random.nextInt(1,4);

        setImage( getRandomImage());
    }

    Image getRandomImage(){
        Image image = new ImageIcon(Commons.fruits[numberOfİmage]).getImage();
        return image;
    }

     void activateFeature(){
        switch (Commons.fruitsNames[numberOfİmage]){
            case "elma":
                Board.score +=2;
                break;
            case "armut":
                Board.score +=1;
                break;
            case "muz":
                Board.score -=1;
                break;
            case "çilek":
                Board.score -=2;
                break;
        }
     }

    public void move(){
        this.y += speed;
    }

    Rectangle getRect(){
        return new Rectangle(x,y,Commons.FEATURE_WIDTH,Commons.FEATURE_WIDTH);
    }

    @Override
    public boolean imageUpdate(Image image, int i, int i1, int i2, int i3, int i4) {
        return false;
    }
}





