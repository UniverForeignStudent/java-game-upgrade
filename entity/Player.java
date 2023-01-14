package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    KeyHandler key;

    public final int screenX;
    public final int screenY;

    public int bones = 0;
    final int initialBones = 0;
    public int initialHealth = 100;
    public int health = 100;

    public Player(GamePanel gp, KeyHandler key) {
        super(gp);
        this.key = key;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2;

        collisionBounds = new Rectangle(22, 32, 2, 2);
        collisionBoundsDefaultX = collisionBounds.x;
        collisionBoundsDefaultY = collisionBounds.y;

        attackBounds.width = gp.tileSize;
        attackBounds.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 30;
        worldY = gp.tileSize * 34;
        speed = 5;
        direction = "down";

    }

    public void restore() {
        health = initialHealth;
        bones = initialBones;
    }


    public void getPlayerImage() {
        up1 = setup("assets/characters/boy_up_1.png", gp.tileSize, gp.tileSize);
        up2 = setup("assets/characters/boy_up_2.png", gp.tileSize, gp.tileSize);
        down1 = setup("assets/characters/boy_down_1.png", gp.tileSize, gp.tileSize);
        down2 = setup("assets/characters/boy_down_2.png", gp.tileSize, gp.tileSize);
        left1 = setup("assets/characters/boy_left_1.png", gp.tileSize, gp.tileSize);
        left2 = setup("assets/characters/boy_left_2.png", gp.tileSize, gp.tileSize);
        right1 = setup("assets/characters/boy_right_1.png", gp.tileSize, gp.tileSize);
        right2 = setup("assets/characters/boy_right_2.png", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttackImage() {
        attack_up = setup("assets/characters/attack/fireball_up_1.png", gp.tileSize, gp.tileSize);
        attack_down = setup("assets/characters/attack/fireball_down_1.png", gp.tileSize, gp.tileSize);
        attack_left = setup("assets/characters/attack/fireball_left_1.png", gp.tileSize, gp.tileSize);
        attack_right = setup("assets/characters/attack/fireball_right_1.png", gp.tileSize, gp.tileSize);
    }

    public void attacking() {
        int currentWorldX = worldX;
        int currentWorldY = worldY;
        int solidAreaWidth = collisionBounds.width;
        int solidAreaHeight = collisionBounds.height;

        //adjust attack area;
        switch (direction){
            case  "up":
                worldY -= attackBounds.height;
                break;
            case "down":
                worldY += attackBounds.height;
                break;
            case "left":
                worldX -= attackBounds.width;
                break;
            case "right":
                worldY += attackBounds.width;
                break;
        }

        collisionBounds.width = attackBounds.width;
        collisionBounds.height = attackBounds.height;

        //check collision
        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
        damageMonster(monsterIndex);
        worldX = currentWorldX;
        worldY = currentWorldY;
        collisionBounds.width = solidAreaWidth;
        collisionBounds.height = solidAreaHeight;

    }

    public void update() {
        if (attacking) {
            attacking();
        } else if (key.up || key.down || key.right || key.left) {
            if (key.up) {
                direction = "up";
                // System.out.println("up");
            } else if (key.down) {
                direction = "down";
                // System.out.println("down");
            } else if (key.left) {
                direction = "left";
                // System.out.println("left");
            } else if (key.right) {
                direction = "right";
                //System.out.println("right");
            }

            //CHECK COLLISIONS
            collisionOn = false;
            gp.cChecker.checkTile(this);

            gp.key.enter = false;

            //CHECK OBJECT COLLISIONS
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //if collision false then player can move
            if (!collisionOn) {
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

            spriteCount++;
            if (spriteCount > 10) {      //player image changes every 10 frames
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCount = 0;
            }

            if (health <= 0) {
                gp.gameState = gp.gameOverState;
            }
        }

        if (invincible == true) {
            invincibleCount++;
            if (invincibleCount > 60) {
                invincible = false;
                invincibleCount = 0;
            }
        }
    }

    private void contactMonster(int i) {
        if (i != 999) {
            if (invincible == false) {
                health -= 20;
                invincible = true;
            }
        }
    }

    private void interactNPC(int i) {
        if (i != 999) {
            gp.gameState = gp.dialogueState;
            gp.npc[gp.currentMap][i].speak();
        }
    }

    public void damageMonster(int i) {
        if (i != 999) {
            if (gp.monster[gp.currentMap][i].invincible == false) {
                gp.monster[gp.currentMap][i].life -= 1;
                gp.monster[gp.currentMap][i].invincible = true;

                if (gp.monster[gp.currentMap][i].life <= 0) {
                    gp.monster[gp.currentMap][i] = null;
                    bones += 5;
                    gp.ui.showMessage("Bones +5");
                }
            }
        }
    }


    public void pickUpObject(int i) {
        if (i != 999) {
            String objName = gp.obj[gp.currentMap][i].name;

            switch (objName) {
                case "Heart":
                    health += 50;
                    gp.gotBone.start();
                    gp.obj[gp.currentMap][i] = null;
                    gp.ui.showMessage("Health +50");
                    break;
                case "Bones":
                    bones += 10;
                    gp.gotBone.start();
                    gp.obj[gp.currentMap][i] = null;
                    gp.ui.showMessage("Bones +10");
                    break;
                case "Chest":
                    if (gp.currentMap == gp.outside) {
                    if(this.bones >= 100){
                            bones -= 100;
                            gp.gameState = gp.dialogueState;
                            gp.ui.currentDialogue = "You got a key! Go find the door now!";
                            gp.haveKey = true;
                            gp.obj[gp.currentMap][i] = null;
                        } else gp.ui.showMessage("not enough bones");
                    }
                    break;
                case "Door":
                    if (gp.haveKey) {
                        if (gp.currentMap == 1) {
                            gp.eventHandler.teleport(0, 40, 52, gp.outside);
                            gp.player.direction = "down";

                        } else if (gp.currentMap == 0) {
                            gp.eventHandler.teleport(1, 31, 54, gp.dungeon);
                            gp.player.direction = "up";
                        }
                    } else {
                        gp.ui.showMessage("you have no key");
                    }
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        BufferedImage image2 = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }

                if (attacking) {
                    tempScreenY = screenY - gp.tileSize;
                    image2 = attack_up;
                }
                break;
            case "down":

                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }


                if (attacking) {
                    tempScreenY = screenY + gp.tileSize;
                    image2 = attack_down;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }

                if (attacking) {
                    tempScreenX = screenX - gp.tileSize;
                    image2 = attack_left;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }

                if (attacking) {
                    tempScreenX = screenX + gp.tileSize;
                    image2 = attack_right;
                }
                break;
        }

        if (invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        }

        g2.drawImage(image, gp.player.screenX, gp.player.screenY, gp.tileSize, gp.tileSize, null);
        g2.drawImage(image2, tempScreenX, tempScreenY, gp.tileSize, gp.tileSize, null);
        //reset opacity
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

}
