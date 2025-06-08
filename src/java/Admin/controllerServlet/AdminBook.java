/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Admin.controllerServlet;

import Core.Entities.Book;
import Core.Interfaces.IBook;
import dao.BookDAO;
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
@WebServlet(name="AdminBook", urlPatterns={"/AdminBook"})
public class AdminBook extends HttpServlet {
   
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
        String title, author, isbn, category, status;
        int id, publishedYear, totalCopies, availableCopies;
        IBook book = new BookDAO();
        int get;
        try{
            String action = (String) request.getAttribute("Action");
            if (action == null) {
                action = request.getParameter("Action");
             }
            
            switch(action){
                case "Add":
                    title = request.getParameter("title");
                    author = request.getParameter("author");
                    isbn = request.getParameter("isbn");
                    category = request.getParameter("category");
                    publishedYear = Integer.parseInt(request.getParameter("publishedYear"));
                    totalCopies = Integer.parseInt(request.getParameter("totalCopies"));
                    availableCopies = Integer.parseInt(request.getParameter("availableCopies"));
                    status = request.getParameter("status");
                    get = book.AddBook(new Book(0, title, author, isbn, category, publishedYear, totalCopies, availableCopies, status));         
                    break;
                    
                case "Edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    title = request.getParameter("title");
                    author = request.getParameter("author");
                    isbn = request.getParameter("isbn");
                    category = request.getParameter("category");
                    publishedYear = Integer.parseInt(request.getParameter("publishedYear"));
                    totalCopies = Integer.parseInt(request.getParameter("totalCopies"));
                    availableCopies = Integer.parseInt(request.getParameter("availableCopies"));
                    status = request.getParameter("status");
                    get = book.EditBook(new Book(id, title, author, isbn, category, publishedYear, totalCopies, availableCopies, status));
                    break;
                    
                case "Delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    get = book.RemoveBook(id);
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
