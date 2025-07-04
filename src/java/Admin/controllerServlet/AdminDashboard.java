 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Admin.controllerServlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ChanRoi
 */
@WebServlet(name="AdminDashboard", urlPatterns={"/AdminDashboard"})
public class AdminDashboard extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String url = "AdminWeb/MainAdminPage.jsp";
        try{
            String changeFile = request.getParameter("file");
            if(changeFile == null){
                changeFile = "MainPage";
            }
            switch(changeFile){
                case "MainPage":
                    url = "AdminWeb/MainAdminPage.jsp";
                    break;
                    
                case "Manage":
                    break;
                    
                case "Transaction":
                    url = "AdminTransactionServlet";
                    break;
                    
                case "Access":
                    url = "AdminWeb/UserList.jsp";
                    break;
                    
                case "Request":
                    url = "AdminWeb/ApprovalRequest.jsp";
                    break;
                    
                case "Overdue":
                    url = "AdminWeb/Overdue.jsp";
                    break;
                    
                case "Inventory":
                    break;
                    
                case "Statistic":
                    url = "AdminWeb/AdminStatistic.jsp";
                    break;
                    
                case "System":
                    url = "AdminWeb/System_config.jsp";
                    break;
                    
                case "Logout":
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                request.getRequestDispatcher(url).forward(request, response);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
