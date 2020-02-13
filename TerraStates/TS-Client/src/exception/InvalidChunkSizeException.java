package exception;

import model.Chunk;

public class InvalidChunkSizeException extends ChunkException {

    public InvalidChunkSizeException(int givenSize) {
        super("The given chunk size of " + givenSize + " is not valid. The maximum chunk size is " + (Chunk.CHUNK_SIZE * Chunk.CHUNK_SIZE));
    }

}
