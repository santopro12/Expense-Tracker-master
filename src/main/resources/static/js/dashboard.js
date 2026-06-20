console.log("dashboard.js loaded");

window.onload = function () {
    loadExpenses();
	loadDashboardStats();
	loadCategorySummary();
	loadPieChart();
};

async function loadExpenses() {

    const response = await fetch(
        "http://localhost:8585/expense"
    );

    const data = await response.json();

    let rows = "";

    data.forEach(expense => {

        rows += `
            <tr>
                <td>${expense.id}</td>
                <td>${expense.title}</td>
                <td>${expense.amount}</td>
                <td>${expense.category}</td>
                <td>${expense.expenseDate}</td>

                <td>
					<button
				    		class="btn btn-warning btn-sm"
				        	onclick="openEditModal(
				        		${expense.id},
				            	'${expense.title}',
				            	${expense.amount},
				            	'${expense.category}',
				            	'${expense.expenseDate}'
				        	)">
				        	Edit
				    	</button>
                    <button
                        class="btn btn-danger btn-sm"
                        onclick="deleteExpense(${expense.id})">
                        Delete
                    </button>
                </td>
            </tr>
        `;
    });

    document.getElementById("expenseTable").innerHTML = rows;
}

async function addExpense() {

    const expense = {

        title: document.getElementById("title").value,

        amount: parseFloat(
            document.getElementById("amount").value
        ),

        category: document.getElementById("category").value,

        expenseDate: document.getElementById("expenseDate").value
    };

    console.log(expense);

    const response = await fetch(
        "http://localhost:8585/expense",
        {
            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(expense)
        }
    );

    if (response.ok) {

        alert("Expense Added Successfully");

        loadExpenses();

        document.getElementById("title").value = "";
        document.getElementById("amount").value = "";
        document.getElementById("category").value = "";
        document.getElementById("expenseDate").value = "";

    } else {

        alert("Failed To Add Expense");

        console.log(await response.text());
    }
}
async function deleteExpense(id) {

    const confirmDelete =
        confirm("Are you sure you want to delete this expense?");

    if (!confirmDelete) {
        return;
    }

    const response = await fetch(
        `http://localhost:8585/expense/${id}`,
        {
            method: "DELETE"
        }
    );

    if (response.ok) {

        alert("Deleted Successfully");

        loadExpenses();

    } else {

        alert("Delete Failed");
    }
}
function openEditModal(
    id,
    title,
    amount,
    category,
    expenseDate
) {

    document.getElementById(
        "editId").value = id;

    document.getElementById(
        "editTitle").value = title;

    document.getElementById(
        "editAmount").value = amount;

    document.getElementById(
        "editCategory").value = category;

    document.getElementById(
        "editDate").value = expenseDate;

    const modal =
        new bootstrap.Modal(
            document.getElementById(
                "editModal")
        );

    modal.show();
}
async function updateExpense() {

    const id =
        document.getElementById(
            "editId").value;

    const expense = {

        title:
            document.getElementById(
                "editTitle").value,

        amount:
            document.getElementById(
                "editAmount").value,

        category:
            document.getElementById(
                "editCategory").value,

        expenseDate:
            document.getElementById(
                "editDate").value
    };

    const response =
        await fetch(
            `http://localhost:8585/expense/${id}`,
            {
                method: "PUT",

                headers: {
                    "Content-Type":
                    "application/json"
                },

                body:
                JSON.stringify(expense)
            }
        );

    if(response.ok){

        alert("Updated Successfully");

        loadExpenses();

        bootstrap.Modal
            .getInstance(
                document.getElementById(
                    "editModal"))
            .hide();

    }else{

        alert("Update Failed");
    }
}
async function loadDashboardStats() {

    const response =
        await fetch(
            "http://localhost:8585/dashboard/stats"
        );

    const data =
        await response.json();

    document.getElementById(
        "totalExpense").innerText =
        "₹" + (data.totalExpense || 0);

    document.getElementById(
        "foodExpense").innerText =
        "₹" + (data.foodExpense || 0);

    document.getElementById(
        "travelExpense").innerText =
        "₹" + (data.travelExpense || 0);
		
	document.getElementById("monthlyExpense")
		.innerText = "₹" + (data.monthlyExpense || 0);
}
async function loadCategorySummary() {

    const response =
        await fetch(
            "http://localhost:8585/dashboard/category-summary"
        );

    const data =
        await response.json();

    let rows = "";

    data.forEach(item => {

        rows += `
            <tr>
                <td>${item[0]}</td>
                <td>₹${item[1]}</td>
            </tr>
        `;
    });

    document.getElementById(
        "categorySummaryTable"
    ).innerHTML = rows;
}
async function loadPieChart() {

    const response =
        await fetch(
            "http://localhost:8585/dashboard/category-summary"
        );

    const data =
        await response.json();

    const labels = [];
    const amounts = [];

    data.forEach(item => {

        labels.push(item[0]);

        amounts.push(item[1]);
    });

    new Chart(
        document.getElementById(
            "expenseChart"
        ),
        {
            type: "pie",

            data: {

                labels: labels,

                datasets: [{
                    data: amounts
                }]
            }
        }
    );
}
function logout() {

    localStorage.clear();

    window.location.href =
        "login.html";
}