package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class NPC_OldMan extends Entity {
    public NPC_OldMan(GamePanel gp) {
        super(gp);

        type = 1;
        direction = "right";
        speed = 2;
        collisionBounds = new Rectangle(3, 18, 42, 42);
        getImage();
        setDialogoue();
    }

    public void getImage() {
        try {
            File file = new File("assets/npc/oldman_left_1.png");
            FileInputStream img = new FileInputStream(file);
            left1 = ImageIO.read(file);
            img.close();

            file = new File("assets/npc/oldman_left_2.png");
            img = new FileInputStream(file);
            left2 = ImageIO.read(file);
            img.close();
            
            file = new File("assets/npc/oldman_up_1.png");
            img = new FileInputStream(file);
            up1 = ImageIO.read(file);
            img.close();
            
            file = new File("assets/npc/oldman_up_2.png");
            img = new FileInputStream(file);
            up2 = ImageIO.read(file);
            img.close();

            file = new File("assets/npc/oldman_down_1.png");
            img = new FileInputStream(file);
            down1 = ImageIO.read(file);
            img.close();

            file = new File("assets/npc/oldman_down_2.png");
            img = new FileInputStream(file);
            down2 = ImageIO.read(file);
            img.close();


            file = new File("assets/npc/oldman_right_1.png");
            img = new FileInputStream(file);
            right1 = ImageIO.read(img);
            img.close();

            file = new File("assets/npc/oldman_right_2.png");
            img = new FileInputStream(file);
            right2 = ImageIO.read(file);
            img.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDialogoue(){
        dialogues[0] = "Hello, kid.";
        dialogues[1] = "So you wish to duel?";
        dialogues[2] = "Alas, your powers are too weak.";
        dialogues[3] = "Collect bones to gain strength.";
        dialogues[4] = "Find the chest.\nOpen the chest to find a key.";
        if(gp.haveKey == true){
            System.out.println("key");
            dialogues[0] = "I see you found a key, kiddo.\nFind a door to fight the invisible\nskeleton guy.";
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

    public void speak(){
        //character specific stuff
       super.speak(); 
    }


}

