async function registerUser() {

    const user = {

        name:
            document.getElementById("name").value,

        email:
            document.getElementById("email").value,

        password:
            document.getElementById("password").value,

        role:
            document.getElementById("role").value
    };

    const response = await fetch(
        "http://localhost:8585/auth/register",
        {
            method: "POST",

            headers: {
                "Content-Type": "application/json"
            },

            body: JSON.stringify(user)
        }
    );

    if(response.ok){

        alert("Registration Successful");

        window.location.href =
            "login.html";

    } else {

        alert("Registration Failed");
    }
}