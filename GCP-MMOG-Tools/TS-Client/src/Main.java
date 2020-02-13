import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.annotations.Nullable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    private static Firestore firestore;
    private static String name;

    public static void main(String[] args) {

        System.out.print("Constructing client...");
        name = args[0];
        System.out.println(" [DONE]");

        System.out.print("Initializing Firestore...");
        initialize();
        System.out.println(" [DONE]");

        DocumentReference docRef = firestore.collection("chunks").document("163e4974-22a4-42df-aedf-6ac6e5faebbe");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirestoreException e) {
                if (e != null) {
                    System.err.println("Listen failed: " + e);
                    return;
                }

                if (snapshot != null && snapshot.exists()) {
                    System.out.println("Current data: " + snapshot.getData());
                } else {
                    System.out.print("Current data: null");
                }
            }
        });

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {}
            }
        });
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.start();


    }

    private static void initialize() {
        try {
            FileInputStream serviceAccount = new FileInputStream("ts-service-account.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://terrastates.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
            firestore = FirestoreClient.getFirestore();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
