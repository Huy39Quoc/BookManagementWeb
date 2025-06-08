/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Core.Entities.Book;
import Core.Entities.BookRequest;
import Core.Entities.BorrowRecord;
import Core.Interfaces.IBook;
import JDBC.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ChanRoi
 */
public class BookDAO implements IBook {
    public ArrayList<Book> booksList = new ArrayList();
    public ArrayList<BorrowRecord> Borrow = new ArrayList();
    public ArrayList<BookRequest> Request = new ArrayList();
    public BookDAO(){
        Connection cn = null;
        try{
            cn = DBUtils.getConnection();
            if(cn != null){
                booksList.clear();
                String sql = "SELECT id, title, author, isbn, category, published_year, total_copies, available_copies, status "
                        + "FROM books";
                PreparedStatement st = cn.prepareCall(sql);
                ResultSet rs = st.executeQuery();
                if(rs != null){
                    while(rs.next()){
                      int id = rs.getInt("id");
                      String title = rs.getString("title");
                      String author = rs.getString("author");
                      String isbn = rs.getString("isbn");
                      String category = rs.getString("category");
                      int PublishedYear = rs.getInt("published_year");
                      int TotalCopies = rs.getInt("total_copies");
                      int AvailableCopies = rs.getInt("available_copies");
                      String Status = rs.getString("status");
                      booksList.add(new Book(id, title, author, isbn, category, PublishedYear, TotalCopies, AvailableCopies, Status));
                    }
                }
                
                String sqlBorrow = "SELECT BR.id, U.name AS UserName, B.title AS BookTitle"
                        + ", BR.borrow_date, BR.due_date, BR.return_date, BR.status FROM borrow_records BR "
                        + "JOIN users U ON BR.user_id = U.id "
                        + "JOIN books B ON BR.book_id = B.id";
                st = cn.prepareCall(sqlBorrow);
                rs = st.executeQuery();
                if(rs != null){
                    Borrow.clear();
                    while(rs.next()){
                      int id = rs.getInt("id");
                      String UserName = rs.getString("UserName");
                      String BookTitle = rs.getString("BookTitle");
                      Date borrow_date = rs.getDate("borrow_date");
                      Date due_date = rs.getDate("due_date");
                      Date return_date = rs.getDate("return_date");
                      String status  = rs.getString("status");
                      Borrow.add(new BorrowRecord(id, UserName, BookTitle, borrow_date, due_date, return_date, status));
                    }
                }
                
                String sqlRequest = "SELECT BR.id, U.name AS UserName, B.title AS BookTitle"
                        + ", BR.request_date, BR.status FROM book_requests BR "
                        + "JOIN users U ON BR.user_id = U.id JOIN books B ON BR.book_id = B.id";
                st = cn.prepareCall(sqlRequest);
                rs = st.executeQuery();
                if(rs != null){
                    Request.clear();
                    while(rs.next()){
                      int id = rs.getInt("id");
                      String UserName = rs.getString("UserName");
                      String BookTitle = rs.getString("BookTitle");
                      Date request_date = rs.getDate("request_date");
                      String status  = rs.getString("status");
                      Request.add(new BookRequest(id, UserName, BookTitle, request_date, status));
                    }
                }
            }
        }catch(Exception e){
             e.printStackTrace();
        }finally{
            try{
                if(cn != null){
                    cn.close();
                }
            }catch(Exception e){
                    e.printStackTrace();
            }
        }
    }
    
    public ArrayList<Book> getBookList(){
        return booksList;
    }
    
