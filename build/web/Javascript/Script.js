/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

    function BanUser(id, status button) {
   const confirmAction = confirm("Are you sure to " + button.innerText + " " + name + "?");
   if(confirmAction){
      if(button.innerText === "Ban"){
         button.innerText = "Unban";
      } else {
         button.innerText = "Ban";
      }
      
      fetch("AdminTransactionServlet", {
          method: "POST",
          headers: {
              "Content-Type": "application/x-www-form-urlencoded"
          },
          body: "id=" + encodeURIComponent(id) + "function=" + encodeURIComponent("Status")
      }).then(response => response.text))
              .then(data => {
              button.innerText = (action === "Ban") ? "Unban" : "Ban";
              document.getElementById
      }
    }
}