
        let promise = fetch("/admin/getAllProject");
        promise.then(response => response.json())
        .then(data => { 
            const parentDiv = document.getElementById("listProject");
            
             const table = document.createElement("table");
            
             const thead = document.createElement("thead");
            const headerRow = document.createElement("tr");
            
            const thId = document.createElement("th");
            thId.textContent = "Project Id";
            headerRow.appendChild(thId);
            
            const thName = document.createElement("th");
            thName.textContent = "Project Name";
            headerRow.appendChild(thName);
            
            thead.appendChild(headerRow);
            table.appendChild(thead);
            
             const tbody = document.createElement("tbody");
            
            data.forEach(project => {
                const row = document.createElement("tr");
                
                const tdId = document.createElement("td");
                tdId.textContent = project.proId;
                row.appendChild(tdId);
                
                const tdName = document.createElement("td");
                tdName.textContent = project.proName;
                row.appendChild(tdName);
                
                tbody.appendChild(row);
            });
            
            table.appendChild(tbody);
            parentDiv.appendChild(table);
        })
        .catch(error => console.error('Error fetching projects:', error));
    