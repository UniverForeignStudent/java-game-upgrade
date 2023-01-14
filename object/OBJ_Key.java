package object;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject{
	public OBJ_Key(){
      name = "Key";
      try{
         File file = new File("assets/objects/key.png");
         FileInputStream img = new FileInputStream(file);
         image = ImageIO.read(img);
         img.close();
      } catch (IOException e){
         e.printStackTrace();
      }
      collision = true;
   }
}
