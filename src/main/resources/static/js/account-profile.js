import { auth, db } from "/js/firebase-init.js";
import {
    updateEmail,
    updatePassword,
    sendPasswordResetEmail,
    deleteUser,
    reauthenticateWithCredential,
    EmailAuthProvider
} from "https://www.gstatic.com/firebasejs/10.8.1/firebase-auth.js";
import { doc, updateDoc, deleteDoc, getFirestore, getDoc } from "https://www.gstatic.com/firebasejs/10.8.1/firebase-firestore.js";

auth.onAuthStateChanged((user) => {
    if (user) {
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
            showAlert('Email updated successfully.', 'success');
            console.log("Firestore email updated successfully.");
        }).catch((error) => {
            showAlert("Error updating email: ", error);
            console.error("Error updating email in Firestore: ", error);
        });
    }).catch((error) => {
        showAlert("Error updating email: ", error);
        console.error("Error updating email: ", error);
    });
});

// document.getElementById('updatePasswordBtn').addEventListener('click', () => {
//     const newPassword = document.getElementById('newPassword').value;
//     updatePassword(user, newPassword).then(() => {
//         showAlert('Password updated successfully.', 'success');
//         console.log("Password updated successfully.");
//     }).catch((error) => {
//         showAlert("Error updating password: ", error);
//         console.error("Error updating password: ", error);
//     });
// });

document.getElementById('updatePasswordBtn').addEventListener('click', () => {
   const email = user.email;
     sendPasswordResetEmail(auth, email).then(() => {
        showAlert('Password reset email sent successfully.', 'success');
        console.log("Password reset email sent successfully.");
    }).catch((error) => {
        showAlert("Error sending password reset email: ", error);
        console.error("Error sending password reset email: ", error);
    });
 })

document.getElementById('closeAccountBtn').addEventListener('click', () => {   
    deleteUser(user).then(() => {
        showAlert('Account deleted successfully.', 'success');
        console.log("Account deleted successfully.");
        // Delete user data from Firestore if you're storing it there too
    }).catch((error) => {
        showAlert("Error deleting account: ", error);
        console.error("Error deleting account: ", error);
    });
});

// document.getElementById('reauthenticateBtn').addEventListener('click', () => {
//     const credential = EmailAuthProvider.credential(user.email, document.getElementById('currentPassword').value);
//     reauthenticateWithCredential(user, credential).then(() => {
//         console.log("Reauthenticated successfully.");
//     }).catch((error) => {
//         console.error("Error reauthenticating: ", error);
//     });
// });

document.getElementById('updateCarRegBtn').addEventListener('click', () => {
    const newCarRegistration = document.getElementById('newCarReg').value;
    const userDocRef = doc(db, "reminders", user.uid);
    updateDoc(userDocRef, {
        regNumber: newCarRegistration
    }).then(() => {
        showAlert('Car registration updated successfully.', 'success');
        console.log("Car registration updated successfully.");
    }).catch((error) => {
        showAlert("Error updating car registration: ", error);
        console.error("Error updating car registration: ", error);
    });
});

document.getElementById('closeAccountBtn').addEventListener('click', () => {
    const userDocRef = doc(db, "users", user.uid);
    deleteDoc(userDocRef).then(() => {
        showAlert('Account closed successfully.', 'success');
        console.log("Account closed successfully.");
    }).catch((error) => {
        showAlert("Error closing account: ", error);
        console.error("Error closing account: ", error);
    });
});

document.getElementById('requestDataBtn').addEventListener('click', () => { 
    const db = getFirestore();
    const userId = getAuth().currentUser.uid;
    const docRef = doc(db, "userData", userId);

    getDoc(docRef).then((docSnap) => {
        if (docSnap.exists()) {
            console.log("User data:", docSnap.data());
            // Process and display or send the data to the user
        } else {
            showAlert("No user data found.", "warning");
            console.log("No user data found.");
        }
    }).catch((error) => {
        showAlert("Error fetching user data: ", error);
        console.error("Error fetching user data: ", error);
    });
});
    }
    else {
        console.log("No user is signed in.");
    }
});

 // Call this function when a request is completed
function showAlert(message, type) {
    const alertPlaceholder = document.getElementById('alert-placeholder');
    const wrapper = document.createElement('div');
    wrapper.innerHTML = [
      `<div class="alert alert-${type} alert-dismissible" role="alert">`,
      `   ${message}`,
      '   <button type="button" class="close" data-dismiss="alert" aria-label="Close">',
      '       <span aria-hidden="true">&times;</span>',
      '   </button>',
      '</div>'
    ].join('')
  
    alertPlaceholder.append(wrapper);
  }
  
  
 
  
