package main;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
public class PassUI {
   GamePanel gp;
   Graphics2D g2;
   Font joystix, paskowy, four_b;
   Color textColor = new Color(242, 203, 97);
   Color bgColor = new Color(250, 236, 197);
   public int menuOption = 0;

   public PassUI(GamePanel gp) {
      this.gp = gp;
     
      try{
         File font = new File("assets/fonts/joystix monospace.ttf");
         FileInputStream fontStream = new FileInputStream(font);
         joystix = Font.createFont(Font.TRUETYPE_FONT, fontStream);
         font = new File("assets/fonts/fourb.ttf");
         fontStream = new FileInputStream(font);
         four_b = Font.createFont(Font.TRUETYPE_FONT, fontStream);
         font = new File("assets/fonts/Paskowy.ttf");
         fontStream = new FileInputStream(font);
         paskowy = Font.createFont(Font.TRUETYPE_FONT, fontStream);

      } catch (IOException | FontFormatException e){
         e.printStackTrace();
      }
      
      
   }
   public void draw(Graphics2D g2){
      this.g2 = g2;
      g2.setFont(four_b.deriveFont(Font.PLAIN, 48));
      g2.setColor(textColor); 

      //title state
      if(gp.gameState == gp.titleState){
         drawTitleScreen();
      }

      if(gp.gameState == gp.playState){
         // todo
      }

      if(gp.gameState == gp.pauseState){
         drawPauseScreen();
      }
   }

   public void drawTitleScreen(){
      BufferedImage[] image = new BufferedImage[10];
      //title name
      g2.setColor(bgColor);
      g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
      g2.setFont(four_b.deriveFont(Font.BOLD, 96));
      String text = "Passed";
      int x = getXforCenteredText(text);
      int y = gp.tileSize *3;

      //shadow
      g2.setColor(Color.yellow);
      g2.drawString(text, x-5, y+5);
      //main color
      g2.setColor(textColor);
      g2.drawString(text, x, y);

      //images
      try {
         File file = new File("assets/characters/jump.png");
         FileInputStream img = new FileInputStream(file);
         image[0] = ImageIO.read(img);
         file = new File("assets/characters/idle.png");
         img = new FileInputStream(file);
         image[1] = ImageIO.read(img);
         file = new File("assets/characters/duck.png");
         img = new FileInputStream(file);
         image[2] = ImageIO.read(img);
         x = gp.screenWidth/2 - (gp.tileSize * 2) / 2;
         y += gp.tileSize;
         g2.drawImage(image[1], x + (gp.tileSize * 2), y, gp.tileSize*2, gp.tileSize*2, null);
         g2.drawImage(image[2], x - (gp.tileSize * 2), y, gp.tileSize*2, gp.tileSize*2, null);
         g2.drawImage(image[0], x, y, gp.tileSize*2, gp.tileSize*2, null);
      } catch (IOException e) {
         e.printStackTrace();
      }

      //menu
      g2.setFont(joystix.deriveFont(Font.PLAIN, 20));
      
      text = "SCORE: ";
      x = getXforCenteredText(text);
      y += gp.tileSize*4;
      g2.drawString(text, x, y);
      
      text = "Congratulations!\nPress [Go!] for the next stage!";
      x = getXforCenteredText(text);
      y += gp.tileSize;
      g2.drawString(text, x, y);
      
      text = "[Go!]";
      x = getXforCenteredText(text);
      y += gp.tileSize;
      g2.drawString(text, x, y);
      if(menuOption == 0){
         g2.drawString("+", x - (gp.tileSize), y);
      }
      
      text = "QUIT";
      x = getXforCenteredText(text);
      y += gp.tileSize;
      g2.drawString(text, x, y);
      if(menuOption == 1){
         g2.drawString("+", x - (gp.tileSize), y);
      }
   }

   public void drawAboutScreen(){
      menuOption = 0;
      g2.setColor(bgColor);
      g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
      g2.setFont(four_b.deriveFont(Font.BOLD, 96));
      String text = "Kenney";
      int x = getXforCenteredText(text);
      int y = gp.tileSize *3;
      
      //shadow
      g2.setColor(Color.GRAY);
      g2.drawString(text, x-5, y+5);
      //main color
      g2.setColor(textColor);
      g2.drawString(text, x, y);

      //back option
      g2.setFont(joystix.deriveFont(Font.PLAIN, 40));
      
      text = "BACK";
      x = getXforCenteredText(text);
      y += gp.tileSize*4;
      g2.drawString(text, x, y);
      if(menuOption == 0){
         g2.drawString("+", x - (gp.tileSize), y);
      }
      
}

   public void drawPauseScreen(){
      String text = "PAUSED";
      int x = getXforCenteredText(text);
      int y = gp.screenHeight/2;
      g2.drawString(text, x, y);
   }

   public int getXforCenteredText(String text){
      int len = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
      int x = gp.screenWidth/2 - len/2;
      return x;
   }
}
