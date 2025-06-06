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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ChanRoi
 */
@WebServlet(name="AdminSearchBook", urlPatterns={"/AdminSearchBook"})
public class AdminSearchBook extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
         IBook book = new BookDAO();
         ArrayList<Book> getList = book.getBookList();
         String get = (String) request.getAttribute("SearchBook");
         String input = (get == null) ? "" : get.toLowerCase();

         List<Book> searchBook = getList.stream()
                 .filter(i -> i.getTitle().toLowerCase().contains(input)).collect(Collectors.toList());

         request.setAttribute("BookList", new ArrayList<>(searchBook));

        if (!searchBook.isEmpty()) {
            request.setAttribute("SearchTitle", "Here is the book list.");
        }else{
            request.setAttribute("SearchTitle", "Book is not existed.");
         }
    }catch(Exception e){
        e.printStackTrace();
    }finally{
        try{
            request.getRequestDispatcher("AdminWeb/BookData.jsp").forward(request, response);
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
