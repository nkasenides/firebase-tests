import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteBatch;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.*;
import model.Chunk;
import model.Grid;
import util.RDBExecutor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GameMakerScriptRDB {

    public static void main(String[] args) {
        try {
            FileInputStream serviceAccount = new FileInputStream("gcp-mmog-rdb-firebase-adminsdk-2tv89-f8a94f8c46.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setProjectId("gcp-mmog-rdb")
                    .setDatabaseUrl("https://gcp-mmog-rdb.firebaseio.com")
                    .build();
            FirebaseApp.initializeApp(options);
            FirebaseDatabase database = FirebaseDatabase.getInstance();

            Grid grid1 = new Grid(30, 30);
            GridGenerator.generate(grid1);

            DatabaseReference ref = database.getReference("games").child("game1");
            RDBExecutor.execute(ref.setValueAsync(grid1));

            for (Chunk c : grid1.getChunks()) {
                DatabaseReference chunkRef = database.getReference("chunks").child(c.getId());
                RDBExecutor.execute(chunkRef.setValueAsync(c));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
