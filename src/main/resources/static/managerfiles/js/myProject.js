document.addEventListener('DOMContentLoaded', function() {
    fetch("/manager/myProject")
        .then(response => response.json())
        .then(data => {
            const projectEmployeeTableBody = document.querySelector('#projectEmployeeTable tbody');
            
            if (data.length === 0) { // Check if there are no projects
                const row = document.createElement('tr');
                const projectNameCell = document.createElement('td');
                projectNameCell.textContent = 'NA'; // Print 'NA' if there are no projects
                row.appendChild(projectNameCell);
                const employeeCell = document.createElement('td');
                employeeCell.textContent = ''; // Leave the employees cell empty
                row.appendChild(employeeCell);
                projectEmployeeTableBody.appendChild(row);
            } else {
                data.forEach(projectEmployeeDTO => {
                    const row = document.createElement('tr');
                    const projectNameCell = document.createElement('td');
                    projectNameCell.textContent = projectEmployeeDTO.projectName; 
                    row.appendChild(projectNameCell);

                    const employeeCell = document.createElement('td');
                    if (projectEmployeeDTO.employees.length === 0) {
                        employeeCell.textContent = 'No Employees Assigned'; // Handle case when there are no employees
                    } else {
                        const employeeList = projectEmployeeDTO.employees.map(employee => `${employee.firstName} ${employee.lastName}`).join(', ');
                        employeeCell.textContent = employeeList;
                    }
                    row.appendChild(employeeCell);
                    projectEmployeeTableBody.appendChild(row);
                });
            }
        })
        .catch(error => console.error('Error fetching project employees:', error));
});
