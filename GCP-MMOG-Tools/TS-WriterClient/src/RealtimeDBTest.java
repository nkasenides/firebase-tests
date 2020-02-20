import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import com.panickapps.javaasynctask.AsyncTask;
import util.RDBExecutor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class RealtimeDBTest {

    public static void main(String[] args) {

        try {

            FileInputStream serviceAccount = new FileInputStream("gcp-mmog-rdb-firebase-adminsdk-2tv89-f8a94f8c46.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setProjectId("gcp-mmog-rdb")
                    .setDatabaseUrl("https://gcp-mmog-rdb.firebaseio.com")
                    .build();
            FirebaseApp.initializeApp(options);
            final FirebaseDatabase database = FirebaseDatabase.getInstance();

            DatabaseReference dataRef = database.getReference("data");

            dataRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String data = dataSnapshot.getValue(String.class);
                    System.out.println("DATA: " + data);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });


            RDBExecutor.execute(dataRef.setValueAsync("Working"));




//            Thread t = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    boolean[] flag = { false };
//                    dataRef.setValue("I'm writing data", new DatabaseReference.CompletionListener() {
//                        @Override
//                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                            if (databaseError != null) {
//                                System.out.println("Data could not be saved " + databaseError.getMessage());
//                            } else {
//                                System.out.println("Data saved successfully.");
//                            }
//                            flag[0] = true;
//                        }
//                    });
//                    try {
//                        Thread.sleep(10000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//            t.start();




        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}
