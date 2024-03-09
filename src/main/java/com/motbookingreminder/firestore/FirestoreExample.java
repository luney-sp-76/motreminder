package com.motbookingreminder.firestore;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import java.util.HashMap;
import java.util.Map;

/**
 * This class provides an example of how to add a document to Firestore.
 */
public class FirestoreExample {
    /**
     * Adds a document to Firestore.
     *
     * @param db The Firestore instance.
     */
    public static void addDocument(Firestore db) {
        try {
            // Create a new document with a generated ID in the "users" collection
            Map<String, Object> user = new HashMap<>();
            user.put("name", "Ada Lovelace");
            user.put("age", 30);

            // Add a new document with a generated ID
            ApiFuture<DocumentReference> addedDocRef = db.collection("users").add(user);
            System.out.println("Added document with ID: " + addedDocRef.get().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}