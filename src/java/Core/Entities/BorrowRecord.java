 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Entities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ChanRoi
 */
public class BorrowRecord {
    private int Id;
    private int userId;
    private int bookId;
    private String UserName;
    private String BookTitle;
    private Date BorrowDate;
    private Date DueDate;
    private Date ReturnDate;
    private String Status;
    private String Price;
    
    
    public BorrowRecord(int Id, int userId, int bookId, String UserName, String BookTitle, Date BorrowDate, Date DueDate, Date ReturnDate, String Status){
        this.Id = Id;
        this.userId = userId;
        this.bookId = bookId;
        this.UserName = UserName;
        this.BookTitle = BookTitle;
        this.BorrowDate = BorrowDate;
        this.DueDate = DueDate;
        this.ReturnDate = ReturnDate;
        this.Status = Status;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getBookTitle() {
        return BookTitle;
    }

    public void setBookTitle(String BookTitle) {
        this.BookTitle = BookTitle;
    }

    public Date getBorrowDate() {
        return BorrowDate;
    }

    public void setBorrowDate(Date BorrowDate) {
        this.BorrowDate = BorrowDate;
    }

    public Date getDueDate() {
        return DueDate;
    }

    public void setDueDate(Date DueDate) {
        this.DueDate = DueDate;
    }

    public Date getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(Date ReturnDate) {
        this.ReturnDate = ReturnDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
}
