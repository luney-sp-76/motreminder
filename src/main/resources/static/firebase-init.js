import { initializeApp } from "https://www.gstatic.com/firebasejs/10.8.1/firebase-app.js";
import { getAnalytics } from "https://www.gstatic.com/firebasejs/10.8.1/firebase-analytics.js";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

import "https://www.gstatic.com/firebasejs/10.8.1/firebase-auth.js";
import "https://www.gstatic.com/firebasejs/10.8.1/firebase-firestore.js";

const firebaseConfig = {
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
  const analytics = getAnalytics(app);