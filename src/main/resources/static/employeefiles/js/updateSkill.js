
fetch('/emp/viewEmployee')
    .then(response => response.json()) // Convert response to JSON
    .then(data => {
        const employee = data.employee;
        console.log(employee);
        document.getElementById('emp-id').value = employee.empId;
        document.getElementById('employee-name').innerText = `${employee.firstName} ${employee.lastName}`;
        document.getElementById('employee-email').innerText = `Email: ${employee.email}`;
 
        // Pre-check the skills
        if (employee.haveSkills && employee.haveSkills.length > 0) {
            employee.haveSkills.forEach(skill => {
                const skillCheckbox = document.querySelector(`input[name="skills"][value="${skill.skillName}"]`);
                if (skillCheckbox) {
                    skillCheckbox.checked = true;
                }
            });
        }
    })
    .catch(error => console.error('Error fetching employee:', error));

document.getElementById('update-skills-form').addEventListener('submit', function(event) {
    event.preventDefault();
    
    // Send the form data via fetch
    fetch('/emp/updateSkill', {
        method: 'POST',
        body: new FormData(this)
    }).then(response => {
        if (response.ok) {
            document.getElementById('successMessage').style.display = 'block';
        } else {
            alert('There was a problem with the request.');
        }
    }).catch(error => {
        console.error('Error:', error);
    });
});
