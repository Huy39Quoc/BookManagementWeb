/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Interfaces;

import Core.Entities.Book;
import java.util.ArrayList;

/**
 *
 * @author ChanRoi
 */
public interface IBook {
    ArrayList<Book> getBookList();
    int AddBook(Book book);
    int EditBook(Book book);
    int RemoveBook(int id);
    Book GetExistBook(int id);
}
