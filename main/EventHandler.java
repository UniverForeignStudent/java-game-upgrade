package main;

import java.awt.*;

public class EventHandler {

    GamePanel gp;

    int tempMap, tempCol, tempRow;
    public EventHandler(GamePanel gp) {
        this.gp = gp;
    }


    public void teleport(int map, int col, int row, int area){
        gp.gameState = gp.transitionState;
        gp.currentArea = area;
        tempMap = map;
        tempCol = col;
        tempRow = row;

    }

}

