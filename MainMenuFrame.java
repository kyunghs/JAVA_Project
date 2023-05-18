import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenuFrame extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private static final int FONT_SIZE = 30;

    private JButton startButton;
    // private JButton loginButton; //로그인 버튼 생성위치가 안잡힙
    private JPanel panel;

    public MainMenuFrame() {
        setTitle("Typing Game - Main Menu");
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

        JLabel titleLabel = new JLabel("TYPING GAME");
        titleLabel.setBounds(180, 80, 250, 50);
        titleLabel.setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
        panel.add(titleLabel);

        startButton = new JButton("Start Game");
        startButton.setBounds(20, 180, 200, 50);
        startButton.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE));
        startButton.addActionListener(this);
        panel.add(startButton);

        // loginButton = new JButton("Login");                          //로그인 버튼 생성위치를 못잡겠음
        // titleLabel.setBounds(-20, 180, 200, 50);                     // 생성위치를 잡으면 타이핑게임 제목이 맘대로 이동해버림
        // titleLabel.setFont(new Font("Arial", Font.PLAIN, FONT_SIZE));
        // loginButton.addActionListener(this);
        // panel.add(loginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            TypingGame game = new TypingGame();
            game.setVisible(true);
            dispose();
        }
    }
}