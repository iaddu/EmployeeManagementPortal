 
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
                     $("#myTable tr").filter(function() {
                        var projectsColumn = $(this).find('td:nth-child(10)').text().toLowerCase();
                         $(this).toggle(projectsColumn != 'na');
                    });
                } else if (selectedOption === 'unassigned') {
                     $("#myTable tr").filter(function() {
                        var projectsColumn = $(this).find('td:nth-child(10)').text().toLowerCase();
                        $(this).toggle(projectsColumn == 'na');
                    });
                    
                } else {
                    $("#myTable tr").show();
                }
            });
        });
   
        fetch("/manager/getAllEmployeeOnly")
        .then(response => response.json())
        .then(data => {
            console.log(data);  
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
    