import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.FirestoreException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

public class ListeningClientRDB implements Runnable {

    private String name;
    private boolean initialized = false;

    public ListeningClientRDB(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        DatabaseReference dataRef = ListeningClientMainRDB.database.getReference("chunks").child("114e4205-a924-43a2-81d1-4695dcb4b7f5");
        final int[] i = {-1};
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                String data = dataSnapshot.getValue(String.class);
//                if (initialized) System.out.println(System.currentTimeMillis());
//                else initialized = true;
                System.out.println(i[0] + "\t\t" + System.currentTimeMillis());
                i[0]++;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        while (true) {
            //Do nothing
        }

    }
}
