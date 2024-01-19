package src;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Paddle extends Sprite  {

    private int dx;
    private int hiz = 2;
    private Image paddle = new ImageIcon("src/resources/paddle1.png").getImage();


    public Paddle() {
        
        initPaddle();        
    }
    
    void initPaddle() {

        setImage(paddle);
        getImageDimensions();
        resetState();
    }



    void move() {

        x += dx;

        if (x <= 0) {

            x = 0;
        }

        if (x >= Commons.WIDTH - imageWidth) {

            x = Commons.WIDTH - imageWidth;
        }
    }

    void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = hiz * -1 ;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = hiz;
        }

        if (key == KeyEvent.VK_UP && hiz < 7){
            dx = hiz++;
        }

        if (key == KeyEvent.VK_DOWN && hiz > 0 ){
            dx = hiz--;
        }
    }

    void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 0;
        }
    }

    private void resetState() {

        x = Commons.INIT_PADDLE_X;
        y = Commons.INIT_PADDLE_Y;
    }
}
