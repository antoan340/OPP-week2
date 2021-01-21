
package game;

import java.awt.*;

public class GameTile {
    /**
     *
     * @author Antoan
     * @param "Създавам си цвят"
     */
    public static final int TILE_SIZE = 100;
    private static Color orange=new Color(255,165,0);

    private float row;
    private float col;
    private float tileSize;

    /**
     *
     * @author Antoan
     * @param "Инициализиране на стойностите за игралната плочка"
     */
    public GameTile(float row, float col) {

        this.row        = row;
        this.col        = col;
        this.tileSize   = 100;
    }
    /**
     *
     * @author Antoan
     * @param "Обединяване на 2та метода за визоализиране в едно"
     */
    public void render(Graphics g){
        RenderCubes(g);
        RenderBorders(g);

    }
    /**
     *
     * @author Antoan
     * @param "Визоализиране на квадратите и на кръгчето в центъра на полето"
     */
    public void RenderCubes(Graphics g) {
        g.setColor(orange);
        g.fillRect(10,31,100,100);
        g.fillRect(410,31,100,100);
        g.fillRect(110,431,100,100);
        g.fillRect(310,431,100,100);
        g.setColor(Color.black);
        g.fillRect(110,31,100,100);
        g.fillRect(310,31,100,100);
        g.fillRect(10,431,100,100);
        g.fillRect(410,431,100,100);
        g.setColor(Color.gray);
        g.fillRect(10,131,500,100);
        g.fillRect(10,331,500,100);
        g.setColor(Color.white);
        g.fillRect(210,31,100,500);
        g.setColor(Color.gray);
        g.fillOval(233,254,54,54);
    }
    /**
     *
     * @author Antoan
     * @param "Визуализиране на решетката на полето"
     */
    public void RenderBorders(Graphics g){
        g.setColor(Color.black);
        for (int i=31; i<=531 ;i+=100){
            for(int j=8;j<=508;j+=100){
                if(i<=431&&j<=408) {
                    g.fillRect(j, i, 2, 100);
                    g.fillRect(j, i, 100, 2);
                }
                if(i==531&&j<508){
                    g.fillRect(j, i, 100, 2);
                }
                if(i<531&&j==508){
                    g.fillRect(j, i, 2, 102);
                }
            }
        }
    }
}
