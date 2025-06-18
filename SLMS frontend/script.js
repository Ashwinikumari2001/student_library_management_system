document.getElementById("loginForm").addEventListener("click", async (event) => {
  event.preventDefault(); // Prevent form from refreshing the page

  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;
  console.log(email);
  console.log(password);
  
  

  let response = await fetch("http://localhost:8080/user/loginApi", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
        email: email,
        password:password })
  });
    
  if(response.status==200){
    let loginContainer=document.querySelector('.login-container');
    loginContainer.style.display='none';
    let afterLogin=document.querySelector('#after-login');
    afterLogin.style.display='block'
  }
  
});
