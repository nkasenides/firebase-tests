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
        DocumentReference docRef = ListeningClientMain.firestore.collection("chunks").document("ff3b3cdd-26a7-487e-a925-d8f6a1c7c1a1");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirestoreException e) {
                if (initialized) System.out.println(System.currentTimeMillis());
                else initialized = true;

                if (e != null) {
                    System.err.println("Listen failed: " + e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
//                    System.out.println("[" + name + "] Data: " + snapshot.getData());
                } else {
                    System.out.print("Current data: null");
                }
            }
        });

        while (true) {
            //Do nothing
        }

    }
}
