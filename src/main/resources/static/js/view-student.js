const searchForm = document.getElementById("searchForm");
const resultBox = document.getElementById("result-box");

// Initially keep search bar centered
searchForm.classList.add("center-mode");

// When search happens → move form up
function onSearchStart() {
    searchForm.classList.remove("center-mode");
    resultBox.style.display = "flex";
}

document.getElementById("searchType").addEventListener("change", showFields);

function showFields() {
    let type = document.getElementById("searchType").value;
    let box = document.getElementById("inputFields");

    box.innerHTML = ""; // clear previous

    switch (type) {
        case "id":
            box.innerHTML = `<input id="idVal" placeholder="Enter ID">`;
            break;
        case "email":
            box.innerHTML = `<input id="emailVal" placeholder="Enter Email">`;
            break;
        case "phone":
            box.innerHTML = `<input id="phoneVal" placeholder="Enter Phone">`;
            break;
        case "dept":
            box.innerHTML = `<input id="deptVal" placeholder="Enter Department">`;
            break;
        case "name":
            box.innerHTML = `<input id="nameVal" placeholder="Enter Name">`;
            break;
        case "nameGender":
            box.innerHTML = `
                <input id="nameVal" placeholder="Enter Name">
                <input id="genderVal" placeholder="Enter Gender">
            `;
            break;
        case "nameDept":
            box.innerHTML = `
                <input id="nameVal" placeholder="Enter Name">
                <input id="deptVal" placeholder="Enter Department">
            `;
            break;
        case "deptGender":
            box.innerHTML = `
                <input id="genderVal" placeholder="Enter Gender">
                <input id="deptVal" placeholder="Enter department">
            `;
            break;
        case "all3":
            box.innerHTML = `
                <input id="nameVal" placeholder="Enter Name">
                <input id="genderVal" placeholder="Enter Gender">
                <input id="deptVal" placeholder="Enter Department">
            `;
            break;
        case "phoneStart":
            box.innerHTML = `<input id="startVal" placeholder="Phone starts with...">`;
            break;
        case "nameContains":
            box.innerHTML = `<input id="containsVal" placeholder="Name contains...">`;
            break;
    }
}

document.getElementById("searchBtn").addEventListener("click", searchStudent);

function searchStudent() {

    onSearchStart(); // <-- move search bar up

    let type = document.getElementById("searchType").value;
    let url = "";

    let inputs = document.querySelectorAll("#inputFields input");
    for (let i of inputs) {
        if (i.value.trim() === "") {
            showError("Please provide valid input.");
            return;
        }
    }

    switch (type) {
        case "id":
            url = `/getById?id=${idVal.value}`;
            break;
        case "all":
            url = `/getAll`;
            break;
        case "email":
            url = `/getByEmail?email=${emailVal.value}`;
            break;
        case "phone":
            url = `/getByPhno?phno=${phoneVal.value}`;
            break;
        case "dept":
            url = `/getByDept?dept=${deptVal.value}`;
            break;
        case "name":
            url = `/getByName?name=${nameVal.value}`;
            break;
        case "nameGender":
            url = `/getByNameAndGender?name=${nameVal.value}&gender=${genderVal.value}`;
            break;
        case "nameDept":
            url = `/getByNameAndDept?name=${nameVal.value}&dept=${deptVal.value}`;
            break;
        case "deptGender":
            url = `/getByGenderAndDept?gender=${genderVal.value}&dept=${deptVal.value}`;
            break;
        case "all3": 
   			url = `/getByNameGenderAndDept?name=${nameVal.value}&gender=${genderVal.value}&dept=${deptVal.value}`; 
   			break; 
		case "phoneStart": 
   			url = `/getByPhnoFirstDigit?firstDigit=${startVal.value}`; 
   			break; 
		case "nameContains": 
   			url = `/getByNameContainingString?name=${containsVal.value}`; 
			break; 

    }

    fetch(url)
    .then(res => res.json())
    .then(result => {
        let box = document.getElementById("result-box");
        box.style.display = "flex";

        if (!result.success || !result.data) {
            box.innerHTML = `
                <div class="no-result-card">
                    <h3>No Student Found</h3>
                    <p>${result.message || "Try a different search."}</p>
                </div>
            `;
            return;
        }

        let students = result.data;

        if (!Array.isArray(students)) students = [students];

        let html = "";

        students.forEach(s => {
            html += `
                <div class="student-card">
                    <h2>${s.name}</h2>
                    <p><strong>ID:</strong> ${s.id}</p>
                    <p><strong>Email:</strong> ${s.email}</p>
                    <p><strong>Gender:</strong> ${s.gender}</p>
                    <p><strong>Department:</strong> ${s.dept}</p>
                    <p><strong>Phone:</strong> ${s.phno}</p>
                    
                    <button class="delete-btn" onclick="deleteStudent(${s.id})">Delete</button>
                </div>
            `;
        });

        box.innerHTML = html;

    })
    .catch(() => {
        showError("Something went wrong. Try again.");
    });

}

function deleteStudent(id) {
    if (!confirm("Are you sure you want to delete this student?")) {
        return;
    }

    fetch(`/deleteById?id=${id}`, {
        method: "DELETE"
    })
    .then(res => res.json())
    .then(result => {
        if (result.success) {
            alert("Student deleted successfully!");

            // remove card instantly
            searchStudent(); // refresh the page with updated list
        } else {
            alert(result.message);
        }
    })
    .catch(() => {
        alert("Something went wrong while deleting.");
    });
}

