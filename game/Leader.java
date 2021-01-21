package game;

import java.awt.*;

/**
 *
 * @author Antoan
 * @param "Лидера наследява стойностите на фигурата+"
 */
    public class Leader extends Figure {
    public Color color;
    private int point;
    public Leader(int row, int col,Color color) {
        super();
        this.row = row;
        this.col=col;
        this.color=color;
    }
    /**
     *
     * @author Antoan
     * @param "движението на лидера"
     */
    public void move(int row, int col) {
        this.row = row;
        this.col = col;
    }

        /**
         *
         * @author Antoan
         * @param "Визоализиране на лидерите"
         */
        public void render(Graphics g){
            int x = this.col * GameTile.TILE_SIZE;
            int y = this.row * GameTile.TILE_SIZE;
            g.setColor(this.color);
            g.fillRect(x+35, y+56, 50, 50);
        }
}
