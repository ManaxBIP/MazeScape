import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MaFenetre extends JFrame implements KeyListener {

    private String text = "PLAY GAME";
    private String text2 = "QUIT GAME";
    public int commandNum = 0;
    public JLabel affText = new JLabel(text);
    public JLabel affText2 = new JLabel(text2);
    private JButton playButton;
    private JButton exitButton;


    public static void main(String[] args) throws IOException {
        MaFenetre fenetre = new MaFenetre();
        fenetre.setVisible(true);
        fenetre.setResizable(false);
    }

    public MaFenetre() throws IOException {
        super("MazeScape");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addKeyListener(this);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1000, 1000);
        BufferedImage img = ImageIO.read(new File("home.png"));
        panel.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
        JLabel pic = new JLabel(new ImageIcon(img));
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
        panel.add(pic);

        // Création des boutons et ajout au JLayeredPane

        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 32);
        Color textColor = Color.RED;


        affText.setBounds(400, 550, 200, 100);
        affText.setFont(font);
        affText.setForeground(textColor);
        layeredPane.add(affText);


        affText2.setBounds(400, 600, 200, 100);
        affText2.setFont(font);
        affText2.setForeground(textColor);
        layeredPane.add(affText2);

        layeredPane.add(panel);

        add(layeredPane);

        pack();
        setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            commandNum--;
            if (commandNum < 0) {
                commandNum = 1;
            }
            updateCursor();
        } else if (code == KeyEvent.VK_DOWN) {
            commandNum++;
            if (commandNum > 1) {
                commandNum = 0;
            }
            updateCursor();
        }
        if (commandNum == 0 && e.getKeyCode() == KeyEvent.VK_ENTER) {
            JLayeredPane layeredPane = getLayeredPane();
            layeredPane.removeAll();
            layeredPane.revalidate();
            layeredPane.repaint();
        } else if (commandNum == 1 && e.getKeyCode() == KeyEvent.VK_ENTER) {
            dispose();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void updateCursor() {
        JLayeredPane layeredPane = getLayeredPane();
        Component[] components = layeredPane.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (label.getText().equals(">")) {
                    layeredPane.remove(label);
                    break;
                }
            }
        }

        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 32);
        Color textColor = Color.RED;
        String cursor = ">";

        if (commandNum == 0) {
            JLabel affCursor = new JLabel(cursor);
            affCursor.setBounds(400 - affText.getText().length() - 20, 550, 200, 100);
            affCursor.setFont(font);
            affCursor.setForeground(textColor);
            layeredPane.add(affCursor);
        } else if (commandNum == 1) {
            JLabel affCursor = new JLabel(cursor);
            affCursor.setBounds(400 - affText2.getText().length() - 20, 600, 200, 100);
            affCursor.setFont(font);
            affCursor.setForeground(textColor);
            layeredPane.add(affCursor);
        }

        layeredPane.revalidate();
        layeredPane.repaint();
    }
}