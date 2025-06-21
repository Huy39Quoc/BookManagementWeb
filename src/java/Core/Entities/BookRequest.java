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
public class BookRequest {
    private int Id;
    private int userId;
    private int bookId;
    private String UserName;
    private String BookTitle;
    private Date RequestDate;
    private String Status;
    private String userStatus;
    
    public BookRequest(int Id, int userId, int bookId, String UserName, String BookTitle, Date RequestDate, String Status, String userStatus){
        this.Id = Id;
        this.userId = userId;
        this.bookId = bookId;
        this.UserName = UserName;
        this.BookTitle = BookTitle;
        this.RequestDate = RequestDate;
        this.Status = Status;
        this.userStatus = userStatus;
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

    public Date getRequestDate() {
        return RequestDate;
    }

    public void setRequestDate(Date RequestDate) {
        this.RequestDate = RequestDate;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
    
    
}
