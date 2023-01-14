package main;

import java.io.*;

public class Config {
    GamePanel gp;
    public Config(GamePanel gp){
        this.gp = gp;
    }

    public  void saveConfig(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));

            //full screen
            if(gp.fullScreenOn){
                bw.write("On");
            }
            if(!gp.fullScreenOn){
                bw.write("Off");
            }
            bw.newLine();

            //music volume
            bw.write(String.valueOf(gp.defaultBgm.volumeScale));
            bw.newLine();

            //se volume
            bw.write(String.valueOf(gp.cursor.volumeScale));
            bw.newLine();

            bw.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  void loadConfig(){
        try {
            BufferedReader br = new BufferedReader(new FileReader("config.txt"));

            String s = br.readLine();

            //full screen
            if(s.equals("On")){
                gp.fullScreenOn = true;
            }
            if(s.equals("Off")){
                gp.fullScreenOn = false;
            }

            //music vol
            s = br.readLine();
            gp.defaultBgm.volumeScale = Integer.parseInt(s);

            br.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
