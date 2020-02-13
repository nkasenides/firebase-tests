import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteBatch;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import model.Chunk;
import model.Grid;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GameMakerScript {

    public static void main(String[] args) {
        try {
            FileInputStream serviceAccount = new FileInputStream("ts-service-account.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://terrastates.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
            Firestore firestore = FirestoreClient.getFirestore();

            Grid grid1 = new Grid(30, 30);
            GridGenerator.generate(grid1);

            WriteBatch batch = firestore.batch();
            DocumentReference game1Ref = firestore.collection("games").document("game1");
            batch.set(game1Ref, grid1);

            for (Chunk c : grid1.getChunks()) {
                DocumentReference chunkRef = firestore.collection("chunks").document(c.getId());
                batch.set(chunkRef, c);
            }

            ApiFuture<List<WriteResult>> future = batch.commit();

            for (WriteResult result : future.get()) {
                System.out.println("Update time : " + result.getUpdateTime());
            }



        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
