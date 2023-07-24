import javax.swing.*;
import java.awt.*;

class GameFrame extends JFrame {
    private GamePanel gamePanel;
    private MainMenuPanel mainMenuPanel;

    GameFrame() {
        mainMenuPanel = new MainMenuPanel();
        mainMenuPanel.setPlayButtonListener(() -> {
            remove(mainMenuPanel);
            gamePanel = new GamePanel();
            add(gamePanel);
            pack();
            gamePanel.requestFocusInWindow();
        });

        add(mainMenuPanel);
        setTitle("Snake");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}

