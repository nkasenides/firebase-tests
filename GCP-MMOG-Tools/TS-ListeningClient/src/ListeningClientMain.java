import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;

public class ListeningClientMain {

    public static Firestore firestore;

    public static void main(String[] args) {

        System.out.print("Constructing client...");
        System.out.println(" [DONE]");

        System.out.print("Initializing Firestore...");
        initialize();
        System.out.println(" [DONE]");

        System.out.println();

        ListeningClient listeningClient = new ListeningClient("LISTENING-CLIENT");
        listeningClient.run();

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
