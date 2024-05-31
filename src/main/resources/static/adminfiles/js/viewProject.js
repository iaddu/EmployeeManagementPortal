
        async function showProject() {
            const proId = document.getElementById('proId').value;
            const data = { proId: proId };
            try {
                const response = await fetch('/admin/viewProject', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data)
                });

                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }

                const project = await response.json();

                const parentDiv = document.getElementById("project");
                const errorMessage = document.getElementById("error-message");

                if (!project) {
                    errorMessage.style.display = 'block';
                    parentDiv.innerHTML = '';
                } else {
                    errorMessage.style.display = 'none';

                    parentDiv.innerHTML = `
                        <h2>${project.proId}</h2>
                        <p>Name: ${project.proName}</p>
                    `;
                }
            } catch (error) {
                console.error('Error fetching project:', error);
                const errorMessage = document.getElementById("error-message");
                errorMessage.style.display = 'block';
                errorMessage.textContent = 'No Project Found!';
                const parentDiv = document.getElementById("project");
                parentDiv.innerHTML = '';
            }
        }
    