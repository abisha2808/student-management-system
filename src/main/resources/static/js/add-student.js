// Select the form
const form = document.querySelector('form');

form.addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent default form submission

    // Get form values
    const name = form.querySelector('input[name="name"]').value.trim();
    const gender = form.querySelector('input[name="gender"]').value;
    const email = form.querySelector('input[name="email"]').value;
    const department = form.querySelector('input[name="dept"]').value;
    const phone = form.querySelector('input[name="phno"]').value;

    // Create a student object
    const student = {
        name: name,
        gender: gender,
        email: email,
        dept: department,
        phno: phone
    };

    // Send POST request to the backend
    fetch('/save', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json' // Send JSON
        },
        body: JSON.stringify(student)
    })
    .then(res => res.json())
.then(data => {
    if(data.success) {
        alert(data.message); // "Student added successfully"
        form.reset();
    } else {
        alert('Error: ' + data.message); // "Email already exists..."
    }
})
    .catch(error => {
        console.error('Error:', error);
        alert('Error adding student.');
    });
});
