package model;

public class TSCell extends Cell {

    private boolean marked;

    public TSCell(int row, int col) {
        super(row, col);
        this.marked = false;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

}
