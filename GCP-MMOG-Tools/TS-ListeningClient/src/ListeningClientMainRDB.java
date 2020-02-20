import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileInputStream;
import java.io.IOException;

public class ListeningClientMainRDB {

    public static FirebaseDatabase database;

    public static void main(String[] args) {

        System.out.print("Constructing client...");
        System.out.println(" [DONE]");

        System.out.print("Initializing real time database...");
        initialize();
        System.out.println(" [DONE]");

        System.out.println();

        ListeningClientRDB listeningClient = new ListeningClientRDB("LISTENING-CLIENT");
        listeningClient.run();

    }

    private static void initialize() {
        try {
            FileInputStream serviceAccount = new FileInputStream("gcp-mmog-rdb-firebase-adminsdk-2tv89-f8a94f8c46.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setProjectId("gcp-mmog-rdb")
                    .setDatabaseUrl("https://gcp-mmog-rdb.firebaseio.com")
                    .build();
            FirebaseApp.initializeApp(options);
            database = FirebaseDatabase.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
