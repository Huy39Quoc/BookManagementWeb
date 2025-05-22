/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Core.Entities.Book;
import JDBC.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ChanRoi
 */
public class BookDAO {
    public int AddBook(Book book){
        int request = 0;
        Connection cn = null;
        try{
            cn = DBUtils.getConnection();
            if(cn != null){
                String sql = "INSERT INTO books VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
    
    public int EditBook(Book book){
        int request = 0;
        Connection cn = null;
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
}
