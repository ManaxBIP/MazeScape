import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;

    public BufferedImage behind1,behind2,behind3,behind4,behindhandle,front1,front2,front3,front4,fronthandle,left1,left2,left3,left4,lefthandle,right1,right2,right3,right4,righthandle;
    public String direction;

    public boolean inactive;

    public int spriteCounter = 0;
    public int spriteNum = 1;

}
