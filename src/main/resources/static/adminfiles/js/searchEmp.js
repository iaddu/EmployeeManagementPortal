
    function searchEmp() {
        const email = document.getElementById('email').value;
        const data = { email: email };

        fetch('/admin/viewEmployee', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        })
        .then(response => response.json())
        .then(data => {
            const employee = data;
            document.getElementById('employee-form').style.display = 'block';

            document.getElementById('emp-id').value = employee.empId;
            document.getElementById('first-name').value = employee.firstName;
            document.getElementById('last-name').value = employee.lastName;
            document.getElementById('update-email').value = employee.email;
            document.getElementById('dob').value = employee.dob;
            document.getElementById('address').value = employee.address;
            document.getElementById('phone').value = employee.phone;

            document.querySelector(`input[name="gender"][value="${employee.gender}"]`).checked = true;
            document.querySelector(`input[name="role"][value="${employee.role}"]`).checked = true;

            const skills = document.querySelectorAll('input[name="skills"]');
            skills.forEach(skill => skill.checked = false); // Reset all checkboxes
            if (employee.haveSkills && employee.haveSkills.length > 0) {
                employee.haveSkills.forEach(skill => {
                    const skillCheckbox = document.querySelector(`input[name="skills"][value="${skill.skillName}"]`);
                    if (skillCheckbox) {
                        skillCheckbox.checked = true;
                    }
                });
            }
        })
        .catch(error => {
            console.error('Error fetching employee:', error);
            const errorMessage = document.getElementById("error-message");
            errorMessage.style.display = 'block';
            errorMessage.textContent = 'No employee found';
        });
    }
    