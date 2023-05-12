
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class PictureLabel extends JLabel {
    public BufferedImage img;
    private float alpha;

    public PictureLabel() throws IOException {
        setBackground(Color.black);
        img = ImageIO.read(new File("home.png"));
        setIcon(new ImageIcon(img));
        alpha = 1.0f; // Initial opacity
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        g2d.dispose();
    }

    static Timer timer;

    public void fadeOut(Runnable callback) {
        timer = new Timer(1, e -> {
            alpha -= 0.01; // Set a lower opacity value (0.5 for 50% opacity)
            if (alpha <= 0) {
                alpha = 0;
                timer.stop();
                callback.run();
            }
            repaint();
        });
        timer.start();
    }

    public void fadeIn() {
        if (alpha > 0) {
            alpha = 0;
            repaint();
        }
        timer = new Timer(1, e -> {
            alpha += 0.01;
            if (alpha >= 1) {
                alpha = 1;
                timer.stop();
            }
            repaint();
        });
        timer.start();
    }
}