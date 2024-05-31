
    async function deleteEmp() {
        const email = document.getElementById('email').value;
        const data = { email: email };
        const messageDiv = document.getElementById('message');
        
        try {
            // Send delete request
            const response = await fetch('/admin/deleteEmp', {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });
            
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            // Read response text
            const text = await response.text();

            // Check if the response is empty or null
            if (!text || text.trim() === '') {
                messageDiv.innerHTML = "No Employee Found!";
                messageDiv.className = 'error';
            } else {
                // If the response is not empty, it means an employee was found and deleted
                messageDiv.innerHTML = "Delete Successful";
                messageDiv.className = 'success';
            }
        } catch (error) {
            console.error('There was a problem with the fetch operation:', error);
            messageDiv.innerHTML = "Error occurred while deleting the employee!";
            messageDiv.className = 'error';
        }
    }
    