    public int AddBook(Book book){
        int request = 0;
        Connection cn = null;
        if(GetExistBook(book) != null){
            return request;
        }
        try{
            cn = DBUtils.getConnection();
            if(cn != null){
                String sql = "INSERT INTO books (title, author, isbn, category, published_year, total_copies, available_copies, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = cn.prepareStatement(sql);
                int PublishedYear = book.getPublishedYear(), TotalCopies = book.getTotalCopies(), AvailableCopies = book.getAvailableCopies();
                String Title = book.getTitle(), Author = book.getAuthor(), ISBN = book.getISBN(), Category = book.getCategory(), Status = book.getStatus();
                ps.setString(1, Title);
                ps.setString(2, Author);
                ps.setString(3, ISBN);
                ps.setString(4, Category);
                ps.setInt(5, PublishedYear);
                ps.setInt(6, TotalCopies);
                ps.setInt(7, AvailableCopies);
                ps.setString(8, Status);
                request = ps.executeUpdate();
                if(request > 0){
                    ResultSet rs = ps.getGeneratedKeys();
                    if(rs.next()){
                        int Id = rs.getInt("id");
                        booksList.add(new Book(Id, Title, Author, ISBN, Category, PublishedYear, TotalCopies, AvailableCopies, Status));
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(cn != null){
                    cn.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return request;
    }
    
    public int EditBook(Book book){
        Book previous;
        int request = 0;
        int id = book.getId();
        Connection cn = null;
        if(GetExistBook(id) == null){
            return request;
        }else{
            previous = GetExistBook(id);
        }
        String title = book.getTitle().equals("") ? previous.getTitle() : book.getTitle();
        String author = book.getAuthor().equals("") ? previous.getAuthor() : book.getAuthor();
        String isbn = book.getISBN().equals("") ? previous.getISBN() : book.getISBN();
        String category = book.getCategory().equals("") ? previous.getCategory() : book.getCategory();
        int publishedYear = book.getPublishedYear();
        int totalCopies = book.getTotalCopies();
        int availableCopies = book.getAvailableCopies();
        String status = book.getStatus().equals("") ? previous.getStatus() : book.getStatus();
        
        try{
            cn = DBUtils.getConnection();
            if(cn != null){
                String sql = "UPDATE books SET title = ?, author = ?, isbn = ?, category = ?"
                        + ", published_year = ?, total_copies = ?, available_copies = ?, status = ? "
                        + "WHERE id = ?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, title);
                ps.setString(2, author);
                ps.setString(3, isbn);
                ps.setString(4, category);
                ps.setInt(5, publishedYear);
                ps.setInt(6, totalCopies);
                ps.setInt(7, availableCopies);
                ps.setString(8, status);
                ps.setInt(9, id);
                request = ps.executeUpdate();
                if(request > 0){
                    for(Book edit: booksList){
                        if(edit.getId() == id){
                            edit.setTitle(title);
                            edit.setAuthor(author);
                            edit.setISBN(isbn);
                            edit.setCategory(category);
                            edit.setPublishedYear(publishedYear);
                            edit.setTotalCopies(totalCopies);
                            edit.setAvailableCopies(availableCopies);
                            edit.setStatus(status);
                            break;
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(cn != null){
                    cn.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return request;
    }
    
    public int RemoveBook(int id){
        int request = 0;
        Connection cn = null;
        try{
            cn = DBUtils.getConnection();
            if(cn != null){
                String sql = "DELETE FROM books WHERE id = ?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setInt(1, id);
                request = ps.executeUpdate();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(cn != null){
                    cn.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return request;
    }
    
    public Book GetExistBook(int id){
        Book check = null;
        Connection cn = null;
        try{
            cn = DBUtils.getConnection();
            if(cn != null){
                String sql = "SELECT * FROM books WHERE id = ?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if(rs != null && rs.next()){
                    check = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("isbn")
                            , rs.getString("category"), rs.getInt("published_year"), rs.getInt("total_copies")
                            , rs.getInt("available_copies"), rs.getString("status"));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(cn != null){
                    cn.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return check;
    }
    
    public Book GetExistBook(Book book){
        Book check = null;
        Connection cn = null;
        try{
            cn = DBUtils.getConnection();
            if(cn != null){
                String sql = "SELECT * FROM books WHERE title = ?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, book.getTitle());
                ResultSet rs = ps.executeQuery();
                if(rs != null && rs.next()){
                    check = new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"), rs.getString("isbn")
                            , rs.getString("category"), rs.getInt("published_year"), rs.getInt("total_copies")
                            , rs.getInt("available_copies"), rs.getString("status"));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(cn != null){
                    cn.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return check;
    }
    
    public ArrayList<BorrowRecord> Overdue(){
        ArrayList<BorrowRecord> getOverdue;
        Date today = new Date();
        getOverdue = (ArrayList<BorrowRecord>) Borrow.stream().filter(i -> i.getStatus().equalsIgnoreCase("overdue") || (i.getReturnDate() == null && i.getDueDate().before(today))).collect(Collectors.toList());
        return getOverdue;
    }
    
    public ArrayList<BorrowRecord> BorrowedBook(){
        return Borrow;
    }
    
    public ArrayList<BookRequest> RequestBook(){
        return Request;
    }
    
     public int EditBookStatus(int id){
        int check = 0;
        Book book = GetExistBook(id);
        if(book != null){
            Connection cn = null;
            try{
                cn = DBUtils.getConnection();
                if(cn != null){
                    String status = book.getStatus().equals("active") ? "blocked" : "active";
                    String sql = "UPDATE users SET status = ? WHERE id = ?";
                    PreparedStatement pt = cn.prepareStatement(sql);
                    pt.setString(1, status);
                    pt.setInt(2, id);
                    check = pt.executeUpdate();
                }
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                try{
                    if(cn != null){
                        cn.close();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return check;
    }
     
}
