import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MaFenetre extends JFrame {

    private JButton playButton;
    private JButton exitButton;


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
        playButton = new JButton("Play");
        playButton.setBounds(450, 600, 100, 50);
        playButton.addActionListener(this::actionPerformed);
        layeredPane.add(playButton);
        layeredPane.setLayer(playButton, JLayeredPane.DEFAULT_LAYER);

        exitButton = new JButton("Goodbye");
        exitButton.setBounds(450, 700, 100, 50);
        exitButton.addActionListener(this::actionPerformed);
        layeredPane.add(exitButton); // Ajout du bouton "Goodbye" au-dessus de l'image et du bouton "Hello"
        layeredPane.setLayer(exitButton, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(panel);


        add(layeredPane);
        //add(helloButton);

    }
}