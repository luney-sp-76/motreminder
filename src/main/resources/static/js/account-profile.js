import { auth, db } from "/js/firebase-init.js";
import {
    updateEmail,
    updatePassword,
    sendPasswordResetEmail,
    deleteUser,
    reauthenticateWithCredential,
    EmailAuthProvider
} from "https://www.gstatic.com/firebasejs/10.8.1/firebase-auth.js";
import { doc, updateDoc, deleteDoc } from "https://www.gstatic.com/firebasejs/10.8.1/firebase-firestore.js";

const user = auth.currentUser;

document.getElementById('updateEmailBtn').addEventListener('click', () => {
    const newEmail = document.getElementById('newEmail').value;
    updateEmail(user, newEmail).then(() => {
        console.log("Email updated successfully.");
        // Update email in Firestore
        const userDocRef = doc(db, "reminders", user.uid);
        updateDoc(userDocRef, {
            email: newEmail
        }).then(() => {
            console.log("Firestore email updated successfully.");
        }).catch((error) => {
            console.error("Error updating email in Firestore: ", error);
        });
    }).catch((error) => {
        console.error("Error updating email: ", error);
    });
});

document.getElementById('updatePasswordBtn').addEventListener('click', () => {
    const newPassword = document.getElementById('newPassword').value;
    updatePassword(user, newPassword).then(() => {
        console.log("Password updated successfully.");
    }).catch((error) => {
        console.error("Error updating password: ", error);
    });
});

document.getElementById('sendPasswordResetEmailBtn').addEventListener('click', () => {
    const email = user.email;
    sendPasswordResetEmail(auth, email).then(() => {
        console.log("Password reset email sent successfully.");
    }).catch((error) => {
        console.error("Error sending password reset email: ", error);
    });
});

document.getElementById('deleteAccountBtn').addEventListener('click', () => {   
    deleteUser(user).then(() => {
        console.log("Account deleted successfully.");
        // Delete user data from Firestore if you're storing it there too
    }).catch((error) => {
        console.error("Error deleting account: ", error);
    });
});

document.getElementById('reauthenticateBtn').addEventListener('click', () => {
    const credential = EmailAuthProvider.credential(user.email, document.getElementById('currentPassword').value);
    reauthenticateWithCredential(user, credential).then(() => {
        console.log("Reauthenticated successfully.");
    }).catch((error) => {
        console.error("Error reauthenticating: ", error);
    });
});

document.getElementById('updateCarRegistrationBtn').addEventListener('click', () => {
    const newCarRegistration = document.getElementById('newCarRegistration').value;
    const userDocRef = doc(db, "users", user.uid);
    updateDoc(userDocRef, {
        carRegistration: newCarRegistration
    }).then(() => {
        console.log("Car registration updated successfully.");
    }).catch((error) => {
        console.error("Error updating car registration: ", error);
    });
});

document.getElementById('closeAccountBtn').addEventListener('click', () => {
    const userDocRef = doc(db, "users", user.uid);
    deleteDoc(userDocRef).then(() => {
        console.log("Account closed successfully.");
    }).catch((error) => {
        console.error("Error closing account: ", error);
    });
});

import { getFirestore, doc, getDoc } from "https://www.gstatic.com/firebasejs/10.8.1/firebase-firestore.js";

document.getElementById('requestDataBtn').addEventListener('click', () => { 
    const db = getFirestore();
    const userId = getAuth().currentUser.uid;
    const docRef = doc(db, "userData", userId);

    getDoc(docRef).then((docSnap) => {
        if (docSnap.exists()) {
            console.log("User data:", docSnap.data());
            // Process and display or send the data to the user
        } else {
            console.log("No user data found.");
        }
    }).catch((error) => {
        console.error("Error fetching user data: ", error);
    });
});
