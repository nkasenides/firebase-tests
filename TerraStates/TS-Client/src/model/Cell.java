package model;

public class Cell {

    private int row;
    private int col;
    private boolean marked;

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

}
