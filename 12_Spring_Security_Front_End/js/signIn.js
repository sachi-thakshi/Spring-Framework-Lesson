document.addEventListener("DOMContentLoaded", function () {
    const form = document.querySelector("form");

    form.addEventListener("submit", async function (event) {
        event.preventDefault();

        const username = document.getElementById("inputUsername").value.trim();
        const password = document.getElementById("inputPassword").value;

        if (!username || !password) {
            Swal.fire({
                icon: 'warning',
                title: 'Missing Fields',
                text: 'Please fill in both username and password.'
            });
            return;
        }

        try {
            const response = await fetch("http://localhost:8080/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ username, password })
            });

            if (!response.ok) {
                throw new Error("Invalid credentials or server error");
            }

            const data = await response.json();
            console.log("Login response:", data);

            // Store token and username
            localStorage.setItem("token", data.data.accessToken);
            localStorage.setItem("username", data.data.username);
            console.log(data.username);

            // Success message
            Swal.fire({
                icon: 'success',
                title: 'Signed in successfully!',
                showConfirmButton: false,
                timer: 1500
            }).then(() => {
                // Redirect to dashboard
                window.location.href = "pages/dashboard.html";
            });

        } catch (error) {
            console.error("Login failed:", error);
            Swal.fire({
                icon: 'error',
                title: 'Sign in failed',
                text: error.message || "Something went wrong"
            });
        }
    });
});
