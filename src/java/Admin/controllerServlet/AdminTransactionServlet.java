/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Admin.controllerServlet;

import Core.Entities.Book;
import Core.Interfaces.IBook;
import dao.BookDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ChanRoi
 */
@WebServlet(name="AdminTransactionServlet", urlPatterns={"/AdminTransactionServlet"})
public class AdminTransactionServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String url = "AdminWeb/BookData.jsp";
        String getInput;
        try{
             String function = request.getParameter("function");
             if(function == null){
                 function = "MainTransaction";
             }
             switch(function){
                 case "MainTransaction":
                     url = "AdminWeb/BookData.jsp";
                     break;
                     
                case "SearchBook":
                    getInput = request.getParameter("SearchBook");
                    if (getInput == null || getInput.trim().isEmpty()){
                       url = "AdminWeb/BookData.jsp";
                    }else{
                       request.setAttribute("Search", "Book");
                       request.setAttribute("SearchBook", getInput.trim());
                       url = "AdminSearch";
                    }
                break;
                
                case "SearchUser":
                    getInput = request.getParameter("SearchUser");
                    if(getInput == null || getInput.trim().isEmpty()){
                        url = "AdminWeb/UserList.jsp";
                    }else{
                        request.setAttribute("Search", "User");
                        request.setAttribute("SearchUser", getInput.trim());
                        url = "AdminSearch";
                    }
                    break;
                
                case "Add":
                    request.setAttribute("Action", "Add");
                    url = "AdminBook";
                    break;
                
                case "Edit":
                    url = "AdminWeb/AdminBookEdit.jsp";
                    int id = Integer.parseInt(request.getParameter("id"));
                    IBook book = new BookDAO();
                    Book getBook = book.GetExistBook(id);
                    request.setAttribute("BookData", getBook);
                    break;
                    
                case "Delete":
                    request.setAttribute("Action", "Delete");
                    url = "AdminBook";
                    break;
                    
                case "Ban": case "Unban":
                    url = "AdminBanServlet";
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
