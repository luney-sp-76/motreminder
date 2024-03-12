firebase.auth().onAuthStateChanged(function (user) {
  var loginLink = document.getElementById("loginLink");
  if (user) {
    // User is signed in, change to "Log Out" and style it
    loginLink.textContent = "Log Out";
    loginLink.classList.add("green-link"); // Add the green class
    loginLink.href = "javascript:void(0)"; // Remove the link to "/login"
    loginLink.onclick = function () {
      firebase
        .auth()
        .signOut()
        .then(function () {
          // Sign-out successful, redirect to home page or do something else
          window.location.href = "/";
        })
        .catch(function (error) {
          // An error happened during sign out
          console.error("Sign Out Error", error);
        });
    };
  } else {
    // No user is signed in, ensure it says "Log In" and links to the login page
    loginLink.textContent = "Log In";
    loginLink.classList.remove("green-link"); // Remove the green class if present
    loginLink.href = "/login"; // Ensure the link goes to "/login"
    // Remove onclick event listener if it exists
    loginLink.onclick = null;
  }
});
