package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    protected GamePanel gp;
    public Tile[] tile;
    public int[][][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[30];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("assets/maps/maps01.txt", 0);
        loadMap("assets/maps/maps02.txt", 1);
    }

    public void getTileImage() {
        try {

            File file = new File("assets/tiles/grass01.png");
            FileInputStream img = new FileInputStream(file);
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(img);

            file = new File("assets/tiles/water01.png");
            img = new FileInputStream(file);
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(img);
            tile[1].collision = true;

            file = new File("assets/tiles/road00.png");
            img = new FileInputStream(file);
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(img);

            file = new File("assets/ntiles/tree.png");
            img = new FileInputStream(file);
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(img);
            tile[3].collision = true;
            
            file = new File("assets/tiles/road04.png");
            img = new FileInputStream(file);
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(img);

            file = new File("assets/tiles/road05.png");
            img = new FileInputStream(file);
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(img);

            file = new File("assets/tiles/road02.png");
            img = new FileInputStream(file);
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(img);


            file = new File("assets/tiles/road07.png");
            img = new FileInputStream(file);
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(img);

            file = new File("assets/tiles/water03.png");
            img = new FileInputStream(file);
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(img);
            tile[8].collision = true;

            file = new File("assets/tiles/water07.png");
            img = new FileInputStream(file);
            tile[9] = new Tile();
            tile[9].image = ImageIO.read(img);
            tile[9].collision = true;

            file = new File("assets/tiles/water11.png");
            img = new FileInputStream(file);
            tile[10] = new Tile();
            tile[10].image = ImageIO.read(img);
            tile[10].collision = true;

            file = new File("assets/tiles/water04.png");
            img = new FileInputStream(file);
            tile[11] = new Tile();
            tile[11].image = ImageIO.read(img);
            tile[11].collision = true;

            file = new File("assets/tiles/water02.png");
            img = new FileInputStream(file);
            tile[12] = new Tile();
            tile[12].image = ImageIO.read(img);
            tile[12].collision = true;


            file = new File("assets/tiles/water05.png");
            img = new FileInputStream(file);
            tile[13] = new Tile();
            tile[13].image = ImageIO.read(img);
            tile[13].collision = true;

            file = new File("assets/tiles/water06.png");
            img = new FileInputStream(file);
            tile[14] = new Tile();
            tile[14].image = ImageIO.read(img);
            tile[14].collision = true;

            file = new File("assets/tiles/water09.png");
            img = new FileInputStream(file);
            tile[15] = new Tile();
            tile[15].image = ImageIO.read(img);
            tile[15].collision = true;

            file = new File("assets/tiles/water10.png");
            img = new FileInputStream(file);
            tile[16] = new Tile();
            tile[16].image = ImageIO.read(img);
            tile[16].collision = true;

            file = new File("assets/tiles/water12.png");
            img = new FileInputStream(file);
            tile[17] = new Tile();
            tile[17].image = ImageIO.read(img);
            tile[17].collision = true;

            file = new File("assets/tiles/water13.png");
            img = new FileInputStream(file);
            tile[18] = new Tile();
            tile[18].image = ImageIO.read(img);
            tile[18].collision = true;

            file = new File("assets/tiles/water08.png");
            img = new FileInputStream(file);
            tile[19] = new Tile();
            tile[19].image = ImageIO.read(img);
            tile[19].collision = true;

            file = new File("assets/tiles/road03.png");
            img = new FileInputStream(file);
            tile[20] = new Tile();
            tile[20].image = ImageIO.read(img);

            file = new File("assets/tiles/road01.png");
            img = new FileInputStream(file);
            tile[21] = new Tile();
            tile[21].image = ImageIO.read(img);

            file = new File("assets/tiles/road06.png");
            img = new FileInputStream(file);
            tile[22] = new Tile();
            tile[22].image = ImageIO.read(img);

            file = new File("assets/tiles/road08.png");
            img = new FileInputStream(file);
            tile[23] = new Tile();
            tile[23].image = ImageIO.read(img);

            file = new File("assets/tiles/road09.png");
            img = new FileInputStream(file);
            tile[24] = new Tile();
            tile[24].image = ImageIO.read(img);

            file = new File("assets/tiles/road12.png");
            img = new FileInputStream(file);
            tile[25] = new Tile();
            tile[25].image = ImageIO.read(img);

            file = new File("assets/tiles/road10.png");
            img = new FileInputStream(file);
            tile[26] = new Tile();
            tile[26].image = ImageIO.read(img);

            file = new File("assets/tiles/road11.png");
            img = new FileInputStream(file);
            tile[27] = new Tile();
            tile[27].image = ImageIO.read(img);

            file = new File("assets/tiles/earth.png");
            img = new FileInputStream(file);
            tile[28] = new Tile();
            tile[28].image = ImageIO.read(img);

            file = new File("assets/tiles/wall.png");
            img = new FileInputStream(file);
            tile[29] = new Tile();
            tile[29].image = ImageIO.read(img);
            tile[29].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int mapNum) {
        try {
            File file = new File(filePath);
            FileInputStream map = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(map));
            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = reader.readLine();
                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split("\\s+");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[mapNum][col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&     worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
