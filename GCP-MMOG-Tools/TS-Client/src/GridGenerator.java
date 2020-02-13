import com.panickapps.jsec.HashType;
import com.panickapps.jsec.Hashing;
import exception.InvalidChunkSizeException;
import model.TSCell;
import model.Chunk;
import model.Grid;

import java.util.ArrayList;
import java.util.HashMap;

public class GridGenerator {


    public static void generate(final Grid grid) {

        System.out.println("Rows: " + grid.getRows());
        System.out.println("Cols: " + grid.getCols());

//        int chunksNeeded = calculateChunksNeeded(grid.getRows(), grid.getCols());


        ArrayList<Chunk> chunks = null;
        try {
            chunks = createChunks(grid.getRows(), grid.getCols());
        } catch (InvalidChunkSizeException e) {
            e.printStackTrace();
        }

        for (Chunk c : chunks) {
            grid.addChunkID(c.getId());
        }

        grid.setChunks(chunks);

        System.out.println();

    }

    private static ArrayList<Chunk> createChunks(final int rows, final int cols) throws InvalidChunkSizeException {
        int colsAsChunks = cols / Chunk.CHUNK_SIZE;
        int colsAsChunksRemainder = cols % Chunk.CHUNK_SIZE;
        System.out.println("Chunks out of cols: " + colsAsChunks);
        System.out.println("Chunks out of cols (remainder): " + colsAsChunksRemainder);

        int rowsAsChunks = rows / Chunk.CHUNK_SIZE;
        int rowsAsChunksRemainder = rows % Chunk.CHUNK_SIZE;
        System.out.println("Chunks out of rows: " + rowsAsChunks);
        System.out.println("Chunks out of rows (remainder): " + rowsAsChunksRemainder);

        int midpointRows = rows / 2;
        int midpointCols = cols / 2;
        System.out.println("Midpoint: (" + midpointRows + "," + midpointCols + ")");

        final int totalChunkRows = rowsAsChunks + ((rowsAsChunksRemainder > 0) ? 1 : 0);
        final int totalChunkCols = colsAsChunks + ((colsAsChunksRemainder > 0) ? 1 : 0);

        ArrayList<Chunk> chunks = new ArrayList<>();

        for (int chunkRow = 0; chunkRow < totalChunkRows; chunkRow++) {
            int startRow = chunkRow * Chunk.CHUNK_SIZE;
            int lastRow = (startRow + Chunk.CHUNK_SIZE >= rows) ? startRow + rowsAsChunksRemainder : startRow + Chunk.CHUNK_SIZE;
            for (int chunkCol = 0; chunkCol < totalChunkCols; chunkCol++) {
                int startCol = chunkCol * Chunk.CHUNK_SIZE;
                int lastCol = (startCol + Chunk.CHUNK_SIZE >= cols) ? startCol + colsAsChunksRemainder : startCol + Chunk.CHUNK_SIZE;
                Chunk chunk = new Chunk(startRow, startCol, lastRow, lastCol);
                HashMap<String, TSCell> cellsInChunk = new HashMap<>();
                for (int row = startRow; row < lastRow; row++) {
                    for (int col = startCol; col < lastCol; col++) {
                        cellsInChunk.put(hashCoordinates(row, col), new TSCell(row, col));
                    }
                }
                chunk.setCells(cellsInChunk);
                chunks.add(chunk);
            }
        }
        return chunks;
    }

    private static String hashCoordinates(int row, int col) {
        String coordinatesUnprocessed = row + "," + col;
        String hashedCoordinates = Hashing.hash(HashType.MD5, coordinatesUnprocessed);
        return hashedCoordinates;
    }

    //TODO REMOVE?
    private static int calculateChunksNeeded(final long rows, final long cols) {
        int colsAsChunks = (int) Math.ceil((double) cols / Chunk.CHUNK_SIZE);
//        System.out.println("Chunks out of cols: " + colsAsChunks);

        int rowsAsChunks = (int) Math.ceil((double) rows / Chunk.CHUNK_SIZE);
//        System.out.println("Chunks out of rows: " + rowsAsChunks);

        int numOfChunks = 0;

        if (colsAsChunks == 1 && rowsAsChunks == 1) {
            numOfChunks = 1;
        }
        else if (colsAsChunks == 1) {
            numOfChunks = rowsAsChunks;
        }
        else if (rowsAsChunks == 1) {
            numOfChunks = colsAsChunks;
        }
        else {
            numOfChunks = colsAsChunks + rowsAsChunks;
        }

//        System.out.println("Num of chunks: " + numOfChunks); //TODO REMOVE
        return numOfChunks;
    }

}
