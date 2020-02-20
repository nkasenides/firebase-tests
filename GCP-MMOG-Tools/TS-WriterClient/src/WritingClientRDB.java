import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import model.Cell;
import model.Chunk;
import util.RDBExecutor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class WritingClientRDB implements Runnable {

    private String name;
    final Chunk[] chunk = new Chunk[]{null};
    DatabaseReference dataRef;

    boolean init = false;

    public WritingClientRDB(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        dataRef = WriterClientMainRDB.database.getReference("chunks").child("b4e19ab0-d25b-48d3-a121-1ebdbbb8ee13");

        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                chunk[0] = dataSnapshot.getValue(Chunk.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        if (!init) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            init = true;
        }

        if (chunk[0] == null) {
            System.out.println("Null chunk!");
            return;
        }

        String hash = Cell.hashCoordinates(0, 0);
        Cell cell = chunk[0].getCells().get(hash);

        for (int i = 0; i < 100; i++) {

            try {
                boolean setValue = (i % 2 == 0);
                System.out.println(setValue);
                cell.setMarked(setValue);
                DatabaseReference chunkRef = WriterClientMainRDB.database.getReference("chunks").child("b4e19ab0-d25b-48d3-a121-1ebdbbb8ee13");
                RDBExecutor.execute(chunkRef.setValueAsync(chunk[0]));
                System.out.println(System.currentTimeMillis());
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
            }
        }

    }

}
