package model;

import com.panickapps.jsec.HashType;
import com.panickapps.jsec.Hashing;

public class Cell {

    private int row;
    private int col;
    private boolean marked;

    public Cell() { }

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.marked = false;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public static String hashCoordinates(int row, int col) {
        String coordinatesUnprocessed = row + "," + col;
        return Hashing.hash(HashType.MD5, coordinatesUnprocessed);
    }

}
