
fetch('/emp/viewEmployee')
    .then(response => response.json())
    .then(data => { 
        const parentDiv = document.getElementById("employee");
        const employee = data.employee;
        const managerName = data.managerName;
        let skills = "";
        if (employee.haveSkills && employee.haveSkills.length > 0) {
            employee.haveSkills.forEach(skill => {
                skills += skill.skillName + ", ";
            });
            // Remove the trailing comma and space
            skills = skills.slice(0, -2);
        } else {
            skills = "No skills available";
        }

        let projects = "NA";
        if (employee.assignedProject != null) {
            projects = employee.assignedProject.proName;
        }
        parentDiv.innerHTML = `
            <h2>${employee.firstName} ${employee.lastName}</h2>
            <p>Email: ${employee.email}</p>
            <p>Address: ${employee.address}</p>
            <p>Phone: ${employee.phone}</p>
            <p>Gender: ${employee.gender}</p>
            <p>Role: ${employee.role}</p>
            <p>Date of Birth: ${employee.dob}</p>
            <p>Skills: ${skills}</p>
            <p>Projects: ${projects}</p>
            <p>Manager: ${managerName}</p>
            <a href="#" class="back-button">Back</a>
        `;
        
        // Add event listener to back button
        const backButton = parentDiv.querySelector('.back-button');
        backButton.addEventListener('click', () => {
            window.history.back();
        });
    })
    .catch(error => console.error('Error fetching employee data:', error));
