import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    public int FPS = 60;
    final int originalTileSize = 5;

    final int scale = 5;

    public final int tileSize = originalTileSize * scale;

    Thread gameThread;

    KeyHandler keyH = new KeyHandler();
    Player player = new Player(this, keyH);

    TileManager tileM = new TileManager(this);

    int playerX = 100;
    int playerY = 100;

    int playerSpeed = 4;

    //WORLD SETTINGS

    public int maxWorldCol = 50;
    public int maxWorldRow = 50;
    public int worldWidth = tileSize * maxWorldCol;
    public int worldHeight = tileSize * maxWorldRow;


    public GamePanel() {
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.requestFocus();
        tileM.instancePlayer(player);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        while(gameThread != null) {
            double drawInterval = 1000000000/FPS;
            double nextDrawTime = System.nanoTime() + drawInterval;
            //1 Update character positions
            update();
            //2 Draw the screen with updated informations
            repaint();
            //3

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread .sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update() {
        player.update();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
