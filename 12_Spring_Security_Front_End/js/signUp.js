document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("signupForm");

    form.addEventListener("submit", async function (event) {
        event.preventDefault();

        const username = document.getElementById("inputUsername").value.trim();
        const password = document.getElementById("inputPassword").value;
        const confirmPassword = document.getElementById("inputConfirmPassword").value;
        const role = document.getElementById("inputRole").value;

        // Validate inputs
        if (!username || !password || !confirmPassword || !role) {
            Swal.fire("Error", "Please fill out all fields.", "error");
            return;
        }

        if (password.length < 6) {
            Swal.fire("Error", "Password should be at least 6 characters.", "error");
            return;
        }

        if (password !== confirmPassword) {
            Swal.fire("Error", "Passwords do not match.", "error");
            return;
        }

        // Prepare payload
        const data = {
            username: username,
            password: password,
            role: role,
        };

        try {
            const response = await fetch("http://localhost:8080/auth/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(data),
            });

            if (!response.ok) {
                // Try to parse error message from backend
                let errorMsg = "Registration failed";
                try {
                    const errorData = await response.json();
                    if (errorData.message) errorMsg = errorData.message;
                } catch {
                    // ignore JSON parse errors
                }
                throw new Error(errorMsg);
            }

            // Success
            Swal.fire({
                icon: "success",
                title: "Account created!",
                text: "You will be redirected to the login page.",
                timer: 2000,
                showConfirmButton: false,
            }).then(() => {
                window.location.href = "../index.html";
            });
        } catch (error) {
            Swal.fire("Error", error.message || "Unable to register. Please try again.", "error");
        }
    });
});
