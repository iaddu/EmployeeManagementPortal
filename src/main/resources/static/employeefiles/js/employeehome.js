
    fetch('/emp/viewEmployee')
    .then(response => response.json())
    .then(data => { 
        const nameElement = document.getElementById("loggedUserName");
        const employee = data.employee;
        nameElement.textContent = `${employee.firstName} ${employee.lastName}`;
    })
    .catch(error => console.error('Error fetching employee data:', error));
    