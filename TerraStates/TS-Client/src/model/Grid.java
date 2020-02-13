package model;

import com.google.cloud.firestore.annotation.Exclude;
import com.google.cloud.firestore.annotation.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.UUID;

@IgnoreExtraProperties
public class Grid {

    private final String name;
    private final int rows;
    private final int cols;
    private final ArrayList<String> chunkIDs;
    @Exclude private ArrayList<Chunk> chunks;

    public Grid(int rows, int cols) {
        this.name = UUID.randomUUID().toString().replace("-", "");
        this.rows = rows;
        this.cols = cols;
        chunkIDs = new ArrayList<>();
        chunks = new ArrayList<>();
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
