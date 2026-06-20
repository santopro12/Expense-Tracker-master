async function saveExpense() {

    let expense = {
        title: document.getElementById("title").value,
        amount: document.getElementById("amount").value
    };

    await fetch("http://localhost:8080/expense", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(expense)
    });

    alert("Expense Saved");
}