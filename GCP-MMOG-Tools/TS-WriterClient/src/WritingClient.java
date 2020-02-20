import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.SetOptions;
import com.google.cloud.firestore.WriteResult;
import model.Cell;
import model.Chunk;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class WritingClient implements Runnable {

    private String name;

    public WritingClient(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        Chunk chunk = null;

        try {
            DocumentReference docRef = WriterClientMain.firestore.collection("chunks").document("7f4bdf04-9cc1-4f27-9925-d3e3559a277e");
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();
            if (document.exists()) {
                chunk = document.toObject(Chunk.class);
            } else {
                System.out.println("No such document!");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        if (chunk == null) {
            System.out.println("Null chunk!");
            return;
        }

        String hash = Cell.hashCoordinates(0,0);
//        Cell cell = chunk.getCells().get(hash);
//        boolean setValue = false;
//        if (cell.isMarked()) {
//            setValue = false;
//        }
//        else {
//            setValue = true;
//        }

        for (int i = 0; i < 100; i++) {
            try {
                boolean value = (i % 2 == 0);
                Map<String, Object> update = new HashMap<>();
                update.put("marked", value);
                ApiFuture<WriteResult> writeResult = WriterClientMain.firestore.collection("chunks").document("7f4bdf04-9cc1-4f27-9925-d3e3559a277e").set(update, SetOptions.merge());
//                ApiFuture<WriteResult> future = WriterClientMain.firestore.collection("chunks").document("7f4bdf04-9cc1-4f27-9925-d3e3559a277e").set(chunk);
//                System.out.println("Server update time: " + future.get().getUpdateTime());
                System.out.println(System.currentTimeMillis());
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
            }
        }


    }
}
