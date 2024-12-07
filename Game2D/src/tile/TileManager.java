package tile;

import main.GamePanel;
import main.UtilityTool;
import java.io.BufferedReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum [][];
    DecimalFormat dFormat = new DecimalFormat("000");

    public TileManager(GamePanel gp){

        this.gp = gp;
        // TODO: Khi thay đổi map thì phải thay đổi số lượng Tile
        tile = new Tile[100];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        // TODO: Thay đổi map ở đây!
        loadMap("/res/maps/world_01.txt");
    }
    public void getTileImage(){

        setup(0,"000",false);
        setup(1,"001",false);
        setup(2,"002",false);
        setup(3,"003",true);
        for(int i = 4; i <= 15 ;i++){
            setup(i,dFormat.format(i),false);
        }

        setup(16,"016",true);
        setup(17,"017",true);
        setup(18,"018",false);
        setup(19,"019",true);
        setup(20,"020",false);
        for(int i = 21; i <= 57 ;i++){
            if(i <= 32) setup(i,dFormat.format(i),true);
            else setup(i, dFormat.format(i), false);
        }
        setup(58, "058", true);
        
    }
    public void setup(int index, String imageName, boolean collision){

        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){

        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;
            
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){

                String line = br.readLine();

                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col ++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row ++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D gp2){

        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;

            //(worldX , worldY) là tọa độ của ô map world cần hiển thị 
            //(player.worldX , player.worldY) là tọa độ của nhân vật khi di chuyển đến vị trị mới (tính theo hệ qui chiếu của map world)
            //(player.screenX , player.screenY) là cố định (luôn ở trung tâm màn hình) có tọa độ tính theo hệ quy chiếu của map world
            //(screenX , screenY) là tọa độ của ô map cần hiển thị trên màn hình (tính theo hệ quy chiếu screen)

            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            // Stop moving the camere at the edge
            if(gp.player.screenX > gp.player.worldX){
                screenX = worldX;
            }
            if(gp.player.screenY > gp.player.worldY){
                screenY = worldY;
            }
            int rightOffset = gp.screenWidth - gp.player.screenX;
            if(rightOffset > gp.worldWidth - gp.player.worldX){
                screenX = gp.screenWidth - (gp.worldWidth - worldX);
            }

            int bottomOffset = gp.screenHeight - gp.player.screenY;
            if(bottomOffset > gp.worldHeight - gp.player.worldY){
                screenY = gp.screenHeight - (gp.worldHeight - worldY);
            }

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

                gp2.drawImage(tile[tileNum].image, screenX , screenY , null);    
            }
            else if(gp.player.screenX > gp.player.worldX || 
                    gp.player.screenY > gp.player.worldY ||
                    rightOffset > gp.worldWidth - gp.player.worldX ||
                    bottomOffset > gp.worldHeight - gp.player.worldY) {
                gp2.drawImage(tile[tileNum].image, screenX , screenY , null);  
            }
            worldCol ++;
            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow ++;
            }
        }
    }
}
