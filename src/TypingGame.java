import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class TypingGame extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final int FONT_SIZE = 20;

    private JTextField textField;
    private JLQKabel timerLabel;
    private JLabel scoreLabel;
    private JButton startButton;
    private JButton restartButton;
    private JPanel panel;
    private Random random;
    private int score;
    private int timeLeft;

    public TypingGame() {
        random = new Random();
        score = 0;
        timeLeft = 60;

        setTitle("Typing Game");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Dimension screenDim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenDim.width - WIDTH) / 2;
        int y = (screenDim.height - HEIGHT) / 2;
        setLocation(x, y);

        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        add(panel);

        JLabel label = new JLabel("Type the word below:");
        label.setBounds(20, 20, 200, 30);
        panel.add(label);

        textField = new JTextField();
        textField.setBounds(20, 60, 200, 30);
        textField.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE));
        textField.addActionListener(this);
        panel.add(textField);

        timerLabel = new JLabel("Time: " + timeLeft);
        timerLabel.setBounds(400, 20, 150, 30);
        panel.add(timerLabel);

        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setBounds(400, 60, 150, 30);
        panel.add(scoreLabel);

        startButton = new JButton("Start");
        startButton.setBounds(100, 130, 100, 50);
        startButton.addActionListener(this);
        panel.add(startButton);

        restartButton = new JButton("Restart");
        restartButton.setBounds(250, 130, 100, 50);
        restartButton.addActionListener(this);
        restartButton.setVisible(false);
        panel.add(restartButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            startGame();
        } else if (e.getSource() == restartButton) {
            restartGame();
        } else if (e.getSource() == textField) {
            checkWord();
        }
    }

    private void startGame() {
        startButton.setEnabled(false);
        textField.setEnabled(true);
        restartButton.setVisible(true);

        textField.requestFocus();
        textField.setText("");

        Thread thread = new Thread(() -> {
            while (timeLeft > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timeLeft--;
                timerLabel.setText("Time: " + timeLeft);
            }
            endGame();
        });
        thread.start();
    }
    private void restartGame() {
        score = 0;
        timeLeft = 60;
        scoreLabel.setText("Score: " + score);
        timerLabel.setText("Time: " + timeLeft);

        textField.setEnabled(true);
        textField.requestFocus();
        textField.setText("");

        Thread thread = new Thread(() -> {
            while (timeLeft > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timeLeft--;
                timerLabel.setText("Time: " + timeLeft);
            }
            endGame();
        });
        thread.start();

        restartButton.setVisible(false);
    }

    private void checkWord() {
        JLabel label = new JLabel();
        String typedWord = textField.getText();
        String currentWord = label.getText();

        if (typedWord.equals(currentWord)) {
            score++;
            scoreLabel.setText("Score: " + score);

            textField.setText("");
            generateWord();
        }
    }

    private void generateWord() {
        JLabel label = new JLabel();
        StringBuilder sb = new StringBuilder();
        int wordLength = 5 + random.nextInt(6);
        for (int i = 0; i < wordLength; i++) {
            char c = (char) ('a' + random.nextInt(26));
            sb.append(c);
        }
        label.setText(sb.toString());
        label.setBounds(100, 100, 200, 30); // JLabel 위치 설정
        label.setForeground(Color.BLACK); // 폰트 색상 설정
        panel.add(label); // JLabel을 패널에 추가
    }

    private void endGame() {
        textField.setEnabled(false);
        startButton.setEnabled(true);
        restartButton.setVisible(false);

        String message = "Time is up! Your score is " + score;
        JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);

        score = 0;
        timeLeft = 60;
        scoreLabel.setText("Score: " + score);
        timerLabel.setText("Time: " + timeLeft);
    }

    public static void main(String[] args) {
        TypingGame game = new TypingGame();
        game.setVisible(true);
    }
}