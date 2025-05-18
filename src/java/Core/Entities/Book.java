/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Entities;

/**
 *
 * @author ChanRoi
 */
public class Book {
    private int Id;
    private String Title;
    private String Author;
    private String ISBN;
    private String Category;
    private int PublishedYear;
    private int TotalCopies;
    private int AvailableCopies;
    private String Status;
    
    public Book(){
        this.Id = 0;
        this.Title = null;
        this.Author = null;
        this.ISBN = null;
        this.Category = null;
        this.PublishedYear = 0;
        this.TotalCopies = 1;
        this.AvailableCopies = 1;
        this.Status = "active";
    }
    
    public Book(int Id, String Title, String Author, String ISBN, String Category, int PublishedYear, int TotalCopies, int AvailableCopies, String Status){
        this.Id = Id;
        this.Title = Title;
        this.Author = Author;
        this.ISBN = ISBN;
        this.Category = Category;
        this.PublishedYear = PublishedYear;
        this.TotalCopies = TotalCopies;
        this.AvailableCopies = AvailableCopies;
        this.Status = Status;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public int getPublishedYear() {
        return PublishedYear;
    }

    public void setPublishedYear(int PublishedYear) {
        this.PublishedYear = PublishedYear;
    }

    public int getTotalCopies() {
        return TotalCopies;
    }

    public void setTotalCopies(int TotalCopies) {
        this.TotalCopies = TotalCopies;
    }

    public int getAvailableCopies() {
        return AvailableCopies;
    }

    public void setAvailableCopies(int AvailableCopies) {
        this.AvailableCopies = AvailableCopies;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    @Override
    public String toString(){
        return String.format("%d, %s, %s, %s, %s, %d, %d, %d, %s"
                , this.Id, this.Title, this.Author, this.ISBN, this.Category
                , this.PublishedYear, this.TotalCopies, this.AvailableCopies, this.Status);
    }
}
