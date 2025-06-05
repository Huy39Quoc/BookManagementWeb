/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

    function BanUser(button) {
      if(button.innerText === "Ban"){
          button.innerText = "Unban";
      }else{
          button.innerText = "Ban";
      }
   }