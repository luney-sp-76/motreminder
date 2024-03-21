/**
    * Initializes the Firestore service by setting up the FirebaseApp with the provided service account key.
    */
package com.motbookingreminder.firestore;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirestoreService {

    @PostConstruct
    public void initialize() {
        try {
            String pathToServiceAccountKey = "/Users/paulolphert/git_repo/MOTBOOKINGREMINDERAPPKEY/motbookingreminder-firebase-adminsdk-3yaxx-ef71a03334.json";
            FileInputStream serviceAccount = new FileInputStream(pathToServiceAccountKey);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) { // <--- check with this line
                FirebaseApp.initializeApp(options);
                System.out.println("Firestore has been initialized");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
