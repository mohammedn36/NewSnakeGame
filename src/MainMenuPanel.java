import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MainMenuPanel extends JPanel {
    private JButton playButton;
    private PlayButtonListener playButtonListener;

    MainMenuPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT));
        setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel("Snake");
        titleLabel.setFont(new Font("Ink Free", Font.BOLD, 75));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.CENTER);

        playButton = new JButton("Play");
        playButton.setFont(new Font("Ink Free", Font.BOLD, 40));
        playButton.setFocusPainted(false);
        playButton.setBackground(Color.BLACK);
        playButton.setForeground(Color.WHITE);
        playButton.addActionListener(e -> {
            if (playButtonListener != null) {
                playButtonListener.onPlayButtonClicked();
            }
        });
        add(playButton, BorderLayout.SOUTH);
    }

    public void setPlayButtonListener(PlayButtonListener listener) {
        this.playButtonListener = listener;
    }

    interface PlayButtonListener {
        void onPlayButtonClicked();
    }
}
