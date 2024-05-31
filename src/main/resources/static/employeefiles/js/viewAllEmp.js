
        fetch("/emp/getAllEmployee")
        .then(response => response.json())
        .then(data => {
            const employeeTableBody = document.querySelector('#employeeTable tbody');

            data.forEach(employee => {
                let skills = "";
                if (employee.haveSkills && employee.haveSkills.length > 0) {
                    employee.haveSkills.forEach(skill => {
                        skills += skill.skillName + ", ";
                    });
                    skills = skills.slice(0, -2);
                } else {
                    skills = "No skills available";
                }
                
                let projects = "";
                if (employee.assignedProject != null) {
                    projects = employee.assignedProject.proName;
                }
                if (employee.haveProject && employee.haveProject.length > 0) {
                    employee.haveProject.forEach(project => {
                        projects += project.proName + ", ";
                    });
                    projects = projects.slice(0, -2);
                }
                if (projects == "") {
                    projects = "No Project Assigned";
                }

                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${employee.empId}</td>
                    <td>${employee.firstName} ${employee.lastName}</td>
                    <td>${employee.email}</td>
                    <td>${employee.address}</td>
                    <td>${employee.phone}</td>
                    <td>${employee.gender}</td>
                    <td>${employee.role}</td>
                    <td>${employee.dob}</td>
                    <td>${skills}</td>
                    <td>${projects}</td>
                `;
                employeeTableBody.appendChild(row);
            });

            const backButton = document.createElement('button');
            backButton.textContent = 'Back';
            backButton.className = 'back-button';
            backButton.onclick = () => window.location.href = 'employeeHome.html';
            document.body.appendChild(backButton);
        })
        .catch(error => console.error('Error fetching unassigned employees:', error));
    