
        async function showEmp() {
            const email = document.getElementById('email').value;
            const data = { email: email };
            try {
                const response = await fetch('/admin/viewEmployee', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data)
                });

                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }

                const employee = await response.json();

                const parentDiv = document.getElementById("employee");
                const errorMessage = document.getElementById("error-message");

                if (!employee) {
                    errorMessage.style.display = 'block';
                    parentDiv.innerHTML = '';
                } else {
                    errorMessage.style.display = 'none';
                    let skills = "";
                    if (employee.haveSkills && employee.haveSkills.length > 0) {
                        employee.haveSkills.forEach(skill => {
                            skills += skill.skillName + ", ";
                        });
                        skills = skills.slice(0, -2);
                    } else {
                        skills = "No skills available";
                    }

                    let projects = "No Project Assigned";
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
                    `;
                }
            } catch (error) {
                 const errorMessage = document.getElementById("error-message");
                errorMessage.style.display = 'block';
                errorMessage.textContent = 'No Such Employee Found!!:';
                const parentDiv = document.getElementById("employee");
                parentDiv.innerHTML = '';
            }
        }
