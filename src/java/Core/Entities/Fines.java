/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Entities;

/**
 *
 * @author ChanRoi
 */
public class Fines {
    private int Id;
    private int BorrowId;
    private double FineAmount;
    private String Status;
    
    public Fines(){
        this.Id = 0;
        this.BorrowId = 0;
        this.FineAmount = 0;
        this.Status = "unpaid";
    }
    
    public Fines(int Id, int BorrowId, double FineAmount, String Status){
        this.Id = Id;
        this.BorrowId = BorrowId;
        this.FineAmount = FineAmount;
        this.Status = Status;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getBorrowId() {
        return BorrowId;
    }

    public void setBorrowId(int BorrowId) {
        this.BorrowId = BorrowId;
    }

    public double getFineAmount() {
        return FineAmount;
    }

    public void setFineAmount(double FineAmount) {
        this.FineAmount = FineAmount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    @Override
    public String toString(){
        return String.format("%d, %d, %f, %s", this.Id, this.BorrowId, this.FineAmount, this.Status);
    }
}
