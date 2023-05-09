import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MaFenetre extends JFrame {

    private JButton playButton;
    private JButton exitButton;
    public int commandNum = 0;

    public static void main(String[] args) throws IOException {

        MaFenetre fenetre = new MaFenetre();
        fenetre.setVisible(true);
        fenetre.setResizable(false);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            JLayeredPane layerpane = getLayeredPane();
            layerpane.removeAll();
            layerpane.revalidate();
            layerpane.repaint();
        } else if (e.getSource() == exitButton) {
            this.dispose();
        }
    }
    public MaFenetre() throws IOException {
        super("MazeScape");
        setSize(1000,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JFrame f = new JFrame("Ajouter une image dans JPanel");
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

        String text = "PLAY GAME";
        if (commandNum == 0){
            String cursor = ">";
            JLabel affCursor = new JLabel(cursor);
            affCursor.setBounds(400-text.length()-20, 550, 200, 100);
        }
        String text2 = "QUIT GAME";
        if (commandNum == 1){
            String cursor = ">";
            JLabel affCursor = new JLabel(cursor);
            affCursor.setBounds(400-text2.length()-20, 600, 200, 100);
        }
        affCursor.setFont(font);
        affCursor.setForeground(textColor);
        layeredPane.add(affCursor);

        JLabel affText = new JLabel(text);
        affText.setBounds(400, 550, 200, 100);
        affText.setFont(font);
        affText.setForeground(textColor);
        layeredPane.add(affText);
        JLabel affText2 = new JLabel(text2);
        affText2.setBounds(400, 600, 200, 100);
        affText2.setFont(font);
        affText2.setForeground(textColor);
        layeredPane.add(affText2);
        layeredPane.add(panel);

        add(layeredPane);

        /*playButton = new JButton("Play");
        playButton.setBounds(450, 600, 100, 50);
        playButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                playButton.setBackground(Color.BLACK);
                playButton.setForeground(Color.WHITE);
            }

            public void mouseExited(MouseEvent evt) {
                playButton.setBackground(Color.WHITE);
                playButton.setForeground(Color.BLACK);
            }
        });
        playButton.addActionListener(this::actionPerformed);
        layeredPane.add(playButton);
        layeredPane.setLayer(playButton, JLayeredPane.DEFAULT_LAYER);

        exitButton = new JButton("Goodbye");
        exitButton.setBounds(450, 700, 100, 50);
        exitButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                exitButton.setBackground(Color.WHITE);
                exitButton.setForeground(Color.BLACK);
            }

            public void mouseExited(MouseEvent evt) {
                exitButton.setBackground(Color.BLACK);
                exitButton.setForeground(Color.WHITE);
            }
        });
        exitButton.addActionListener(this::actionPerformed);
        layeredPane.add(exitButton); // Ajout du bouton "Goodbye" au-dessus de l'image et du bouton "Hello"
        layeredPane.setLayer(exitButton, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(panel);


        add(layeredPane);
        //add(helloButton);
*/

    }
}