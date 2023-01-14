package environment;

import main.GamePanel;

import java.awt.*;

public class EnvironmentManager {
    GamePanel gp;
    Light light;

    public  EnvironmentManager(GamePanel gp){
        this.gp = gp;
    }

    public  void setLight(){
        light = new Light(gp, 500);
    }

    public  void draw(Graphics2D g2){
        if(gp.currentArea == gp.dungeon){
            light.draw(g2);
        }
    }
}
