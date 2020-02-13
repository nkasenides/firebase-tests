package model;

import exception.InvalidChunkSizeException;

import java.util.HashMap;
import java.util.UUID;

public class Chunk {

    public static final int CHUNK_SIZE = 16;

    private final String id;
    private final int startRow;
    private final int startCol;
    private final int lastRow;
    private final int lastCol;
    private HashMap<String, ? extends Cell> cells;

    public Chunk(int startRow, int startCol, int lastRow, int lastCol) {
        this.id = UUID.randomUUID().toString();
        this.startRow = startRow;
        this.startCol = startCol;
        this.lastRow = lastRow;
        this.lastCol = lastCol;
        this.cells = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getLastRow() {
        return lastRow;
    }

    public int getLastCol() {
        return lastCol;
    }

    public HashMap<String, ? extends Cell> getCells() {
        return cells;
    }

    public void setCells(HashMap<String, ? extends Cell> cells) throws InvalidChunkSizeException {
        if (cells.size() <= (CHUNK_SIZE * CHUNK_SIZE)) {
            this.cells = cells;
        }
        else {
            throw new InvalidChunkSizeException(cells.size());
        }
    }

}
