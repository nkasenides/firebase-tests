import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteBatch;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            //Firestore firestore = FirestoreClient.getFirestore(); TODO FIRESTORE

            Grid grid1 = new Grid(30, 30);
            GridGenerator.generate(grid1);

//            WriteBatch batch = firestore.batch(); TODO FIRESTORE
            database.getReference("games").child("game1").setValue(grid1, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    System.out.println(databaseError);
                    System.out.println(databaseReference);
                }
            });
//            DocumentReference game1Ref = firestore.collection("games").document("game1"); TODO FIRESTORE
//            batch.set(game1Ref, grid1); TODO FIRESTORE

            for (Chunk c : grid1.getChunks()) {
//                DocumentReference chunkRef = firestore.collection("chunks").document(c.getId()); TODO FIRESTORE
                //                batch.set(chunkRef, c); TODO FIRESTORE
                database.getReference("chunks").child(c.getId()).setValue(c, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                        System.out.println(databaseError);
                        System.out.println(databaseReference);
                    }
                });
            }

//            ApiFuture<List<WriteResult>> future = batch.commit(); TODO FIREBASE

//            for (WriteResult result : future.get()) {
//                System.out.println("Update time : " + result.getUpdateTime());
//            }



        } catch (IOException /*| InterruptedException | ExecutionException*/ e) {
            e.printStackTrace();
        }
    }

}
