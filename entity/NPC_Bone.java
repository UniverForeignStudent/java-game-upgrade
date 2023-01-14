package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class NPC_Bone extends Entity {
    public NPC_Bone(GamePanel gp) {
        super(gp);

        type = 1;
        direction = "1";
        speed = 2;
        collisionBounds = new Rectangle(3, 18, 42, 42);
        getImage();
        setDialogoue();
    }

    public void getImage() {
        try {
            File file = new File("assets/npc/skeleton_stand.png");
            FileInputStream img = new FileInputStream(file);
            left1 = ImageIO.read(file);
            left1 = flipHoriztal(left1);
            img.close();

            file = new File("assets/npc/skeleton_stand.png");
            img = new FileInputStream(file);
            left2 = ImageIO.read(file);
            left2 = flipHoriztal(left2);
            img.close();

            file = new File("assets/npc/skeleton_stand.png");
            img = new FileInputStream(file);
            up1 = ImageIO.read(file);
            img.close();

            file = new File("assets/npc/skeleton_stand.png");
            img = new FileInputStream(file);
            up2 = ImageIO.read(file);
            img.close();

            file = new File("assets/npc/skeleton_stand.png");
            img = new FileInputStream(file);
            down1 = ImageIO.read(file);
            img.close();

            file = new File("assets/npc/skeleton_stand.png");
            img = new FileInputStream(file);
            down2 = ImageIO.read(file);
            img.close();


            file = new File("assets/npc/skeleton_stand.png");
            img = new FileInputStream(file);
            right1 = ImageIO.read(img);
            img.close();

            file = new File("assets/npc/skeleton_stand.png");
            img = new FileInputStream(file);
            right2 = ImageIO.read(file);
            img.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setDialogoue() {
        dialogues[0] = "He He, you found me.";
        dialogues[1] = "I've been here for years.";
        dialogues[2] = "What I'm doing here?\nFinding out the difference between override,\noverwrite and overload.";
        dialogues[3] = "I believe you are smarter than me.";
        dialogues[4] = "Go for your own journey.";
    }
    public void speak() {
        if (dialogues[dialoguesCount] != null) {
            gp.ui.currentDialogue = dialogues[dialoguesCount];
        }
        dialoguesCount++;

        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }


        System.out.println(dialoguesCount);
        if(dialoguesCount > 5){
            gp.isWin = true;
            dialoguesCount = 0;
        }
    }
}
