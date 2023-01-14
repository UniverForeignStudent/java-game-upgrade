package object;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Bones extends SuperObject {
   public OBJ_Bones(){
      name = "Bones";
      try{
         File file = new File("assets/ntiles/bones2.png");
         FileInputStream img = new FileInputStream(file);
         image = ImageIO.read(img);
         img.close();
      } catch (IOException e){
         e.printStackTrace();
      }
      collision = true;
   }
   
}
