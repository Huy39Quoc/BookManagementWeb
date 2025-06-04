/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Core.Entities.Book;
import Core.Entities.BorrowRecord;
import JDBC.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ChanRoi
 */
public class BookDAO {
    public ArrayList<Book> booksList = new ArrayList();
    public ArrayList<BorrowRecord> Borrow = new ArrayList();
    public BookDAO(){
        Connection cn = null;
        try{
            cn = DBUtils.getConnection();
            if(cn != null){
                String sql = "SELECT id, title, author, isbn, category, published_year, total_copies, available_copies, status "
                        + "FROM books";
                PreparedStatement st = cn.prepareCall(sql);
                ResultSet rs = st.executeQuery();
                if(rs != null){
                    while(rs.next()){
                      int id = rs.getInt("id");
                      String title = rs.getNString("title");
                      String author = rs.getNString("author");
                      String isbn = rs.getNString("isbn");
                      String category = rs.getNString("category");
                      int PublishedYear = rs.getInt("published_year");
                      int TotalCopies = rs.getInt("total_copies");
                      int AvailableCopies = rs.getInt("available_copies");
                      String Status = rs.getString("status");
                      booksList.add(new Book(id, title, author, isbn, category, PublishedYear, TotalCopies, AvailableCopies, Status));
                    }
                }
                
                String sqlBorrow = "SELECT id, user_id, book_id, borrow_date, due_date, return_date, status "
                        + "FROM borrow_records";
                st = cn.prepareCall(sqlBorrow);
                rs = st.executeQuery();
                if(rs != null){
                    while(rs.next()){
                      int id = rs.getInt("id");
                      int user_id = rs.getInt("user_id");
                      int book_id = rs.getInt("book_id");
                      Date borrow_date = rs.getDate("borrow_date");
                      Date due_date = rs.getDate("due_date");
                      Date return_date = rs.getDate("return_date");
                      String status  = rs.getString("status");
                      Borrow.add(new BorrowRecord(id, user_id, book_id, borrow_date, due_date, return_date, status));
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
    
    public int AddBook(Book book){
        int request = 0;
        Connection cn = null;
        if(CheckExistBook(book) == null){
            return request;
        }
        try{
            cn = DBUtils.getConnection();
            if(cn != null){
                String sql = "INSERT INTO books VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = cn.prepareStatement(sql);
                int Id = book.getId(), PublishedYear = book.getPublishedYear(), TotalCopies = book.getTotalCopies(), AvailableCopies = book.getAvailableCopies();
                String Title = book.getTitle(), Author = book.getAuthor(), ISBN = book.getISBN(), Category = book.getCategory(), Status = book.getStatus();
                ps.setInt(1, Id);
                ps.setString(2, Title);
                ps.setString(3, Author);
                ps.setString(4, ISBN);
                ps.setString(5, Category);
                ps.setInt(6, PublishedYear);
                ps.setInt(7, TotalCopies);
                ps.setInt(8, AvailableCopies);
                ps.setString(9, Status);
                request = ps.executeUpdate();
                booksList.add(new Book(Id, Title, Author, ISBN, Category, PublishedYear, TotalCopies, AvailableCopies, Status));
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
        int request = 0;
        Connection cn = null;
        if(CheckExistBook(book) == null){
            return request;
        }
        try{
            cn = DBUtils.getConnection();
            if(cn != null){
                String sql = "UPDATE books SET id = ?, title = ?, author = ?, isbn = ?, category = ?"
                        + ", published_year = ?, total_copies = ?, available_copies = ?, status = ?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setInt(1, book.getId());
                ps.setString(2, book.getTitle());
                ps.setString(3, book.getAuthor());
                ps.setString(4, book.getISBN());
                ps.setString(5, book.getCategory());
                ps.setInt(6, book.getPublishedYear());
                ps.setInt(7, book.getTotalCopies());
                ps.setInt(8, book.getAvailableCopies());
                ps.setString(9, book.getStatus());
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
    
    public int RemoveBook(Book book){
        int request = 0;
        Connection cn = null;
        if(CheckExistBook(book) == null){
            return request;
        }
        try{
            cn = DBUtils.getConnection();
            if(cn != null){
                String sql = "DELETE FROM books WHERE id = ?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setInt(1, book.getId());
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
    
    public Book CheckExistBook(Book book){
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
    
    public List<BorrowRecord> OverdueBook(){
        List<BorrowRecord> Overdue = new ArrayList();
        Date today = new Date();
        Overdue = Borrow.stream().filter(i -> i.getStatus().equalsIgnoreCase("overdue") || (i.getReturnDate() == null && i.getDueDate().before(today))).collect(Collectors.toList());
        return Overdue;
    }
}
