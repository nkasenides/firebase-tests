import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.FirestoreException;
import com.google.firebase.database.annotations.Nullable;

public class ListeningClient implements Runnable {

    private String name;
    private boolean initialized = false;

    public ListeningClient(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        DocumentReference docRef = ListeningClientMain.firestore.collection("chunks").document("7f4bdf04-9cc1-4f27-9925-d3e3559a277e");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirestoreException e) {
                System.out.println(System.currentTimeMillis());
            }
        });

        while (true) {
            //Do nothing
        }

    }
}
