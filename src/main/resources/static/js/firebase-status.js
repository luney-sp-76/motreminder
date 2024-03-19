// firebase-status.js
import { auth } from './firebase-init.js'; // Adjust path as necessary

const adjustLoginLogoutLink = () => {
  const loginLink = document.getElementById("loginLink");
  const setDateLink = document.getElementById("setdateLink"); // Get the "Reminder" link
  const accountLink = document.getElementById("accountLink"); // Get the "Account" link
  if (!loginLink) return; // Exit if loginLink doesn't exist on the page

  auth.onAuthStateChanged(user => {
    if (user) {
      // User is signed in, adjust to "Log Out"
      loginLink.textContent = "Log Out";
      loginLink.classList.add("green-link"); // Add the green class for styling
      loginLink.href = "javascript:void(0)";
      loginLink.onclick = () => {
        auth.signOut().then(() => {
          window.location.href = "/";
        }).catch(error => {
          console.error("Sign Out Error", error);
        });
      };
      if (setDateLink) {
        setDateLink.parentElement.style.display = ""; // Ensure the "Reminder" link is visible
      }
      if (accountLink) {
        accountLink.parentElement.style.display = "";  //  Ensure the  "Account" link is visible
      }
    } else {
      // No user is signed in, adjust to "Log In"
      loginLink.textContent = "Log In";
      loginLink.classList.remove("green-link"); // Remove the green class if present
      loginLink.href = "/login";
      loginLink.onclick = null; // Remove any previously set onclick event
      if (setDateLink) {
        setDateLink.parentElement.style.display = "none"; // Hide the "Reminder" link
      }
      if (accountLink) {
        accountLink.parentElement.style.display = "none"; // Hide the "Account" link
      }
    }
  });
};

export { adjustLoginLogoutLink };
