package object;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject {
   public OBJ_Chest(){
      name = "Chest";
      try{
         File file = new File("assets/ntiles/chest1.png");
         FileInputStream img = new FileInputStream(file);
         image = ImageIO.read(img);
         img.close();
      } catch (IOException e){
         e.printStackTrace();
      }
   }
   
}
