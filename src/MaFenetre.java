import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MaFenetre extends JFrame {

    private JButton helloButton;
    private JButton goodbyeButton;


    public static void main(String[] args) throws IOException {

        MaFenetre fenetre = new MaFenetre();
        fenetre.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == helloButton) {
            JOptionPane.showMessageDialog(null, "Bonjour !");
        } else if (e.getSource() == goodbyeButton) {
            JOptionPane.showMessageDialog(null, "Au revoir !");
        }
    }
    public MaFenetre() throws IOException {
        super("Maze");
        setSize(1000,1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JFrame f = new JFrame("Ajouter une image dans JPanel");
        JPanel panel = new JPanel();
        panel.setBounds(50, 50, 250, 250);
        BufferedImage img = ImageIO.read(new File("home.png"));
        JLabel pic = new JLabel(new ImageIcon(img));
        panel.add(pic);

        helloButton = new JButton("Hello");
        helloButton.addActionListener( this::actionPerformed);
        goodbyeButton = new JButton("Au revoir");
        goodbyeButton.addActionListener((ActionListener) this::actionPerformed);

        add(helloButton, BorderLayout.NORTH);
        add(goodbyeButton, BorderLayout.SOUTH);
    }
}