 
        async function validateForm(event) {
            event.preventDefault();
             if (!validateEmail() || !validatePhoneNumber() || !validateNames()) {
                return; 
            }

             const form = event.target;
            const formData = new FormData(form);
            const data = Object.fromEntries(formData.entries());

            try {
                const response = await fetch('/admin/registerEmp', {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                    body: new URLSearchParams(data)
                });

                if (response.ok) {
                    alert('Employee registered successfully.');
                    window.location.href = '/adminfiles/html/adminhome.html';
                } else {
                    const message = await response.text();
                    document.getElementById('error-message').innerText = message;
                }
            } catch (error) {
                console.error('Error occurred during form submission:', error);
                document.getElementById('error-message').innerText = 'An error occurred. Please try again later.';
            }
        }

        function validateEmail() {
            const emailField = document.getElementById("email");
            const email = emailField.value;
            const validDomains = ["@nucleusteq.com", "@gmail.com"];
            const isValid = validDomains.some(domain => email.endsWith(domain));

            if (!isValid) {
                alert("Email should end with @nucleusteq.com or @gmail.com");
                emailField.focus();
                return false;
            }

            return true;
        }

        function validatePhoneNumber() {
            const phoneField = document.getElementById("phone");
            const phone = phoneField.value;
            const isValid = /^\d{10}$/.test(phone);

            if (!isValid) {
                alert("Phone number should be exactly 10 digits");
                phoneField.focus();
                return false;
            }

            return true;
        }

        function validateNames() {
            const firstNameField = document.getElementById("first-name");
            const lastNameField = document.getElementById("last-name");
            const firstName = firstNameField.value;
            const lastName = lastNameField.value;

            if (!validateName(firstName)) {
                alert("First name should contain only letters");
                firstNameField.focus();
                return false;
            }

            if (!validateName(lastName)) {
                alert("Last name should contain only letters");
                lastNameField.focus();
                return false;
            }

            return true;
        }

        function validateName(name) {
            return /^[a-zA-Z]+$/.test(name);
        }
    