package pieces;

import main.Board;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Pawn extends Piece{
    public Pawn(Board board, int col, int row, boolean isWhite){
        super(board);
        this.col = col;
        this.row = row;
        this.xpos = col * board.tilesize;
        this.ypos = row * board.tilesize;
        this.isWhite=isWhite;
        this.name="Pawn";
        this.sprite = sheet.getSubimage(5 * sheetscale, isWhite? 0:sheetscale,sheetscale,sheetscale).getScaledInstance(board.tilesize, board.tilesize, BufferedImage.SCALE_SMOOTH);
    }
}
