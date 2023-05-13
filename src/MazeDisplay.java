import java.awt.Color ;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Insets ;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MazeDisplay extends JPanel {
    Maze m1;

    double scale;
    int offsetX = 10;
    int offsetY = 10;
    int cellSize = 20;
    Tile brick = new Tile();
    int pointX, pointY, oldX, oldY;
    boolean erase;

    GamePanel gp;


    public MazeDisplay(GamePanel gp){
        this.gp = gp;
        m1 = new Maze();
        pointX = offsetX + cellSize / 2;
        pointY = offsetY + cellSize / 2;
        oldX = pointX;
        oldY = pointY;
        gp.setLocation(400,400);
    }

    public MazeDisplay(Maze m2, GamePanel gp){
        this.gp = gp;
        this.m1 = m2;
        pointX = offsetX + cellSize / 2;
        pointY = offsetY + cellSize / 2;
        oldX = pointX;
        oldY = pointY;
    }

    MazeDisplay(Maze m2, int cellSize2, GamePanel gp){
        this.gp = gp;
        m1 = m2;
        cellSize = cellSize2;
        pointX = offsetX + cellSize / 2;
        pointY = offsetY + cellSize / 2;
        oldX = pointX;
        oldY = pointY;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public void doDrawing(Graphics2D g2d){
        try {
            brick.image = ImageIO.read(getClass().getResourceAsStream("ressources/bricktexture.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AffineTransform transform = new AffineTransform();
        transform.scale(scale,scale);
        g2d.setTransform(transform);
        g2d.setColor(Color.blue);

        Dimension size = getSize();
        Insets insets = getInsets();

        int w = size.width - insets.left - insets.right;
        int h = size.height - insets.top - insets.bottom;

        g2d.clearRect(0,0, w, h);

        Path2D mazeShape = new Path2D.Double();
        int x, y;
        for(Integer i =0;i<m1.sizeX;i++){
            x = i* cellSize+offsetX;
            for (Integer j=0;j<m1.sizeY;j++){
                y = j*cellSize+offsetY;

                if(m1.cells[i][j].walls[0] == 1){
                    //mazeShape.moveTo(x, y);
                    //mazeShape.lineTo(x + cellSize, y);
                    //g2d.drawLine(x, y, x + cellSize, y);
                    g2d.drawImage(brick.image, x, y, cellSize, 5, null);
                }
                if(m1.cells[i][j].walls[1] == 1){
                    g2d.drawImage(brick.image, x + cellSize - 5, y, 5, cellSize, null);
                }
                if(m1.cells[i][j].walls[2] == 1){
                    g2d.drawImage(brick.image, x, y + cellSize - 5, cellSize, 5, null);
                }
                if(m1.cells[i][j].walls[3] == 1){
                    g2d.drawImage(brick.image, x, y, 5, cellSize, null);
                }
            }
        }

        x = (oldX - offsetX -cellSize / 2) / cellSize;
        y = (oldY - offsetY -cellSize/2) / cellSize;

        if(x >= 0 && x < m1.sizeX && oldX > pointX && m1.cells[x][y].walls[3] == 1){
            pointX = oldX;
            pointY = oldY;
        } else if(x >= 0 && x < m1.sizeX && oldX < pointX && m1.cells[x][y].walls[1] == 1){
            pointX = oldX;
            pointY = oldY;
        } else if(y >= 0 && y < m1.sizeY && oldY > pointY && m1.cells[x][y].walls[0] == 1){
            pointX = oldX;
            pointY = oldY;
        } else if(y >= 0 && y < m1.sizeY && oldY < pointY && m1.cells[x][y].walls[2] == 1){
            pointX = oldX;
            pointY = oldY;
        }

    }

}