import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;


public class MaFenetre extends JFrame {

    private JButton playButton;
    private JButton exitButton;

    public int x = 400;
    public int y = 400;
    int sizeX = 5;
    int sizeY = 5;
    int cellSize = 100;
    private double zoom = 2.5; // Valeur initiale de zoom
    Maze m1 = new Maze(sizeX, sizeY);


    public static void main(String[] args) throws IOException {

        MaFenetre fenetre = new MaFenetre();
        fenetre.setVisible(true);
        fenetre.setResizable(false);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            applyZoom();
        } else if (e.getSource() == exitButton) {
            this.dispose();
        }
    }

    private void applyZoom() {
        JLayeredPane layerpane = getLayeredPane();
        layerpane.removeAll();
        layerpane.revalidate();
        layerpane.repaint();
        MazeDisplay md = new MazeDisplay(m1, cellSize);
        md.setScale(zoom); // Appel de la méthode setScale() de MazeDisplay pour appliquer le zoom
        layerpane.add(md);
        md.setBounds(x, y, 0, 0);
        md.setSize(layerpane.getSize());
        md.setPreferredSize(layerpane.getSize());
        md.setMaximumSize(layerpane.getSize());
        md.setMinimumSize(layerpane.getSize());
        md.requestFocusInWindow();
        layerpane.addKeyListener(md);
        md.setFocusable(true);
    }


    public MaFenetre() throws IOException {
        super("MazeScape");
        setSize(1000,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JFrame f = new JFrame("Ajouter une image dans JPanel");
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1000, 1000);
        BufferedImage img = ImageIO.read(new File("./home.png"));
        panel.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
        JLabel pic = new JLabel(new ImageIcon(img));
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
        panel.add(pic);

        // Création des boutons et ajout au JLayeredPane
        playButton = new JButton("Play");
        playButton.setForeground(Color.WHITE);
        playButton.setBackground(Color.BLACK);
        playButton.setBounds(450, 600, 100, 50);
        playButton.addActionListener(this::actionPerformed);
        layeredPane.add(playButton);
        layeredPane.setLayer(playButton, JLayeredPane.DEFAULT_LAYER);

        exitButton = new JButton("Goodbye");
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(Color.RED);
        exitButton.setBounds(450, 700, 100, 50);
        exitButton.addActionListener(this::actionPerformed);
        layeredPane.add(exitButton); // Ajout du bouton "Goodbye" au-dessus de l'image et du bouton "Hello"
        layeredPane.setLayer(exitButton, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(panel);


        add(layeredPane);
        //add(helloButton);


    }
}