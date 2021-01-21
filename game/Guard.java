package game;

import java.awt.*;
/**
 *
 * @author Antoan
 * @param "Пазача наследява стойностите на фигурата"
 */
public class Guard extends Figure {
    public Color color;
    public Color color1;
    private int point;

    public Guard(int row, int col,Color color,Color color1) {
        super();
        this.row = row;
        this.col=col;
        this.color=color;
        this.color1=color1;
    }
    /**
     *
     * @author Antoan
     * @param "Визуализиране на пазачите"
     */
    public void render(Graphics g) {
        int x = this.col * GameTile.TILE_SIZE;
        int y = this.row * GameTile.TILE_SIZE;
        g.setColor(this.color);
        g.fillOval(x+33, y+54, 54, 54);
        g.setColor(this.color1);
        g.fillOval(x+40, y+61, 40, 40);
    }
    /**
     *
     * @author Antoan
     * @param "движението на гарда"
     */
    public void move(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

