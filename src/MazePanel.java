import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


public class MazePanel extends JPanel {
    private Maze maze;

    public MazePanel(Maze maze) {
        this.maze = maze;
        setPreferredSize(new Dimension(0, 0)); // taille arbitraire pour le JPanel
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Dessiner le labyrinthe
        /*for (int i = 0; i < maze.getWidth(); i++) {
            for (int j = 0; j < maze.getHeight(); j++) {
                if (maze.isWall(i, j)) {
                    g2d.setColor(Color.BLACK);
                    g2d.fillRect(i * 10, j * 10, 10, 10); // dessiner un carré noir pour chaque mur
                }
            }
        }*/
    }
}