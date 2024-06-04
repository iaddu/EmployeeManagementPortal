
        fetch('/manager/myProfile')
            .then(response => response.json())
            .then(data => {
                const parentDiv = document.getElementById("manager");
                const manager = data;

                let skills = "";
                if (manager.haveSkills && manager.haveSkills.length > 0) {
                    manager.haveSkills.forEach(skill => {
                        skills += skill.skillName + ", ";
                    });
                     skills = skills.slice(0, -2);
                } else {
                    skills = "No skills available";
                }

                let projects = "No projects managed";
                if (manager.haveProject && manager.haveProject.length > 0) {
                    projects = "<ul class='projects-list'>";
                    manager.haveProject.forEach(project => {
                        projects += `<li>${project.proName}</li>`;
                    });
                    projects += "</ul>";
                }

                parentDiv.innerHTML = `
                    <h2>${manager.firstName} ${manager.lastName}</h2>
                    <p>Email: ${manager.email}</p>
                    <p>Address: ${manager.address}</p>
                    <p>Phone: ${manager.phone}</p>
                    <p>Gender: ${manager.gender}</p>
                    <p>Role: ${manager.role}</p>
                    <p>Date of Birth: ${manager.dob}</p>
                    <p>Skills: ${skills}</p>
                    <p>Projects:</p> ${projects}
                    <a href="#" class="back-button">Back</a>
                `;
                
                 const backButton = parentDiv.querySelector('.back-button');
                backButton.addEventListener('click', () => {
                    window.history.back();
                });
            })
            .catch(error => console.error('Error fetching manager data:', error));
    