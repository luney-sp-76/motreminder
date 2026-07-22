import { auth } from "/js/firebase-init.js?v=2";
import { patchReminders, deleteReminders, deleteUserDoc, getReminders } from "/js/reminders-api.js";
import {
    updateEmail,
    updatePassword,
    sendPasswordResetEmail,
    deleteUser,
    reauthenticateWithCredential,
    EmailAuthProvider,
    getAuth
} from "https://www.gstatic.com/firebasejs/10.8.1/firebase-auth.js";


auth.onAuthStateChanged((user) => {
    if (user) {
const user = auth.currentUser;

function isValidEmail(email) {
    // Regular expression for basic email validation
    const regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    return regex.test(email);
}


document.getElementById('updateEmailBtn').addEventListener('click', () => {
    const newEmail = document.getElementById('newEmail').value;

     // Check if the email input is not empty and is valid
     if (!newEmail || !isValidEmail(newEmail)) {
        showAlert("Please enter a valid email address.", "error");
        console.error("Invalid email input.");
        return; // Stop the function if the email is not valid
    }
    updateEmail(user, newEmail).then(() => {
        console.log("Email updated successfully.");
        // Update email in Firestore via backend API
        patchReminders({ email: newEmail })
            .then(() => {
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


// function isValidUKRegPlate(regPlate) {
//     // Regex to check various styles of UK plates
//     const regex = /^([A-Z]{2}\d{2} [A-Z]{3}|[A-Z]\d{1,3} [A-Z]{3}|[A-Z]{3} \d{1,3}[A-Z]|[A-Z]{1,3} \d{1,4}|[1-9]\d{0,3} [A-Z]{1,3}|[A-Z]{1,3} [1-9]\d{0,3})$/;
//     return regex.test(regPlate.toUpperCase());
// }

//Reg Number check will check valid plates with or without spaces
function isValidUKRegPlate(regPlate) {
    // Regex to check various styles of UK plates, making spaces optional
    const regex = /^([A-Z]{2}\d{2}\s?[A-Z]{3}|[A-Z]\d{1,3}\s?[A-Z]{3}|[A-Z]{3}\s?\d{1,3}[A-Z]|[A-Z]{1,3}\s?\d{1,4}|[1-9]\d{0,3}\s?[A-Z]{1,3}|[A-Z]{1,3}\s?[1-9]\d{0,3})$/;
    return regex.test(regPlate.toUpperCase());
}



document.getElementById('updateCarRegBtn').addEventListener('click', () => {
    const newCarRegistration = document.getElementById('newCarReg').value;
    // Check if the email input is not empty and is valid
    if (!newCarRegistration || !isValidUKRegPlate(newCarRegistration)) {
        showAlert("Invalid registration number plate", "error");
        console.error("Invalid registration number plate update input.");
        return; // Stop the function if the email is not valid
    }

    patchReminders({ regNumber: newCarRegistration })
    .then(() => {
        showAlert('Car registration updated successfully.', 'success');
        console.log("Car registration updated successfully.");
    }).catch((error) => {
        showAlert("Error updating car registration: ", error);
        console.error("Error updating car registration: ", error);
    });
});

document.getElementById('closeAccountBtn').addEventListener('click', () => {
    deleteUserDoc().then(() => {
        showAlert('Account closed successfully.', 'success');
        console.log("Account closed successfully.");
    }).catch((error) => {
        showAlert("Error closing account: ", error);
        console.error("Error closing account: ", error);
    });
});


document.getElementById('gdprDataRequestBtn').addEventListener('click', () => {


    if (auth.currentUser) {
        const userId = auth.currentUser.uid;
        getReminders()
            .then(data => {
                if (data && Object.keys(data).length > 0) {
                    console.log("User reminder data:", data);
                    generatePDF([data]);
                } else {
                    showAlert("No reminders data found for user.", "warning");
                    console.log("No reminders data found for user.");
                }
            })
            .catch(error => {
                showAlert(`Error fetching reminders data: ${error}`, "danger");
                console.error("Error fetching reminders data: ", error);
            });
    } else {
        showAlert("No user is signed in.", "warning");
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
    ].join('');

    alertPlaceholder.append(wrapper);
}

function generatePDF(userData) {
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF();

    userData.forEach((data, index) => {
        const baseY = 10 + (index * 10);
        Object.keys(data).forEach((key, idx) => {
            doc.text(`${key}: ${data[key]}`, 10, baseY + (idx * 10));
        });
    });

    doc.save('user-data.pdf');
}   }   else {
    console.log("No user is signed in.");
}   });
