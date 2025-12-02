const form = document.querySelector('form');

form.addEventListener('submit', function(event) {
    event.preventDefault(); // prevent default form submission
     let id= form.querySelector('input[name="id"]').value.trim()

    const student = {
        name: form.querySelector('input[name="name"]').value.trim(),
        gender: form.querySelector('input[name="gender"]').value.trim(),
        email: form.querySelector('input[name="email"]').value.trim(),
        dept: form.querySelector('input[name="dept"]').value.trim(),
        phno: form.querySelector('input[name="phno"]').value.trim()
    };

    // Optional: validation
    if(id==null) {
        alert("Id is required to update a student!");
        return;
    }

    fetch(`/update?id=${id}`, {   // backend endpoint
        method: 'PUT',            // Use PUT for updating
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(student)
    })
    .then(res => res.json())
    .then(data => {
        if(data.success) {
            alert(data.message); // "Student updated successfully"
            form.reset();
        } else {
            alert('Error: ' + data.message); // Show backend error
        }
    })
    .catch(err => {
        console.error(err);
        alert('Error updating student. Check console for details.');
    });
});
