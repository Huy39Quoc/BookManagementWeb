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
    private int UserId;
    private int BookId;
    private Date BorrowDate;
    private Date DueDate;
    private Date ReturnDate;
    private String Status;
    
    public BorrowRecord(){
        this.Id = 0;
        this.UserId = 0;
        this.BookId = 0;
        this.BorrowDate = null;
        this.DueDate = null;
        this.ReturnDate = null;
        this.Status = "borrowed";
    }
    
    public BorrowRecord(int Id, int UserId, int BookId, Date BorrowDate, Date DueDate, Date ReturnDate, String Status){
        this.Id = Id;
        this.UserId = UserId;
        this.BookId = BookId;
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
    
    @Override
    public String toString(){
        return String.format("%d, %d, %d, %s, %s, %s, %s", this.Id, this.UserId, this.BookId
                , new SimpleDateFormat("yyyy-MM-dd").format(this.BorrowDate)
                , new SimpleDateFormat("yyyy-MM-dd").format(this.DueDate)
                , new SimpleDateFormat("yyyy-MM-dd").format(this.ReturnDate), this.Status);
    }
}
