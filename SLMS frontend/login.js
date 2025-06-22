let loginForm=document.getElementById("loginForm");

document.getElementById("forgot-password").addEventListener("click", ()=>{
  window.location.href='./forget-password.html';
});

document.getElementById("signUp").addEventListener("click", ()=>{
  window.location.href='./sign-up.html';
});

loginForm.addEventListener("submit", async (event) => {
  event.preventDefault(); // Prevent form from refreshing the page

  const email = document.getElementById("email");
  const password = document.getElementById("password");

  let response = await fetch("http://localhost:8080/user/loginApi", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
        email: email.value,
        password:password.value 
    })
  });
  email.value='';
  password.value='';
  if(response.status==200){
    window.location.replace('./after-login.html');
  }else{
    let paragraph=document.createElement('p');
    paragraph.textContent='Invalid Email or Password !!';
    paragraph.style.color='red';
    paragraph.classList.add('flag');
    event.target.appendChild(paragraph);
    setTimeout(()=>{
      let flag=document.querySelector(".flag");
      flag.outerHTML='';
    },2000);
  }
});




