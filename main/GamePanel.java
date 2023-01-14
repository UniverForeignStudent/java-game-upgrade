package main;

import entity.Entity;
import entity.Player;
import environment.EnvironmentManager;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    //screen settings
    final int originalTileSize = 16; //16x16
    final int scale = 3;
    public int tileSize = originalTileSize * scale; //48x48
    public int maxScreenCol = 20;
    public int maxScreenRow = 12;
    public int screenWidth = tileSize * maxScreenCol; //768 pixels
    public int screenHeight = tileSize * maxScreenRow; //576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 62;
    public final int maxWorldRow = 62;
    public AssetSetter aSetter = new AssetSetter(this);
    public final int maxMap = 5;
    public int currentMap = 0;

    //FPS
    int FPS = 60;

    //SYSTEM
    TileManager tileManager = new TileManager(this);
    public KeyHandler key = new KeyHandler(this);
    public EventHandler eventHandler = new EventHandler(this);
    public UI ui = new UI(this);
    public Audio defaultBgm = new Audio("assets/audio/BlueBoyAdventure.wav", true);
    public Audio bossBgm = new Audio("assets/audio/boss.wav", true);
    public Audio gotBone = new Audio("assets/audio/gotbone.wav", false);
    public Audio attack = new Audio("assets/audio/attack.wav", false);
    public Audio cursor = new Audio("assets/audio/cursor.wav", false);
    Config config = new Config(this);
    EnvironmentManager environmentManager = new EnvironmentManager(this);
    Thread gameThread;

    //COLLISION
    public CollisionChecker cChecker = new CollisionChecker(this);

    //entity and objects
    public Player player = new Player(this, key);
    public SuperObject obj[][] = new SuperObject[maxMap][10];
    public Entity npc[][] = new Entity[maxMap][10];
    public Entity monster[][] = new Entity[maxMap][30];

    public Entity projectiles[][] = new Entity[maxMap][30];
    public boolean haveKey = false;
    ArrayList<Entity> entityList = new ArrayList<>();
    public ArrayList<Entity> projectileList = new ArrayList<>();

    //game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 4;
    public final int optionsState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int winState = 8;
    public boolean fullScreenOn = false;
    Graphics2D g2;

    //full screen
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;

    //AREA SETTING
    public int currentArea;
    public final int outside = 0;
    public final int dungeon = 1;


    public boolean isWin = false;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        Color color = new Color(103, 165, 94); //custom color
        this.setBackground(color);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);

    }

    public void setupGame() {
        aSetter.setObject();
        aSetter.setNpc();
        aSetter.setMonster();
        environmentManager.setLight();
        gameState = titleState;
        playMusic();
        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D) tempScreen.getGraphics();

        if (fullScreenOn == true) {
            setFullScreen();
        }
    }

    public void resetGame() {
        player.setDefaultValues();
        player.restore();
        aSetter.setNpc();
        aSetter.setObject();
        aSetter.setMonster();
        currentArea = outside;
        currentMap = outside;
        bossBgm.stop();
        defaultBgm.stop();
        defaultBgm.start();
    }


    public void setFullScreen() {
        //get local screen device
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        //get full screen width & height
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            // System.out.println("game status: running");
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                // update information (character position etc)
                update();
                //draw the screen with the updated information
                drawToTempScreen(); //draw to the buffered image
                drawToScreen(); //draw the buffered image to screen

                delta--;
            }
        }
    }

    public void update() {
        if (gameState == playState) {
            //PLAYER
            player.update();

            //NPC
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }

            //MONSTER
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    monster[currentMap][i].update();
                }
            }

            // DRAW PROJECTILE
            for (int i = 0; i < projectiles[1].length; i++) {
                if (projectiles[currentMap][i] != null) {
                    projectiles[currentMap][i].update();
                }
            }

            if (player.health == 0) {
                gameState = gameOverState;
            }

            if (isWin) {
                gameState = winState;
            }

        }
    }

    public void drawToTempScreen() {
        // title screen
        if (gameState == titleState) {
            ui.draw(g2);
        } else {
            tileManager.draw(g2); //draw map1 tiles

            //draw npc
            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].draw(g2);
                }
            }

            // DRAW OBJECT
            for (int i = 0; i < obj[1].length; i++) {
                if (obj[currentMap][i] != null) {
                    obj[currentMap][i].draw(g2, this);
                }
            }

            // DRAW MONSTER
            for (int i = 0; i < monster[1].length; i++) {
                if (monster[currentMap][i] != null) {
                    monster[currentMap][i].draw(g2);
                }
            }

//            // Projectile
//            for (int i = 0; i < projectileList.size(); i++) {
//                if (projectileList.get(i) != null) {
//                    entityList.add(projectileList.get(i));
//                }
//            }


            player.draw(g2); //then draw player


            //draw environment
            environmentManager.draw(g2);

//            //draw entity list
//            for (int i = 0; i < entityList.size(); i++) {
//                entityList.get(i).draw(g2);
//            }
//
//            for (int i = 0; i < entityList.size(); i++) {
//                entityList.remove(i);
//            }

            ui.draw(g2);

        }
    }

    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }

    public void playMusic() {
        defaultBgm.start();
    }

    public void changeArea() {
        if (currentArea == outside) {

            bossBgm.stop();
            defaultBgm.start();
        } else if (currentArea == dungeon) {
            defaultBgm.stop();
            bossBgm.start();
        }
    }

}
