package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel {

    private final Breakout breakout;
    private JTextField usernameField;
    private JTextField number1Field;
    private JTextField expressionField;
    private JTextField number2Field;

    public StartPanel(Breakout breakout) {
        this.breakout = breakout;
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());
        addBackgroundPanel();
    }

    private void addBackgroundPanel() {
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("src/resources/back9.png");
                Image img = backgroundImage.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);

        addLogoLabel(backgroundPanel);
        addCalculatorFields(backgroundPanel);
        addStartGameButton(backgroundPanel);

        add(backgroundPanel, BorderLayout.CENTER);
    }


    private JButton createStartGameButton() {
        JButton startGameButton = new JButton("Start Game");
        Font font = new Font("Times New Roman", Font.BOLD, 20);

        startGameButton.setFont(font);
        startGameButton.setForeground(new Color(255, 255, 255));
        startGameButton.setBackground(new Color(255, 25, 129));
        startGameButton.setBorder(BorderFactory.createLineBorder(Color.red, 2));

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = usernameField.getText();
                int num1 = Integer.parseInt(number1Field.getText());
                int num2 = Integer.parseInt(number2Field.getText());
                String expression = expressionField.getText();
                int result = calculateResult(num1, expression, num2);
                System.out.println("User Name: " + user);

                System.out.println("Result of the calculation: " + result);
                breakout.startGame(1);
            }
        });

        return startGameButton;
    }



    private void addStartGameButton(JPanel panel) {
        int startGameButtonX = 350;
        int startGameButtonY = 470;
        int startGameButtonWidth = 150;
        int startGameButtonHeight = 50;

        JButton startGameButton = createStartGameButton();
        startGameButton.setBounds(startGameButtonX, startGameButtonY, startGameButtonWidth, startGameButtonHeight);

        panel.add(startGameButton);
    }

    private int calculateResult(int num1, String expression, int num2) {
        switch (expression) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            default:
                throw new IllegalArgumentException("Invalid expression");
        }
    }

    private void addLogoLabel(JPanel panel) {
        ImageIcon logoIcon = new ImageIcon("src/resources/kiz3.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(65, 120, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        imageLabel.setBounds(50, 400, 65, 120);

        panel.add(imageLabel);
    }

    private void addCalculatorFields(JPanel panel) {
        int calculatorX = 250;
        int calculatorY = 170;
        int calculatorWidth = 300;
        int calculatorHeight = 100;

        JPanel calculatorPanel = new JPanel();
        calculatorPanel.setBounds(calculatorX, calculatorY, calculatorWidth, calculatorHeight);
        calculatorPanel.setLayout(new GridLayout(4, 1));
        //calculatorPanel.setBackground(new Color(179, 0, 0));

        JLabel username = new JLabel("User name");
        usernameField = new JTextField();
        JLabel number1Label = new JLabel("Number 1:");
        number1Field = new JTextField();
        JLabel expressionLabel = new JLabel("Expression:");
        expressionField = new JTextField();
        JLabel number2Label = new JLabel("Number 2:");
        number2Field = new JTextField();

        calculatorPanel.add(username);
        calculatorPanel.add(usernameField);
        calculatorPanel.add(number1Label);
        calculatorPanel.add(number1Field);
        calculatorPanel.add(expressionLabel);
        calculatorPanel.add(expressionField);
        calculatorPanel.add(number2Label);
        calculatorPanel.add(number2Field);

        panel.add(calculatorPanel);
    }

}
