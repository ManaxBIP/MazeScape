import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WorldPanel extends JPanel {
    private String worldName;
    private String imageFileName;
    private boolean locked;

    public WorldPanel(String worldName, String imageFileName, boolean locked) {
        this.worldName = worldName;
        this.imageFileName = imageFileName;
        this.locked = locked;

        setLayout(new BorderLayout());

        // Create the image label
        ImageIcon imageIcon = new ImageIcon(imageFileName);
        JLabel imageLabel = new JLabel(imageIcon);

        // Check if the world is locked
        if (locked) {
            // World is locked, add the cadena image
            ImageIcon cadenaIcon = new ImageIcon("cad.png");
            JLabel cadenaLabel = new JLabel(cadenaIcon);
            imageLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle click event for locked world
                    System.out.println("This world is locked!");
                }
            });
            add(cadenaLabel, BorderLayout.CENTER);
        } else {
            // World is unlocked, add the image label
            imageLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Handle click event for unlocked world
                    System.out.println("Opening " + worldName + "...");
                }
            });
            add(imageLabel, BorderLayout.CENTER);
        }
    }
}
