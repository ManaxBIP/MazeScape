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

    private boolean lock1world = false;
    private boolean lock2world = true;
    private boolean lock3world = true;
    private boolean lock4world = true;
    private boolean lock1level = false;
    private boolean lock2level = true;
    private boolean lock3level = true;
    private boolean lock4level = true;

    private String worldnames = "";

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
        JLayeredPane layeredPane = new JLayeredPane();
        PictureLabel pictureLabel = new PictureLabel();
        panel.setPreferredSize(new Dimension(pictureLabel.getWidth(), pictureLabel.getHeight()));

        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 32);
        Color textColor = Color.BLACK;
        Color backgroundColor = Color.WHITE;

        int bubbleWidth = 400;
        int bubbleHeight = 40;

        bubblePanel1 = createBubblePanel(text, font, textColor, backgroundColor, bubbleWidth, bubbleHeight);
        bubblePanel1.setBounds(300, 562, bubbleWidth, bubbleHeight);
        layeredPane.add(bubblePanel1);
        bubblePanel2 = createBubblePanel(text2, font, textColor, backgroundColor, bubbleWidth, bubbleHeight);
        bubblePanel2.setBounds(300, 612, bubbleWidth, bubbleHeight);
        layeredPane.add(bubblePanel2);

        layeredPane.add(panel);
        panel.add(pictureLabel);

        add(layeredPane);

        setVisible(true);
        updateCursor();
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
            remove(getLayeredPane());
            openWorldPage();
            revalidate();
            repaint();
        } else if (commandNum == 1 && e.getKeyCode() == KeyEvent.VK_ENTER) {
            dispose();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e){
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

    private void openWorldPage() {
        bubblePanel1.setVisible(false);
        bubblePanel2.setVisible(false);

        JPanel worldPanel = new JPanel();
        worldPanel.setLayout(new GridLayout(2, 2));

        JPanel world1Panel = createWorldPanel("World 1", "world1.png", lock1world);
        JPanel world2Panel = createWorldPanel("World 2", "world2.png", lock2world);
        JPanel world3Panel = createWorldPanel("World 3", "world3.png", lock3world);
        JPanel world4Panel = createWorldPanel("World 4", "world4.png", lock4world);

        worldPanel.add(world1Panel);
        worldPanel.add(world2Panel);
        worldPanel.add(world3Panel);
        worldPanel.add(world4Panel);

        getContentPane().removeAll();
        getContentPane().add(worldPanel);

        revalidate();
        repaint();
    }

    private JPanel createWorldPanel(String worldName, String imageFileName, boolean locked) {
        JPanel worldPanel = new JPanel();
        worldPanel.setLayout(new BorderLayout());

        ImageIcon imageIcon = new ImageIcon(imageFileName);
        Image originalImage = imageIcon.getImage();

        int scaledWidth;
        int scaledHeight;
        if (locked) {
            scaledWidth = originalImage.getWidth(null) / 3;
            scaledHeight = originalImage.getHeight(null) / 3;
        } else {
            scaledWidth = originalImage.getWidth(null) / 3;
            scaledHeight = originalImage.getHeight(null) / 3;
        }

        Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);

        if (locked) {
            ImageIcon cadenaIcon;
            if (worldName.equals("World 1")) {
                cadenaIcon = new ImageIcon("world1lock.png");
            } else if (worldName.equals("World 2")) {
                cadenaIcon = new ImageIcon("world2lock.png");
            } else if (worldName.equals("World 3")) {
                cadenaIcon = new ImageIcon("world3lock.png");
            } else if (worldName.equals("World 4")) {
                cadenaIcon = new ImageIcon("world4lock.png");
            } else {
                cadenaIcon = new ImageIcon("cad.png");
            }
            int scaledWidth2 = cadenaIcon.getIconWidth() / 3;
            int scaledHeight2 = cadenaIcon.getIconHeight() / 3;

            Image originalCadenaImage = cadenaIcon.getImage();
            Image scaledCadenaImage = originalCadenaImage.getScaledInstance(scaledWidth2, scaledHeight2, Image.SCALE_SMOOTH);
            ImageIcon scaledCadenaIcon = new ImageIcon(scaledCadenaImage);

            JLabel cadenaLabel = new JLabel(scaledCadenaIcon);
            worldPanel.add(cadenaLabel, BorderLayout.CENTER);
        } else {
            imageLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    remove(getLayeredPane());
                    openLevelPage();
                    revalidate();
                    repaint();
                    System.out.println("Opening " + worldName + "...");
                    worldnames = worldName;
                }
            });

            worldPanel.add(imageLabel, BorderLayout.CENTER);
        }

        return worldPanel;
    }



    private void openLevelPage() {
        JPanel levelPanel = new JPanel();
        levelPanel.setLayout(new GridLayout(2, 2));

        JPanel level1Panel = createLevelPanel("Level 1", "level.png", lock1level,worldnames);
        JPanel level2Panel = createLevelPanel("Level 2", "level.png", lock2level,worldnames);
        JPanel level3Panel = createLevelPanel("Level 3", "level.png", lock3level,worldnames);
        JPanel level4Panel = createLevelPanel("Level 4", "level.png", lock4level,worldnames);

        levelPanel.add(level1Panel);
        levelPanel.add(level2Panel);
        levelPanel.add(level3Panel);
        levelPanel.add(level4Panel);

        getContentPane().removeAll();
        getContentPane().add(levelPanel);

        revalidate();
        repaint();
    }

    private JPanel createLevelPanel(String levelName, String imageFileName, boolean locked, String worldName) {
        JPanel levelPanel = new JPanel();
        levelPanel.setLayout(new BorderLayout());

        ImageIcon imageIcon = new ImageIcon(imageFileName);

        int scaledWidth = (int) (imageIcon.getIconWidth()/1.5);;
        int scaledHeight = (int) (imageIcon.getIconHeight()/1.5);;

        Image originalImage = imageIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel scaledImageLabel = new JLabel(scaledIcon);

        if (locked) {
            ImageIcon cadenaIcon;
            cadenaIcon = new ImageIcon("levellock.png");

            int scaledWidth2 = (int) (cadenaIcon.getIconWidth()/1.5);
            int scaledHeight2 = (int) (cadenaIcon.getIconHeight()/1.5);

            Image originalCadenaImage = cadenaIcon.getImage();
            Image scaledCadenaImage = originalCadenaImage.getScaledInstance(scaledWidth2, scaledHeight2, Image.SCALE_SMOOTH);
            ImageIcon scaledCadenaIcon = new ImageIcon(scaledCadenaImage);

            JLabel cadenaLabel = new JLabel(scaledCadenaIcon);
            levelPanel.add(cadenaLabel, BorderLayout.CENTER);
        } else {
            levelPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Opening " + levelName + "...");
                    if (levelName == "Level 1" && worldName == "World 1"){
                        //ICI DALYL FAUT QUE TU METTE LE NIVEAU
                        //if (level == finish){
                        //lock2level = false;
                        // }
                    }
                }
            });

            levelPanel.add(scaledImageLabel, BorderLayout.CENTER);
        }

        JLabel nameLabel = new JLabel(levelName);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        levelPanel.add(nameLabel, BorderLayout.NORTH);

        return levelPanel;
    }


}

