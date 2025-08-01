document.addEventListener("DOMContentLoaded", function () {
    // Utility: Parse JWT token safely
    function parseJwt(token) {
        console.log("Token from localStorage:", token);
        if (!token) {
            throw new Error("No token provided to parseJwt");
        }

        const parts = token.split('.');
        if (parts.length !== 3) {
            throw new Error("Invalid JWT token format");
        }

        const base64Url = parts[1];
        const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        const jsonPayload = decodeURIComponent(
            atob(base64)
                .split('')
                .map(c => '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2))
                .join('')
        );

        return JSON.parse(jsonPayload);
    }

    // Utility: Redirect user to login with message
    function redirectToLogin(message) {
        Swal.fire({
            icon: 'warning',
            title: 'Authentication Required',
            text: message,
            confirmButtonText: 'Login'
        }).then(() => {
            localStorage.clear();
            window.location.href = "../index.html";
        });
    }

    // Get token from localStorage
    const token = localStorage.getItem('token');
    if (!token) {
        console.error("No token found in localStorage!");
        return redirectToLogin("Please log in to continue.");
    }

    // Parse and validate token
    let payload;
    try {
        payload = parseJwt(token);
    } catch (error) {
        console.error("Token parsing error:", error);
        return redirectToLogin("Invalid token. Please log in again.");
    }

    const now = Math.floor(Date.now() / 1000);
    if (!payload.exp || payload.exp < now) {
        return redirectToLogin("Session expired. Please log in again.");
    }

    // Display username
    const username = localStorage.getItem("username") || payload.sub || "User";
    const userLabel = document.getElementById("usernameDisplay");
    if (userLabel) {
        userLabel.textContent = username;
    }
    else  {
       console.warn("Username label not found in DOM. Please check your HTML markup.");
    }

    // -------- JOB MANAGEMENT --------
    let jobs = [
        {
            id: 1,
            title: "Frontend Developer",
            company: "Tech Solutions Inc.",
            location: "Colombo, Sri Lanka",
            type: "Full-time",
            status: "Active",
            description: "We are looking for a skilled Frontend Developer to join our team."
        },
        {
            id: 2,
            title: "Backend Engineer",
            company: "Data Systems",
            location: "Remote",
            type: "Full-time",
            status: "Active",
            description: "Join our backend team to build scalable APIs and services."
        },
        {
            id: 3,
            title: "UI/UX Designer",
            company: "Creative Minds",
            location: "Kandy, Sri Lanka",
            type: "Part-time",
            status: "Inactive",
            description: "Looking for a creative designer to revamp our user interfaces."
        }
    ];

    // Render jobs table rows
    function renderJobs(jobsToRender) {
        const tbody = $('#jobsTableBody');
        tbody.empty();

        jobsToRender.forEach((job, index) => {
            const statusClass = job.status === "Active" ? "badge-active" : "badge-inactive";
            const row = `
        <tr>
          <td>${index + 1}</td>
          <td>${job.title}</td>
          <td>${job.company}</td>
          <td>${job.location}</td>
          <td>${job.type}</td>
          <td><span class="badge badge-status ${statusClass}">${job.status}</span></td>
          <td>
            <button class="btn btn-sm btn-action btn-edit" data-id="${job.id}" data-bs-toggle="modal" data-bs-target="#editJobModal">
              <i class="fas fa-edit"></i>
            </button>
            <button class="btn btn-sm btn-action btn-toggle" data-id="${job.id}">
              <i class="fas fa-power-off"></i>
            </button>
            <button class="btn btn-sm btn-action btn-delete" data-id="${job.id}">
              <i class="fas fa-trash"></i>
            </button>
          </td>
        </tr>
      `;
            tbody.append(row);
        });
    }

    renderJobs(jobs);

    // Search filter
    $('#searchInput').on('keyup', function () {
        const searchTerm = $(this).val().toLowerCase();
        const filteredJobs = jobs.filter(job =>
            job.title.toLowerCase().includes(searchTerm) ||
            job.company.toLowerCase().includes(searchTerm) ||
            job.location.toLowerCase().includes(searchTerm) ||
            job.type.toLowerCase().includes(searchTerm)
        );
        renderJobs(filteredJobs);
    });

    // Add new job
    $('#addJobForm').on('submit', function (e) {
        e.preventDefault();

        const newJob = {
            id: jobs.length + 1,
            title: $('#jobTitle').val(),
            company: $('#companyName').val(),
            location: $('#jobLocation').val(),
            type: $('#jobType').val(),
            status: "Active",
            description: $('#jobDescription').val()
        };

        jobs.push(newJob);
        renderJobs(jobs);

        $(this).trigger('reset');
        $('#addJobModal').modal('hide');

        Swal.fire({
            icon: 'success',
            title: 'Job Added!',
            text: 'The new job posting has been added successfully.',
            timer: 2000,
            showConfirmButton: false
        });
    });

    // Edit job - fill modal fields
    $(document).on('click', '.btn-edit', function () {
        const jobId = $(this).data('id');
        const job = jobs.find(j => j.id === jobId);

        if (job) {
            $('#editJobId').val(job.id);
            $('#editJobTitle').val(job.title);
            $('#editCompanyName').val(job.company);
            $('#editJobLocation').val(job.location);
            $('#editJobType').val(job.type);
            $('#editJobDescription').val(job.description);
        }
    });

    // Update job after edit
    $('#editJobForm').on('submit', function (e) {
        e.preventDefault();

        const jobId = parseInt($('#editJobId').val());
        const jobIndex = jobs.findIndex(j => j.id === jobId);

        if (jobIndex !== -1) {
            jobs[jobIndex] = {
                ...jobs[jobIndex],
                title: $('#editJobTitle').val(),
                company: $('#editCompanyName').val(),
                location: $('#editJobLocation').val(),
                type: $('#editJobType').val(),
                description: $('#editJobDescription').val()
            };

            renderJobs(jobs);
            $('#editJobModal').modal('hide');

            Swal.fire({
                icon: 'success',
                title: 'Job Updated!',
                text: 'The job posting has been updated successfully.',
                timer: 2000,
                showConfirmButton: false
            });
        }
    });

    // Toggle job status Active/Inactive
    $(document).on('click', '.btn-toggle', function () {
        const jobId = $(this).data('id');
        const jobIndex = jobs.findIndex(j => j.id === jobId);

        if (jobIndex !== -1) {
            jobs[jobIndex].status = jobs[jobIndex].status === "Active" ? "Inactive" : "Active";
            renderJobs(jobs);

            Swal.fire({
                icon: 'success',
                title: 'Status Updated!',
                text: 'The job status has been changed.',
                timer: 1500,
                showConfirmButton: false
            });
        }
    });

    // Delete job
    $(document).on('click', '.btn-delete', function () {
        const jobId = $(this).data('id');

        Swal.fire({
            title: 'Are you sure?',
            text: "This job will be permanently deleted.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                jobs = jobs.filter(j => j.id !== jobId);
                renderJobs(jobs);

                Swal.fire('Deleted!', 'The job has been removed.', 'success');
            }
        });
    });

});
