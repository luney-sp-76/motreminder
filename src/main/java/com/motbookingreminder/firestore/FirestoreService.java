package com.motbookingreminder.firestore;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.FileInputStream;
import java.io.IOException;

public class FirestoreService {
    public static void initialize() {
        try {
            // Path to your service account key file
            String pathToServiceAccountKey = "/Users/paulolphert/git_repo/MOTBOOKINGREMINDERAPPKEY/motbookingreminder-firebase-adminsdk-3yaxx-ef71a03334.json";

            // Initialize the app with a service account, granting admin privileges
            FileInputStream serviceAccount = new FileInputStream(pathToServiceAccountKey);
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            FirebaseApp.initializeApp(options);

            System.out.println("Firestore has been initialized");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
