/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

 function BanUser(userId, status, button) {
  console.log("User ID:", userId);
  console.log("Status:", status);
  button.innerText = status === "active" ? "Unban" : "Ban";
}