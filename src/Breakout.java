package src;

import javax.swing.*;
import java.awt.*;

public class Breakout extends JFrame {

    private Board board;

    public Breakout() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        StartPanel startPanel = new StartPanel(this);
        add(startPanel, BorderLayout.CENTER);

        setTitle("Breakout");
        setSize(Commons.WIDTH, Commons.HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void startGame(String op , int result) {
        getContentPane().removeAll();

        Board board = new Board(op , result);
        add(board);
        board.setFocusable(true);
        board.requestFocusInWindow();

        SoundManager.startBackgroundMusic();

        pack();
        setVisible(true);

    }

    public static void main(String[] args) {

            var game = new Breakout();
            game.setVisible(true);

    }
}
