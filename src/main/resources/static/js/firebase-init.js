
// firebase-init.js
import {initializeApp}  from 'https://www.gstatic.com/firebasejs/10.8.1/firebase-app.js';
import  {getAuth}  from 'https://www.gstatic.com/firebasejs/10.8.1/firebase-auth.js';
import  {getFirestore}  from 'https://www.gstatic.com/firebasejs/10.8.1/firebase-firestore.js';


  const firebaseConfig = 
  {
  apiKey: "AIzaSyC70rJCPcUrMqdNC2647bk7n6pnLOqXio4",
    authDomain: "motbookingreminder.firebaseapp.com",
    projectId: "motbookingreminder",
    storageBucket: "motbookingreminder.appspot.com",
    messagingSenderId: "470015000345",
    appId: "1:470015000345:web:ff0adfd1e313cc795dcf88",
    measurementId: "G-WHN3BH6RRZ"
  };

// Initialize Firebase
const app = initializeApp(firebaseConfig);

// Optionally initialize services
const auth = getAuth(app);
const db = getFirestore(app);

// Export for use in other modules
export { app, auth, db };