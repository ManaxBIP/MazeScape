import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {
    private double zoom = 12.5;
    int sizeX = 15;
    int sizeY = 15;
    int cellSize = 100;
    MazeDisplay md;

    Maze m1;
    GamePanel gp;
    Tile [] tile;

    Player p1;

    MazeDisplay maze;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
        this.m1 = new Maze(sizeX,sizeY);
        this.md = new MazeDisplay(m1,gp);
        md.setScale(zoom);
    }

    public Tile getTileImage() {
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("ressources/bricktexture.png"));
            return tile[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void instancePlayer(Player p1) {
        this.p1 = p1;
        this.p1.affecterMazeDp(md);
    }
    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;
        md.doDrawing(g2);

        while (worldCol < 1000 && worldRow < 1000) {
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.playerX + 1000;
            int screenY = worldY - gp.playerY + 1000;
            worldCol++;
            if (worldCol == 1000) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
