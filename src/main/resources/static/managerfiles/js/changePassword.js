 
        document.getElementById('passwordForm').addEventListener('submit', function(event) {
            event.preventDefault();
            
            // Send the form data via fetch
            fetch('/manager/changePassword', {
                method: 'POST',
                body: new FormData(this)
            }).then(response => {
                if (response.ok) {
                    document.getElementById('successMessage').style.display = 'block';
                } else {
                    alert('There was a problem with the request.');
                }
            }).catch(error => {
                console.error('Error:', error);
            });
        });
    