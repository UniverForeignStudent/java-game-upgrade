package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font joystix, paskowy, four_b;
    Color textColor = new Color(255, 180, 50);
    Color bgColor = new Color(171, 213, 220);
    public int menuOption = 0;
    BufferedImage healthImage;
    BufferedImage bonesImage;
    public boolean messageOn = false;
    public String message = "";
    public String currentDialogue = "";
    int msgCount = 0;
    int subState = 0;
    int counter = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            File font = new File("assets/fonts/joystix monospace.ttf");
            FileInputStream fontStream = new FileInputStream(font);
            joystix = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            font = new File("assets/fonts/fourb.ttf");
            fontStream = new FileInputStream(font);
            four_b = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            font = new File("assets/fonts/Paskowy.ttf");
            fontStream = new FileInputStream(font);
            paskowy = Font.createFont(Font.TRUETYPE_FONT, fontStream);

            File file = new File("assets/ntiles/heart.png");
            FileInputStream img = new FileInputStream(file);
            healthImage = ImageIO.read(img);
            img.close();

            file = new File("assets/ntiles/bones1.png");
            img = new FileInputStream(file);
            bonesImage = ImageIO.read(img);
            img.close();


        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(four_b.deriveFont(Font.PLAIN, 48));
        g2.setColor(textColor);

        //title state
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        if (gp.gameState == gp.playState) {
            drawGameStateScreen();
        }

        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }

        if (gp.gameState == gp.optionsState) {
            drawOptionScreen();
        }

        if (gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }

        if (gp.gameState == gp.transitionState) {
            drawTransitionScreen();
        }

        if (gp.gameState == gp.winState) {
            drawWinScreen();
        }
    }

    private void drawDialogueScreen() {
        //window
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 18));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    private void drawGameStateScreen() {
        g2.setFont(four_b.deriveFont(Font.PLAIN, 15));
        g2.drawImage(healthImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
        g2.drawImage(bonesImage, gp.tileSize / 2 + 850, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
        g2.drawString("" + gp.player.health, 40, 40);
        g2.drawString("" + gp.player.bones, 895, 40);

        // message
        if (messageOn == true) {
            g2.setFont(g2.getFont().deriveFont(20F));
            g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);
            msgCount++;
            if (msgCount > 120) {
                msgCount = 0;
                messageOn = false;
            }
        }
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void drawTitleScreen() {
        BufferedImage[] image = new BufferedImage[10];
        //title name
        g2.setColor(bgColor);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setFont(four_b.deriveFont(Font.BOLD, 55));
        String text = "Kenney\'s Adventure";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;

        //shadow
        g2.setColor(Color.GRAY);
        g2.drawString(text, x - 5, y + 5);
        //main color
        g2.setColor(textColor);
        g2.drawString(text, x, y);

        //images
        try {
            File file = new File("assets/characters/boy_left_2.png");
            FileInputStream img = new FileInputStream(file);
            image[0] = ImageIO.read(img);
            file = new File("assets/characters/boy_left_1.png");
            img = new FileInputStream(file);
            image[1] = ImageIO.read(img);
            file = new File("assets/characters/boy_left_1.png");
            img = new FileInputStream(file);
            image[2] = ImageIO.read(img);
            x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
            y += gp.tileSize;
            g2.drawImage(image[1], x + (gp.tileSize * 2), y, gp.tileSize * 2, gp.tileSize * 2, null);
            g2.drawImage(image[2], x - (gp.tileSize * 2), y, gp.tileSize * 2, gp.tileSize * 2, null);
            g2.drawImage(image[0], x, y, gp.tileSize * 2, gp.tileSize * 2, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //menu
        g2.setFont(joystix.deriveFont(Font.PLAIN, 40));

        text = "START";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (menuOption == 0) {
            g2.drawString("+", x - (gp.tileSize), y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (menuOption == 1) {
            g2.drawString("+", x - (gp.tileSize), y);
        }
    }

    public void drawPauseScreen() {
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
    }

    public void drawOptionScreen() {
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        //sub window
        int frameX = gp.tileSize * 4;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 12;
        int frameHeight = gp.tileSize * 10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0:
                options_top(frameX, frameY);
                break;
            case 1:
                options_fScreenNotification(frameX, frameY);
                break;
            case 2:
                options_control(frameX, frameY);
                break;
            case 3:
                options_endGame(frameX, frameY);
                break;
        }

        gp.key.enter = false;
    }

    public void drawWinScreen() {
        g2.setColor(new Color(250, 236, 197));
        g2.fillRect(0, 0, gp.screenWidth2, gp.screenHeight2);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "YOU WIN!";

        x = getXforCenteredText(text);
        y = gp.tileSize * 4;

        //shadow
        g2.setColor(Color.DARK_GRAY);
        g2.drawString(text, x, y);

        g2.setColor(new Color(255, 194, 38));
        g2.drawString(text, x - 4, y - 4);

        //retry
        g2.setFont(joystix.deriveFont(Font.PLAIN, 20));


        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (menuOption == 1) {
            g2.drawString("+", x - (gp.tileSize), y);
        }
    }


    public void drawGameOverScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth2, gp.screenHeight2);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "Failed";

        x = getXforCenteredText(text);
        y = gp.tileSize * 4;

        //shadow
        g2.setColor(Color.DARK_GRAY);
        g2.drawString(text, x, y);

        g2.setColor(Color.white);
        g2.drawString(text, x - 4, y - 4);

        //retry
        g2.setFont(joystix.deriveFont(Font.PLAIN, 20));

        text = "You can play more, Press [AGAIN]";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);

        text = "[AGAIN]";
        x = getXforCenteredText(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
        if (menuOption == 0) {
            g2.drawString("+", x - (gp.tileSize), y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (menuOption == 1) {
            g2.drawString("+", x - (gp.tileSize), y);
        }
    }

    public void drawTransitionScreen() {
        counter++;
        g2.setColor(new Color(0, 0, 0, counter * 5));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        if (counter == 50) {
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eventHandler.tempMap;
            gp.player.worldX = gp.tileSize * gp.eventHandler.tempCol;
            gp.player.worldY = gp.tileSize * gp.eventHandler.tempRow;
            gp.changeArea();
        }
    }

    public void options_top(int frameX, int frameY) {
        int textX;
        int textY;

        //Title
        String text = "OPTIONS";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);


//        //FULL SCREEN ON/OFF
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
//        g2.drawString("Full Screen", textX, textY);
//        if (menuOption == 0) {
//            g2.drawString(">", textX - 25, textY);
//            if (gp.key.enter) {
//                if (!gp.fullScreenOn) {
//                    gp.fullScreenOn = true;
//                } else if (gp.fullScreenOn) {
//                    gp.fullScreenOn = false;
//                }
//                subState = 1;
//            }
//        }

        //MUSIC
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if (menuOption == 0) {
            g2.drawString(">", textX - 25, textY);
        }

        //MUSIC
        textY += gp.tileSize;
        g2.drawString("S.Effect", textX, textY);
        if (menuOption == 1) {
            g2.drawString(">", textX - 25, textY);
        }

        //CONTROL
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if (menuOption == 2) {
            g2.drawString(">", textX - 25, textY);
            if (gp.key.enter == true) {
                subState = 2;
                menuOption = 0;
            }
        }

        //QUIT GAME
        textY += gp.tileSize;
        g2.drawString("Quit", textX, textY);
        if (menuOption == 3) {
            g2.drawString(">", textX - 25, textY);
            if (gp.key.enter == true) {
                subState = 3;
                menuOption = 0;
            }
        }

        //BACK
        textY += gp.tileSize * 2;
        g2.drawString("Back", textX, textY);
        if (menuOption == 4) {
            g2.drawString(">", textX - 25, textY);
            if (gp.key.enter) {
                gp.gameState = gp.playState;
                menuOption = 0;
            }
        }

        //FULL SCREEN CHECK BOX
        textX = frameX + (int) (gp.tileSize * 8.5);
        textY = frameY + gp.tileSize + 24;
//        g2.drawRect(textX, textY, 24, 24);
//        if (gp.fullScreenOn) {
//            g2.fillRect(textX + 6, textY + 6, 13, 13);
//        }

        //MUSIC VOL
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        int volumeWidth = 24 * gp.defaultBgm.volumeScale;
        g2.fillRect(textX + 6, textY + 6, volumeWidth - 11, 13);

        //SE VOL
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gp.cursor.volumeScale;
        g2.fillRect(textX + 6, textY + 6, volumeWidth - 11, 13);

        gp.config.saveConfig();
    }

    public void options_control(int frameX, int frameY) {
        int textX;
        int textY;

        //Title
        String text = "CONTROL";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += (int) (gp.tileSize * 1.5);

        g2.drawString("Move:", textX, textY);
        textY += gp.tileSize * 2;
        g2.drawString("Confirm:", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Attack:", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Pause:", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Option:", textX, textY);

        textX = (int) (frameX + gp.tileSize * 5.5);
        textY = (int) (frameY + gp.tileSize * 2.5);
        g2.drawString("WASD", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Arrow Keys", textX, textY);
        textY += gp.tileSize;
        g2.drawString("Enter", textX, textY);
        textY += gp.tileSize;
        g2.drawString("J", textX, textY);
        textY += gp.tileSize;
        g2.drawString("P", textX, textY);
        textY += gp.tileSize;
        g2.drawString("ESC", textX, textY);
        textY += gp.tileSize;

        //back
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize * 9;
        g2.drawString("Back", textX, textY);
        if (menuOption == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.key.enter) {
                subState = 0;
                menuOption = 2;
            }
        }
    }

    public void options_endGame(int frameX, int frameY) {
        int textX;
        int textY = frameY + gp.tileSize * 4;

        currentDialogue = "Quit Game?";
        textX = getXforCenteredText(currentDialogue);
        g2.drawString(currentDialogue, textX, textY);
        //yes
        String text = "Yes";
        textX = getXforCenteredText(text);
        textY += gp.tileSize * 3;
        g2.drawString(text, textX, textY);
        if (menuOption == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.key.enter) {
                gp.resetGame();
                subState = 0;
                gp.gameState = gp.titleState;

            }
        }

        //no
        text = "No";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text, textX, textY);
        if (menuOption == 1) {
            g2.drawString(">", textX - 25, textY);
            if (gp.key.enter) {
                subState = 0;
                menuOption = 4;
            }
        }
    }

    public void options_fScreenNotification(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize * 4;

        currentDialogue = "Restart the game \nfor this action to \ntake effect.";

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }


        //back
        textY = frameY + gp.tileSize * 9;
        g2.drawString("Back", textX, textY);

        if (menuOption == 0) {
            g2.drawString(">", textX - 25, textY);
            if (gp.key.enter == true) {
                subState = 0;
            }
        }
    }

    public int getXforCenteredText(String text) {
        int len = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - len / 2;
        return x;
    }


    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 170);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

}
