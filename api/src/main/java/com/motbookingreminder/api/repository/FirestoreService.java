package com.motbookingreminder.api.repository;

import com.google.cloud.firestore.Firestore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirestoreService {

    private final Firestore firestore;

    @Autowired
    public FirestoreService(Firestore firestore) {
        this.firestore = firestore;
    }

    // Example method to demonstrate usage
    public void exampleMethod() {
        // Use the Firestore instance to interact with your database
    }
}
