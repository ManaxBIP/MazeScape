import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    MazeDisplay md;
    public int screenX;
    public int screenY;
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = 1000/2 - (gp.tileSize/2);
        screenY = 1000/2 - (gp.tileSize/2);

        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            front1 = ImageIO.read(getClass().getResourceAsStream("ressources/front1.png"));
            front2 = ImageIO.read(getClass().getResourceAsStream("ressources/front2.png"));
            front3 = ImageIO.read(getClass().getResourceAsStream("ressources/front3.png"));
            front4 = ImageIO.read(getClass().getResourceAsStream("ressources/front4.png"));
            fronthandle = ImageIO.read(getClass().getResourceAsStream("ressources/fronthandle.png"));

            behind1 = ImageIO.read(getClass().getResourceAsStream("ressources/behind1.png"));
            behind2 = ImageIO.read(getClass().getResourceAsStream("ressources/behind2.png"));
            behind3 = ImageIO.read(getClass().getResourceAsStream("ressources/behind3.png"));
            behind4 = ImageIO.read(getClass().getResourceAsStream("ressources/behind4.png"));
            behindhandle = ImageIO.read(getClass().getResourceAsStream("ressources/behindhandle.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("ressources/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("ressources/left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("ressources/left3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("ressources/left4.png"));
            lefthandle = ImageIO.read(getClass().getResourceAsStream("ressources/lefthandle.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("ressources/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("ressources/right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("ressources/right3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("ressources/right4.png"));
            righthandle = ImageIO.read(getClass().getResourceAsStream("ressources/righthandle.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void affecterMazeDp(MazeDisplay md) {
        this.md = md;
    }
    public void setDefaultValues() {
        worldX = 100;
        worldY = 100;
        speed = 4;
        direction = "down";
    }
    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            inactive = false;
            if (keyH.upPressed == true) {
                direction = "up";
                worldY -= speed;

            } else if(keyH.downPressed == true){
                direction = "down";
                worldY += speed;
            } else if(keyH.leftPressed == true) {
                direction = "left";
                worldX -= speed;
            } else if(keyH.rightPressed == true) {
                System.out.println(worldX);
                direction = "right";
                worldX += speed;
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 4;
                } else if (spriteNum == 4) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            inactive = true;
        }
    }
    public void draw(Graphics2D g2) {


        BufferedImage image = null;

        switch(direction) {
            case "up":
                if (spriteNum == 1) {
                    image=behind1;
                }
                if (spriteNum == 2) {
                    image=behind2;
                }
                if (spriteNum == 3) {
                    image=behind3;
                }
                if (spriteNum == 4) {
                    image=behind4;
                }
                if (inactive) {
                    image=behindhandle;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image=front1;
                }
                if (spriteNum == 2) {
                    image=front2;
                }
                if (spriteNum == 3) {
                    image=front3;
                }
                if (spriteNum == 4) {
                    image=front4;
                }
                if (inactive) {
                    image=fronthandle;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image=left1;
                }
                if (spriteNum == 2) {
                    image=left2;
                }
                if (spriteNum == 3) {
                    image=left3;
                }
                if (spriteNum == 4) {
                    image=left4;
                }
                if (inactive) {
                    image=lefthandle;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image=right1;
                }
                if (spriteNum == 2) {
                    image=right2;
                }
                if (spriteNum == 3) {
                    image=right3;
                }
                if (spriteNum == 4) {
                    image=right4;
                }
                if (inactive) {
                    image=righthandle;
                }
                break;
        }
        g2.drawImage(image, md.pointX-2, md.pointY-2, 4, 4, null);
    }
}
