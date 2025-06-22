document.getElementById("resetForm").addEventListener("submit",async (event)=>{
  event.preventDefault();
  const name=document.getElementById("name").value;
  const email=document.getElementById("resetemail").value;
  const password=document.getElementById("resetpassword").value;

  let response=await fetch(`http://localhost:8080/user/update-user`,{
    method:"PUT",
    headers:{
      "Content-Type":"application/json"
    },
    body: JSON.stringify({
      name:name,
      email:email,
      password:password })
  });
  let paragraph=document.createElement('p');
  let mainDiv=document.querySelector('.forgot-password-container');
    if(response.status==200){
      paragraph.textContent='User Details Reset Successfully';
      paragraph.style.color='green';
    }else{
      paragraph.textContent='User Details Reset Failed';
      paragraph.style.color='red';
    }
    mainDiv.appendChild(paragraph);
    paragraph.classList.add('flag');
    setTimeout(()=>{
        document.querySelector('.flag').outerHTML='';
        window.location.href='./login.html';
    },3000);
});