import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.annotations.Nullable;
import model.Cell;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WriterClientMain {

    public static Firestore firestore;

    public static void main(String[] args) {

        System.out.print("Constructing client...");
        System.out.println(" [DONE]");

        System.out.print("Initializing Firestore...");
        initialize();
        System.out.println(" [DONE]");

        System.out.println();


        WritingClient writingClient = new WritingClient("WRITING-CLIENT");
        writingClient.run();

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
