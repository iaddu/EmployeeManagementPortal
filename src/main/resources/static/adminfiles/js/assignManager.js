
function assignManager() {
    const managerId = document.getElementById('managerId').value;
    const proId = document.getElementById('proId').value;
    const data = {
        managerId: managerId,
        proId: proId
    };
    fetch('/admin/assignManager', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => {
        if (response.ok) {
            console.log("success");
             alert("Manager assigned successfully!");
        } else {
             alert("Error assigning manager. Either manager or project not found.");
        }
    })
    .catch(error => console.error('Error fetching unassigned employees:', error));
}
