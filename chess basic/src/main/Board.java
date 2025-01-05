package main;

import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {

    public int tilesize=85;
    int cols=8;
    int rows=8;

    ArrayList<Piece> piecelist = new ArrayList<>();

    public Piece selectedpiece;

    Input input = new Input(this);

    public Board(){
        this.setPreferredSize(new Dimension(tilesize*cols, tilesize*rows));
        addpieces();

        this.addMouseListener(input);
        this.addMouseMotionListener(input);
    }



    public Piece getpiece(int col, int row){

        for (Piece piece : piecelist){
            if(piece.col == col && piece.row == row){
                return piece;
            }
        }

        return null;
    }


    public void makemove(Move move){
        move.piece.col = move.newcol;
        move.piece.row = move.newrow;
        move.piece.xpos = move.newcol * tilesize;
        move.piece.ypos = move.newrow * tilesize;


        capture(move);
    }

    public void capture(Move move){
        piecelist.remove(move.capture);
    }

    public boolean isvalidmove(Move move){

        if (sameteam(move.piece, move.capture)){
            return false;
        }

        if (!move.piece.isvalidmovement(move.newcol, move.newrow)){
            return false;
        }

        if (move.piece.movecollides(move.newcol, move.newrow)){
            return false;
        }
        return true;
    }

    public boolean sameteam(Piece p1, Piece p2){
        if(p1 == null || p2 == null){
            return false;
        }
        return p1.isWhite == p2.isWhite;
    }


    public void addpieces(){
        piecelist.add(new Knight(this,1,0,false));
        piecelist.add(new Knight(this,1,7,true));

        piecelist.add(new Knight(this,6,0,false));
        piecelist.add(new Knight(this,6,7,true));



        piecelist.add(new Rook(this,0,0,false));
        piecelist.add(new Rook(this,0,7,true));

        piecelist.add(new Rook(this,7,0,false));
        piecelist.add(new Rook(this,7,7,true));



        piecelist.add(new Bishop(this,2,0,false));
        piecelist.add(new Bishop(this,2,7,true));

        piecelist.add(new Bishop(this,5,0,false));
        piecelist.add(new Bishop(this,5,7,true));


        piecelist.add(new Queen(this,3,0,false));
        piecelist.add(new Queen(this,3,7,true));


        piecelist.add(new King(this,4,0,false));
        piecelist.add(new King(this,4,7,true));

        piecelist.add(new Pawn(this,0,1,false));
        piecelist.add(new Pawn(this,1,1,false));
        piecelist.add(new Pawn(this,2,1,false));
        piecelist.add(new Pawn(this,3,1,false));
        piecelist.add(new Pawn(this,4,1,false));
        piecelist.add(new Pawn(this,5,1,false));
        piecelist.add(new Pawn(this,6,1,false));
        piecelist.add(new Pawn(this,7,1,false));

        piecelist.add(new Pawn(this,0,6,true));
        piecelist.add(new Pawn(this,1,6,true));
        piecelist.add(new Pawn(this,2,6,true));
        piecelist.add(new Pawn(this,3,6,true));
        piecelist.add(new Pawn(this,4,6,true));
        piecelist.add(new Pawn(this,5,6,true));
        piecelist.add(new Pawn(this,6,6,true));
        piecelist.add(new Pawn(this,7,6,true));
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        for (int r=0;r<rows;r++){
            for (int c=0;c<cols;c++){
                g2d.setColor(((c+r)%2==0)? new Color(239, 215, 203): new Color(159, 119, 86));
                g2d.fillRect(c*tilesize, r*tilesize, tilesize, tilesize);
            }
        }
        if(selectedpiece != null)
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                if(isvalidmove(new Move(this, selectedpiece, c, r))){
                    g2d.setColor(new Color(145, 193, 103, 103));
                    g2d.fillRect(c*tilesize, r*tilesize, tilesize, tilesize);
                }
            }
        }

        for (Piece piece : piecelist){
            piece.paint(g2d);
        }
    }
}

