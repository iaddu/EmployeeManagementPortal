document.addEventListener('DOMContentLoaded', function() {
    fetch("/manager/myProject")
        .then(response => response.json())
        .then(data => {
            const projectEmployeeTableBody = document.querySelector('#projectEmployeeTable tbody');
            
            if (data.length === 0) { 
                const row = document.createElement('tr');
                const projectIdCell = document.createElement('td');
                projectIdCell.textContent = 'NA';  
                row.appendChild(projectIdCell);
                const projectNameCell = document.createElement('td');
                projectNameCell.textContent = 'NA';  
                row.appendChild(projectNameCell);
                const employeeCell = document.createElement('td');
                employeeCell.textContent = ''; 
                row.appendChild(employeeCell);
                projectEmployeeTableBody.appendChild(row);
            } else {
                data.forEach(projectEmployeeDTO => {
                    const row = document.createElement('tr');
                    
                    const projectIdCell = document.createElement('td');
                    projectIdCell.textContent = projectEmployeeDTO.projectId; // Assuming projectId is a property of projectEmployeeDTO
                    row.appendChild(projectIdCell);

                    const projectNameCell = document.createElement('td');
                    projectNameCell.textContent = projectEmployeeDTO.projectName; 
                    row.appendChild(projectNameCell);

                    const employeeCell = document.createElement('td');
                    if (projectEmployeeDTO.employees.length === 0) {
                        employeeCell.textContent = 'No Employees Assigned';  
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
