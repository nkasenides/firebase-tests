package model;

import com.google.cloud.firestore.annotation.Exclude;
import com.google.cloud.firestore.annotation.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.UUID;

@IgnoreExtraProperties
public class Grid {

    private String name;
    private int rows;
    private int cols;
    private ArrayList<String> chunkIDs;
    @Exclude private ArrayList<Chunk> chunks;

    public Grid() { }

    public Grid(int rows, int cols) {
        this.name = UUID.randomUUID().toString().replace("-", "");
        this.rows = rows;
        this.cols = cols;
        chunkIDs = new ArrayList<>();
        chunks = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public void setChunkIDs(ArrayList<String> chunkIDs) {
        this.chunkIDs = chunkIDs;
    }

    public ArrayList<String> getChunkIDs() {
        return chunkIDs;
    }

    public void addChunkID(final String chunkID) {
        this.chunkIDs.add(chunkID);
    }

    public ArrayList<Chunk> getChunks() {
        return chunks;
    }

    public void setChunks(ArrayList<Chunk> chunks) {
        this.chunks = chunks;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public String getName() {
        return name;
    }

}
