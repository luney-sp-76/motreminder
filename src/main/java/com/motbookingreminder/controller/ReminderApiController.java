package com.motbookingreminder.controller;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.cloud.FirestoreClient;
import com.motbookingreminder.controller.ReminderCronJob;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class ReminderApiController {

    @Autowired
    private ReminderCronJob reminderCronJob;

    private String verifyToken(HttpServletRequest request) throws Exception {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new SecurityException("Missing Authorization header");
        }
        FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(authHeader.substring(7));
        return token.getUid();
    }

    private Firestore db() {
        return FirestoreClient.getFirestore();
    }

    @GetMapping("/reminders")
    public ResponseEntity<Map<String, Object>> getReminders(HttpServletRequest request) {
        try {
            String uid = verifyToken(request);
            DocumentSnapshot doc = db().collection("reminders").document(uid).get().get();
            return ResponseEntity.ok(doc.exists() ? doc.getData() : Collections.emptyMap());
        } catch (SecurityException e) {
            return ResponseEntity.status(401).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/reminders")
    public ResponseEntity<Void> setReminders(HttpServletRequest request,
                                              @RequestBody Map<String, Object> data) {
        try {
            String uid = verifyToken(request);
            data.put("userID", uid);
            DocumentReference docRef = db().collection("reminders").document(uid);
            try {
                docRef.update(data).get();
            } catch (ExecutionException e) {
                String msg = e.getMessage() != null ? e.getMessage() : "";
                if (msg.contains("NOT_FOUND") || msg.contains("No document to update")) {
                    docRef.set(toNestedMap(data)).get();
                } else {
                    throw e;
                }
            }
            return ResponseEntity.ok().build();
        } catch (SecurityException e) {
            return ResponseEntity.status(401).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PatchMapping("/reminders")
    public ResponseEntity<Void> patchReminders(HttpServletRequest request,
                                                @RequestBody Map<String, Object> data) {
        try {
            String uid = verifyToken(request);
            db().collection("reminders").document(uid).update(data).get();
            return ResponseEntity.ok().build();
        } catch (SecurityException e) {
            return ResponseEntity.status(401).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/reminders")
    public ResponseEntity<Void> deleteReminders(HttpServletRequest request) {
        try {
            String uid = verifyToken(request);
            db().collection("reminders").document(uid).delete().get();
            return ResponseEntity.ok().build();
        } catch (SecurityException e) {
            return ResponseEntity.status(401).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/reminders/vehicle/{regNumber}")
    public ResponseEntity<Void> deleteVehicle(HttpServletRequest request,
                                               @PathVariable String regNumber) {
        try {
            String uid = verifyToken(request);
            db().collection("reminders").document(uid)
                    .update("vehicles." + regNumber, FieldValue.delete())
                    .get();
            return ResponseEntity.ok().build();
        } catch (SecurityException e) {
            return ResponseEntity.status(401).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/users")
    public ResponseEntity<Void> deleteUserDoc(HttpServletRequest request) {
        try {
            String uid = verifyToken(request);
            db().collection("users").document(uid).delete().get();
            return ResponseEntity.ok().build();
        } catch (SecurityException e) {
            return ResponseEntity.status(401).build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    /**
     * POST /api/admin/trigger-booking-check
     * Immediately runs the MOT booking follow-up check for the authenticated user.
     * Use for testing — set motBookingDate to a past date first.
     */
    @PostMapping("/admin/trigger-booking-check")
    public ResponseEntity<String> triggerBookingCheck(HttpServletRequest request) {
        try {
            String uid = verifyToken(request);
            reminderCronJob.processUserBookings(uid);
            return ResponseEntity.ok("Booking check triggered for user " + uid);
        } catch (SecurityException e) {
            return ResponseEntity.status(401).body("Unauthorized");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> toNestedMap(Map<String, Object> data) {
        Map<String, Object> result = new LinkedHashMap<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key.contains(".")) {
                String[] parts = key.split("\\.", 2);
                Map<String, Object> nested = (Map<String, Object>)
                        result.computeIfAbsent(parts[0], k -> new LinkedHashMap<>());
                nested.put(parts[1], value);
            } else {
                result.put(key, value);
            }
        }
        return result;
    }
}
