package object;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.GamePanel;

public class SuperObject {
   BufferedImage image;
   BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
   public String name;
   public boolean collision = false;
   public int worldX, worldY;  
   public Rectangle collisionBounds = new Rectangle(0,0,40,40);
   public int collisionBoundsDefaultX = 0;
   public int collisionBoundsDefaultY = 0;



   public void draw(Graphics2D g2, GamePanel gp){
      int screenX = worldX - gp.player.worldX + gp.player.screenX;
      int screenY = worldY - gp.player.worldY + gp.player.screenY;

      if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&     worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
      }
   }
}
