
        fetch("/admin/getAllEmployee")
        .then(response => response.json())
        .then(data => {
            console.log(data); // Log the entire response to verify structure

            const employeeTableBody = document.querySelector('#employeeTable tbody');
            
            data.forEach(employee => {
                // Iterate over the skills and create a comma-separated string
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
                
                let projects="";
                if(employee.assignedProject!=null){
                    projects=employee.assignedProject.proName;
                }
                if(employee.haveProject && employee.haveProject.length>0){
                     employee.haveProject.forEach(project=>{
                         projects+=project.proName+", ";
                     })
                     projects=projects.slice(0,-2);
                }
                if(projects==""){
                    projects="No Project Assigned";
                }
                // Create a new row and cells for each employee
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

            // Add back button below the table
            const backButton = document.createElement('button');
            backButton.textContent = 'Back';
            backButton.className = 'back-button';
            backButton.onclick = () => window.location.href = 'adminHome.html';
            document.body.appendChild(backButton);
        })
        .catch(error => console.error('Error fetching unassigned employees:', error));
    