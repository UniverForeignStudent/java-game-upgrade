package object;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject{
	public OBJ_Door() {
		name = "Door";
		try{
			File file = new File("assets/objects/door.png");
			FileInputStream img = new FileInputStream(file);
			image = ImageIO.read(img);
			img.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}