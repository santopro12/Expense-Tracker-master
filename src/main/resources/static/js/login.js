async function login() {

    const email =
        document.getElementById("email").value;

    const password =
        document.getElementById("password").value;

    const response =
        await fetch(
            "http://localhost:8585/auth/login",
            {
                method:"POST",

                headers:{
                    "Content-Type":
                    "application/json"
                },

                body:JSON.stringify({
                    email,
                    password
                })
            });

    const result =
        await response.text();

    alert(result);

    if(result.includes("Success")){

        window.location =
            "dashboard.html";
    }
}