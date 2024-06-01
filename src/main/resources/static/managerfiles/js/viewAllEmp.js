 
        $(document).ready(function(){
            $("#myInput").on("keyup", function(){
                var value = $(this).val().toLowerCase();
                $("#myTable tr").filter(function(){
                    var skillsColumn = $(this).find('td:nth-child(9)').text().toLowerCase();
                    $(this).toggle(skillsColumn.indexOf(value) > -1);
                });
            });
            
            $("#filterButton").on("click", function() {
                var selectedOption = $("#filterOption").val();
                if (selectedOption === 'assigned') {
                	//console.log("inside assigned");
                    $("#myTable tr").filter(function() {
                        var projectsColumn = $(this).find('td:nth-child(10)').text().toLowerCase();
                       // console.log(projectsColumn);
                        $(this).toggle(projectsColumn != 'na');
                    });
                } else if (selectedOption === 'unassigned') {
                	//console.log("unassigned");
                    $("#myTable tr").filter(function() {
                        var projectsColumn = $(this).find('td:nth-child(10)').text().toLowerCase();
                        $(this).toggle(projectsColumn == 'na');
                    });
                    
                } else {
                	//console.log("all");
                   $("#myTable tr").show();
                }
            });
        });
   
        fetch("/manager/getAllEmployeeOnly")
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
                if(employee.assignedProject != null) {
                    projects = employee.assignedProject.proName;
                }
                if(employee.haveProject && employee.haveProject.length > 0) {
                    employee.haveProject.forEach(project => {
                        projects += project.proName + ", ";
                    });
                    projects = projects.slice(0, -2);
                }
                if(projects === "") {
                    projects = "NA";
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
        })
        .catch(error => console.error('Error fetching unassigned employees:', error));
    