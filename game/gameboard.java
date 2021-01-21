package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 *
 * @author Antoan
 * @param "проектиране на игралното поле"
 */
public class gameboard extends JFrame implements MouseListener {
    public static final int TILE_SIDE_COUNT = 5;
    int rowStart,colStart;
    private Object[][] Guardians;
    private Object[][] Leadershit;
    private Object selectedGuard;
    private Object selectedLeader;
    private Object leaderAutoCheck;
    /**
     * @param "Създаване на игралното поле"
     * @author Antoan
     */
    public gameboard() {
        this.Guardians = new Guard[TILE_SIDE_COUNT][TILE_SIDE_COUNT];
        for (int i=0;i<4;i++){
            this.Guardians[0][i] = (new Guard(0, i, Color.YELLOW, Color.GREEN));
        }
        for (int i=4;i>0;i--){
            this.Guardians[4][i] = (new Guard(4, i, Color.GREEN, Color.YELLOW));
        }
        this.Leadershit= new Leader[TILE_SIDE_COUNT][TILE_SIDE_COUNT];
        this.Leadershit[0][4] = (new Leader(0, 4, Color.GREEN));
        this.Leadershit[4][0] = (new Leader(4, 0, Color.YELLOW));
        this.setSize(600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addMouseListener(this);

    }
    /**
     *
     * @author Antoan
     * @param "Override метод във който се викат функцията на mouseClicked "
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int row = this.getBoardDimentionBasedOnCoordinates(e.getY());
        int col = this.getBoardDimentionBasedOnCoordinates(e.getX());
        if(row<5&&col<5) {
            if (this.selectedGuard != null||this.selectedLeader!=null) {
                if((this.selectedGuard !=this.Guardians[row][col]&&this.selectedGuard !=this.Leadershit[row][col])&&(row-rowStart>-2&&row-rowStart<2)&&(col-colStart>-2&&col-colStart<2)) {
                    if (this.hasGuardPiece(row, col) || this.hasLeaderPiece(row, col)) {
                        System.out.println("Невалиден ход");
                    } else {
                        guardMove(row, col);
                    }
                }
                if((this.selectedLeader !=this.Leadershit)&&(row==rowStart||col==colStart)) {
                    if (this.hasGuardPiece(row, col) || this.hasLeaderPiece(row, col)) {
                        System.out.println("Невалиден ход");
                    } else {
                        leaderMove(row, col);
                    }
                }
            }else {
                rowStart=row;
                colStart=col;
                if (this.hasGuardPiece(row, col)) {
                    this.selectedGuard = this.getGuardPiece(row, col);
                }
                if (this.hasLeaderPiece(row, col)) {
                    this.selectedLeader = this.getLeaderPiece(row, col);
                }
            }
        }
    }
    /**
     *
     * @author Antoan
     * @param "Движението на гарда"
     */
    private void guardMove(int row,int col){
        Guard p = (Guard) this.selectedGuard;
        p.move(row, col);
        this.Guardians[row][col] = this.Guardians[rowStart][colStart];
        this.Guardians[rowStart][colStart] = null;
        this.repaint();
        rowStart = row;
        colStart = col;
        this.selectedGuard = null;
    }
    /**
     *
     * @author Antoan
     * @param "Движението на лидера и проверка дали лидера и гарда не са на едно и също поле"
     */
    private void leaderMove(int row, int col){
            int rowCount=rowStart,colCount=col;
            Leader p1 = (Leader) this.selectedLeader;
            if(rowStart>row) {
                do {
                    rowCount--;
                } while ((this.Guardians[rowCount][col] == null && this.Leadershit[rowCount][col] == null) && rowCount > 0);
                if(this.Guardians[rowCount][col] != null || this.Leadershit[rowCount][col] != null)rowCount++;
                p1.move(rowCount, col);
                this.Leadershit[rowCount][col] = this.selectedLeader;
            }
            if(rowStart<row) {
                do {
                    rowCount++;
                } while ((this.Guardians[rowCount][col] == null && this.Leadershit[rowCount][col] == null) && rowCount <4);
                if(this.Guardians[rowCount][col] != null || this.Leadershit[rowCount][col] != null)rowCount--;
                p1.move(rowCount, col);
                this.Leadershit[rowCount][col] = this.selectedLeader;
            }
            if(colStart>col) {
                do {
                    colCount--;
                } while ((this.Guardians[row][colCount] == null && this.Leadershit[row][colCount] == null) && colCount >0);
                if(this.Guardians[row][colCount] != null || this.Leadershit[row][colCount] != null)colCount++;
                p1.move(row, colCount);
                this.Leadershit[row][colCount] = this.selectedLeader;
            }
            if(colStart<col) {
                do {
                    colCount++;
                } while ((this.Guardians[row][colCount] == null && this.Leadershit[row][colCount] == null) && colCount < 4);
                if(this.Guardians[row][colCount] != null || this.Leadershit[row][colCount] != null)colCount--;
                p1.move(row, colCount);
                this.Leadershit[row][colCount] = this.selectedLeader;
            }
            this.Leadershit[rowStart][colStart] = null;
            this.repaint();
            rowStart = row;
            colStart = col;
            this.selectedLeader = null;
        }

    @Override
    /**
     *
     * @author Antoan
     * @param "Пренаписване на вече написания родителски клас пеин"
     */
    public void paint(Graphics g) {

        super.paint(g);

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                GameTile tile = new GameTile(row, col);
                tile.render(g);
            }
        }
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                renderGamePiece(g,row,col);
            }
        }

    }

    private Object getGuardPiece(int row, int col) {
        return this.Guardians[row][col];

    }
    /**
     *
     * @author Antoan
     * @param "проверка за дали гарда не е нулл"
     */

    private boolean hasGuardPiece(int row, int col) {
        return this.getGuardPiece(row, col) != null;
    }
    /**
     *
     * @author Antoan
     * @param "обекта лидер"
     */
    private Object getLeaderPiece(int row, int col) {
        return this.Leadershit[row][col];

    }
    /**
     *
     * @author Antoan
     * @param "проверка дали лидера не нулл"
     */

    private boolean hasLeaderPiece(int row, int col) {
        return this.getLeaderPiece(row, col) != null;
    }
    /**
     *
     * @author Antoan
     * @param "проектирането на игралните фигури"
     */
    private void renderGamePiece(Graphics g, int row, int col) {
        if (this.hasGuardPiece(row, col)) {
            Guard p=(Guard)this.getGuardPiece(row,col);
            p.render(g);

        }
        if (this.hasLeaderPiece(row, col)) {
            Leader p1=(Leader) this.getLeaderPiece(row,col);
            p1.render(g);

        }
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    /**
     *
     * @author Antoan
     * @param "координатите на играта"
     */
    private int getBoardDimentionBasedOnCoordinates(int coordinates) {
        return coordinates / GameTile.TILE_SIZE;
    }
}
