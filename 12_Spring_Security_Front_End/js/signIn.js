document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        const username = document.getElementById("inputEmail").value.trim();
        const password = document.getElementById("inputPassword").value;

        if (!username || !password) {
            alert("Please fill in both username and password.");
            return;
        }

        if (username === "admin@example.com" && password === "password123") {
            alert("Sign in successful!");
        } else {
            alert("Invalid username or password.");
        }
    });
});
