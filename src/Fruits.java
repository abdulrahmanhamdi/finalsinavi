package src;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Fruits extends Sprite{

    public static ArrayList<Fruits> features = new ArrayList<>();

    public Fruits(int X, int Y){
        this.x = X;
        this.y = Y;
    }

     //void drawFeature(Graphics g);
    // void activateFeature();

    public void move(){
        this.y += 1;
    }

    Rectangle getRect(){
        return new Rectangle(x,y,Commons.FEATURE_WIDTH,Commons.FEATURE_WIDTH);
    }

}





