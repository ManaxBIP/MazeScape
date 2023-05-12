import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class MaFenetre extends JFrame implements KeyListener {

    private String text = "PLAY GAME";
    private String text2 = "QUIT GAME";
    public int commandNum = 0;
    private JPanel bubblePanel1;
    private JPanel bubblePanel2;

    public static void main(String[] args) throws IOException {
        MaFenetre fenetre = new MaFenetre();
        fenetre.setVisible(true);
        fenetre.setResizable(false);
    }

    public MaFenetre() throws IOException {
        super("MazeScape");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addKeyListener(this);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1000, 1000);
        //BufferedImage img = ImageIO.read(new File("home.png"));
//        panel.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
//        JLabel pic = new JLabel(new ImageIcon(img));
        JLayeredPane layeredPane = new JLayeredPane();
//        layeredPane.setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
        PictureLabel pictureLabel = new PictureLabel();
        //picturePanel.add(pic);
        panel.setPreferredSize(new Dimension(pictureLabel.getWidth(), pictureLabel.getHeight()));

        // Création des bulles blanches et ajout au JLayeredPane

        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 32);
        Color textColor = Color.BLACK;
        Color backgroundColor = Color.WHITE;

        int bubbleWidth = 400;
        int bubbleHeight = 40;

        // Bulle "PLAY GAME"
        bubblePanel1 = createBubblePanel(text, font, textColor, backgroundColor, bubbleWidth, bubbleHeight);
        bubblePanel1.setBounds(300, 562, bubbleWidth, bubbleHeight);
        layeredPane.add(bubblePanel1);

        // Bulle "QUIT GAME"
        bubblePanel2 = createBubblePanel(text2, font, textColor, backgroundColor, bubbleWidth, bubbleHeight);
        bubblePanel2.setBounds(300, 612, bubbleWidth, bubbleHeight);
        layeredPane.add(bubblePanel2);

        layeredPane.add(panel);
        panel.add(pictureLabel);

        add(layeredPane);

        setVisible(true);
        updateCursor();
//        pictureLabel.fadeOut(()->{});
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            commandNum--;
            if (commandNum < 0) {
                commandNum = 1;
            }
            updateCursor();
        } else if (code == KeyEvent.VK_DOWN) {
            commandNum++;
            if (commandNum > 1) {
                commandNum = 0;
            }
            updateCursor();
        }
        if (commandNum == 0 && e.getKeyCode() == KeyEvent.VK_ENTER) {
            JLayeredPane layeredPane = getLayeredPane();
            layeredPane.removeAll();
            layeredPane.revalidate();
            layeredPane.repaint();
        } else if (commandNum == 1 && e.getKeyCode() == KeyEvent.VK_ENTER) {
            dispose();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void updateCursor() {
        JLayeredPane layeredPane = getLayeredPane();
        layeredPane.remove(bubblePanel1);
        layeredPane.remove(bubblePanel2);

        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 32);
        Color textColor = Color.BLACK;
        Color borderColor = Color.BLACK;
        int borderWidth = 7;
        int bubbleWidth = 400;
        int bubbleHeight = 40;

        if (commandNum == 0) {
            bubblePanel1 = createBorderedPanel(text, font, textColor, borderColor, borderWidth, bubbleWidth, bubbleHeight);
            bubblePanel1.setBounds(300, 562, bubbleWidth, bubbleHeight);
            layeredPane.add(bubblePanel1);
        } else if (commandNum == 1) {
            bubblePanel2 = createBorderedPanel(text2, font, textColor, borderColor, borderWidth, bubbleWidth, bubbleHeight);
            bubblePanel2.setBounds(300, 612, bubbleWidth, bubbleHeight);
            layeredPane.add(bubblePanel2);
        }

        layeredPane.revalidate();
        layeredPane.repaint();
    }

    private JPanel createBubblePanel(String text, Font font, Color textColor, Color backgroundColor, int bubbleWidth, int bubbleHeight) {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(backgroundColor);
                g2d.fillRoundRect(0, 0, bubbleWidth, bubbleHeight, 0, 0);
                g2d.setColor(textColor);
                g2d.setFont(font);
                FontMetrics fontMetrics = g2d.getFontMetrics();
                int textWidth = fontMetrics.stringWidth(text);
                int textHeight = fontMetrics.getHeight();
                int x = (bubbleWidth - textWidth) / 2;
                int y = (bubbleHeight - textHeight) / 2 + fontMetrics.getAscent();
                g2d.drawString(text, x, y);
                g2d.dispose();
            }
        };
    }

    private JPanel createBorderedPanel(String text, Font font, Color textColor, Color borderColor, int borderWidth, int bubbleWidth, int bubbleHeight) {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(borderColor);
                g2d.setStroke(new BasicStroke(borderWidth));
                g2d.drawRect(0, 0, bubbleWidth, bubbleHeight);
                g2d.setColor(textColor);
                g2d.setFont(font);
                FontMetrics fontMetrics = g2d.getFontMetrics();
                int textWidth = fontMetrics.stringWidth(text);
                int textHeight = fontMetrics.getHeight();
                int x = (bubbleWidth - textWidth) / 2;
                int y = (bubbleHeight - textHeight) / 2 + fontMetrics.getAscent();
                g2d.drawString(text, x, y);
                g2d.dispose();
            }
        };
    }
}
