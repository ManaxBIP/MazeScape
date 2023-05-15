import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {
    private Maze maze;

    public MazePanel(Maze maze) {
        this.maze = maze;
        System.out.println(maze.sizeX);
        System.out.println("Bonjour");
    }

    public void draw(Graphics2D g2d) {

        g2d.setColor(Color.blue);

        int cellWidth = getWidth() / maze.sizeX;
        int cellHeight = getHeight() / maze.sizeY;

        // Draw the cells
        for (int i = 0; i < maze.sizeX; i++) {
            for (int j = 0; j < maze.sizeY; j++) {
                int x = i * cellWidth;
                int y = j * cellHeight;

                // Draw the walls
                if (maze.cells[i][j].walls[0] == 1) { // North wall
                    g2d.drawLine(x, y, x + cellWidth, y);
                }
                if (maze.cells[i][j].walls[1] == 1) { // East wall
                    g2d.drawLine(x + cellWidth, y, x + cellWidth, y + cellHeight);
                }
                if (maze.cells[i][j].walls[2] == 1) { // South wall
                    g2d.drawLine(x, y + cellHeight, x + cellWidth, y + cellHeight);
                }
                if (maze.cells[i][j].walls[3] == 1) { // West wall
                    g2d.drawLine(x, y, x, y + cellHeight);
                }
            }
        }
    }

}
