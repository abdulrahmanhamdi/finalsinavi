package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Board extends JPanel {

    private JButton tryAgainButton;
    private Timer timer;
    private String operationName;
    private int operationResult;

    private Timer scoreTimer;
    private Timer fruitTimer;


    public static Paddle paddle;

    private int bricksNum=0;


    private Random random = new Random();



    private boolean inGame = true;
    private Image backgroundImage;

    private int score = 0;
    private int timerSeconds = 0;
    private boolean youWin = true;


    public Board(String operation, int result) {
        initBoard();
        operationName = operation;
        result = operationResult;

    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(Commons.WIDTH, Commons.HEIGHT));

        backgroundImage = new ImageIcon("src/resources/back.jpg").getImage();
        //youLoseImage = new ImageIcon("src/resources/you_lose.png").getImage();
        //youWinImage = new ImageIcon("src/resources/you_win.png").getImage();

        gameInit();
    }

    private void gameInit() {


        paddle = new Paddle();


        timer = new Timer(Commons.PERIOD, new GameCycle());
        timer.start();

        scoreTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerSeconds++;

            }
        });

        fruitTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Fruits.fruits.add(new Fruits(random.nextInt(100,701), 0));

            }
        });

        scoreTimer.start();
        fruitTimer.start();
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        var g2d = (Graphics2D) g;
        g2d.drawImage(backgroundImage, 0, 0, Commons.WIDTH, Commons.HEIGHT, this);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        if (inGame) {
            drawObjects(g2d);
        } else {
            gameFinished(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics2D g2d) {


        g2d.drawString("5 + 4 ", 0,0);


        g2d.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),
                paddle.getImageWidth(), paddle.getImageHeight(), this);


        for (int i = 0; i < Fruits.fruits.size(); i++) {
            Fruits.fruits.get(i).drawFruits(g2d);
            if (Fruits.fruits.get(i).getY() > Commons.BOTTOM_EDGE)
                Fruits.fruits.remove(i);
        }

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        g2d.drawString("Score: " + score, 20, 20);
        g2d.drawString("Time: " + timerSeconds + "s", Commons.WIDTH - 120, 20);
    }


    private void gameFinished(Graphics2D g2d) {
       // int imageX2 = (Commons.WIDTH - youLoseImage.getWidth(this)) / 2;
       // int imageY2 = (Commons.HEIGHT - youLoseImage.getHeight(this)) / 2;

        if (youWin) {

            SoundManager.playVictorySound();
        } else {
            //g2d.drawImage(youLoseImage, imageX2, imageY2, this);

            SoundManager.playGameOverSound();
            tryAgainButton = new JButton("Try Again");
            tryAgainButton.setForeground(new Color(255, 255, 255));
            tryAgainButton.setBackground(new Color(65, 72, 134));
            tryAgainButton.setFont(new Font("Arial", Font.PLAIN, 16));
            //tryAgainButton.setBounds((Commons.WIDTH - 100) / 2, imageY2 + youLoseImage.getHeight(this) + 20, 120, 30);
            tryAgainButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    restartGame();
                }
            });
            add(tryAgainButton);

        }
    }

    private void restartGame() {
        inGame = true;
        youWin = true;
        score = 0;
        timerSeconds = 0;
        bricksNum = 0;
        Fruits.fruits.clear();
        gameInit();
        repaint();
        remove(tryAgainButton);
        SoundManager.startBackgroundMusic();
    }


    private class TAdapter extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
        }
    }

    private class GameCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            doGameCycle();
        }
    }

    private void doGameCycle() {


        for (int i = 0; i < Fruits.fruits.size(); i++) {
            Fruits.fruits.get(i).move();
        }

        paddle.move();
        checkCollision();
        repaint();
    }

    private void stopGame() {
        inGame = false;
        timer.stop();
        SoundManager.stopBackgroundMusic();
    }

    private void checkCollision() {


        for (int i = 0; i < Fruits.fruits.size(); i++) {
            if ((Fruits.fruits.get(i).getRect()).intersects(paddle.getRect())) {
                //Fruits.fruits.get(i).activateFeature();
                Fruits.fruits.remove(i);
            }
        }
    }
}

