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
    private int UserId;
    private int BookId;
    private Date RequestDate;
    private String Status;
    
    public BookRequest(){
        this.Id = 0;
        this.UserId = 0;
        this.BookId = 0;
        this.RequestDate = null;
        this.Status = null;
    }
    
    public BookRequest(int Id, int UserId, int BookId, Date RequestDate, String Status){
        this.Id = Id;
        this.UserId = UserId;
        this.BookId = BookId;
        this.RequestDate = RequestDate;
        this.Status = Status;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public int getBookId() {
        return BookId;
    }

    public void setBookId(int BookId) {
        this.BookId = BookId;
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
    
    @Override
    public String toString(){
        return String.format("%d, %d, %d, %s, &s", this.Id, this.UserId, this.BookId
                , new SimpleDateFormat("yyyy-MM-dd").format(this.RequestDate), this.Status);
    }
}
