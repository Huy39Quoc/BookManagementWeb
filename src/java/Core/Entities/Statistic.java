/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Entities;

import java.util.ArrayList;

/**
 *
 * @author ChanRoi
 */
public class Statistic {
    private int Book;
    private int Users;
    private int BorrowedBook;
    private ArrayList<Most5> MostBorrowed;
    private ArrayList<MonthBorrow> BorrowingStat;
    private double Duration;
    
    public Statistic(){   
   }
    
    public Statistic(int Book, int Users, int BorrowedBook, ArrayList<Most5> MostBorrowed, ArrayList<MonthBorrow> BorrowingStat, double Duration){
        this.Book = Book;
        this.Users = Users;
        this.BorrowedBook = BorrowedBook;
        this.MostBorrowed = MostBorrowed;
        this.BorrowingStat = BorrowingStat;
        this.Duration = Duration;
    }

    public int getBook() {
        return Book;
    }

    public void setBook(int Book) {
        this.Book = Book;
    }

    public int getUsers() {
        return Users;
    }

    public void setUsers(int Users) {
        this.Users = Users;
    }

    public int getBorrowedBook() {
        return BorrowedBook;
    }

    public void setBorrowedBook(int BorrowedBook) {
        this.BorrowedBook = BorrowedBook;
    }

    public ArrayList<Most5> getMostBorrowed() {
        return MostBorrowed;
    }

    public void setMostBorrowed(ArrayList<Most5> MostBorrowed) {
        this.MostBorrowed = MostBorrowed;
    }

    public ArrayList<MonthBorrow> getBorrowingStat() {
        return BorrowingStat;
    }

    public void setBorrowingStat(ArrayList<MonthBorrow> BorrowingStat) {
        this.BorrowingStat = BorrowingStat;
    }

    

    public double getDuration() {
        return Duration;
    }

    public void setDuration(double Duration) {
        this.Duration = Duration;
    }
}
