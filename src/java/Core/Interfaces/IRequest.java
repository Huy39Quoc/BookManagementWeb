/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Interfaces;

import Core.Entities.BookRequest;
import java.util.ArrayList;

/**
 *
 * @author ChanRoi
 */
public interface IRequest {
    ArrayList<BookRequest> RequestBook();
    int getApprove(int id);
    int getReject(int id);
    void loadRequestBooks();
}
