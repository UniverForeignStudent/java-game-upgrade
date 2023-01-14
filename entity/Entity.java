package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

public class Entity {
    GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public boolean invincible = false;

    //npc
    public BufferedImage idle, up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    String dialogues[] = new String[20];


    //attack
    public BufferedImage attack_up, attack_down, attack_left, attack_right;
    public boolean attacking = false;
    public Rectangle attackBounds = new Rectangle(0, 0, 0, 0);

    //collisions

    public Rectangle collisionBounds;
    public int collisionBoundsDefaultX, collisionBoundsDefaultY;
    public boolean collisionOn = false;

    //counter
    public int actionLockCounter = 0;
    public int invincibleCount = 0;
    public int dialoguesCount = 0;
    public int spriteCount = 0;
    public int spriteNum = 1;
    int dyingCounter = 0;

    //player's life
    public int maxLife;
    public int life;

    //object
    public String name;
    public int attack;
    public boolean alive = true;
    boolean dying = false;
    boolean hpBarOn = false;
    int hpBarCounter = 0;
    public int type; //0 = player 1 = npc 2 = monster

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public static BufferedImage flipHoriztal(BufferedImage image) {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D gg = newImage.createGraphics();
        gg.drawImage(image, image.getHeight(), 0, -image.getWidth(), image.getHeight(), null);
        gg.dispose();
        return newImage;
    }

    //draw npc
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    break;
                case "idle":
                default:
                    image = idle;
                    break;
            }

            //monster hp
            if (type == 2 && hpBarOn == true) {
                double oneScale = (double) gp.tileSize / maxLife;
                double hpBarValue = oneScale * life;
                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX - 1, screenY - 15, gp.tileSize + 2, 12);
                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 14, (int) hpBarValue, 10);

                hpBarCounter++;

                if (hpBarCounter > 600) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

            if (invincible) {
                hpBarOn = true;
                hpBarCounter = 0;
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }


    public void setAction() {
    }


    public void speak() {
        if (dialogues[dialoguesCount] != null) {
            gp.ui.currentDialogue = dialogues[dialoguesCount++];
        } else dialoguesCount = 0;

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

    }

    public void update() {
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (collisionOn == false) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        if (this.type == 2 && contactPlayer == true) {
            if (gp.player.invincible == false) {
                //give damage
                gp.player.health -= 10;
                gp.player.invincible = true;
            }
        }

        spriteCount++;
        if (spriteCount > 10) {      //player image changes every 10 frames
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCount = 0;
        }

        if (invincible == true) {
            invincibleCount++;
            if (invincibleCount > 40) {
                invincible = false;
                invincibleCount = 0;
            }
        }

    }

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            File file = new File(imagePath);
            FileInputStream img = new FileInputStream(file);
            image = ImageIO.read(img);
            image = utilityTool.scaleImage(image, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }


}