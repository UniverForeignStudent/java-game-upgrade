package entity;

import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class MON_BlueSlime extends Entity {

   public MON_BlueSlime(GamePanel gp) {
      super(gp);

      type = 2;
      direction = "right";
      name = "Blue Slime";
      speed = 1;
      maxLife = 5;
      life = maxLife;
      collisionBounds = new Rectangle(3, 18, 42, 42);
      collisionBoundsDefaultX = collisionBounds.x;
      collisionBoundsDefaultY = collisionBounds.y;

      getImage();
   }
   
   public void getImage(){
      try {
            File file = new File("assets/monster/slime_left1.png");
            FileInputStream img = new FileInputStream(file);
            left1 = ImageIO.read(file);
            img.close();

            file = new File("assets/monster/slime_left2.png");
            img = new FileInputStream(file);
            left2 = ImageIO.read(file);
            img.close();


            file = new File("assets/monster/slime_up1.png");
            img = new FileInputStream(file);
            up1 = ImageIO.read(file);
            img.close();
            
            file = new File("assets/monster/slime_up2.png");
            img = new FileInputStream(file);
            up2 = ImageIO.read(file);
            img.close();
            
            file = new File("assets/monster/slime_down1.png");
            img = new FileInputStream(file);
            down1 = ImageIO.read(file);
            img.close();

            file = new File("assets/monster/slime_down2.png");
            img = new FileInputStream(file);
            down2 = ImageIO.read(file);
            img.close();

            file = new File("assets/monster/slime_right1.png");
            img = new FileInputStream(file);
            right1 = ImageIO.read(img);
            img.close();

            file = new File("assets/monster/slime_right2.png");
            img = new FileInputStream(file);
            right2 = ImageIO.read(file);
            img.close();

         } catch (IOException e) {
            e.printStackTrace();
         }
   }
   public void setAction() {
        actionLockCounter++;
        
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
                            
           if (i <= 25) {
               direction = "up";
//               System.out.println("up");
           } else if (i > 25 && i <= 50) {
//            System.out.println("down");
               direction = "down";
           } else if (i > 50 && i <= 75){
//            System.out.println("left");
                direction = "left";
           } else if(i > 75 && i <=100){
//            System.out.println("right");
                direction = "right";
           }

         actionLockCounter = 0;
        }
    }
}
