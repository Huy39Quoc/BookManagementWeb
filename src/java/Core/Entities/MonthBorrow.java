/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Entities;

import java.time.YearMonth;

/**
 *
 * @author ChanRoi
 */
public class MonthBorrow {
    private YearMonth time;
    private int count;
    
    public MonthBorrow(YearMonth time, int count){
        this.time = time;
        this.count = count;
    }

    public YearMonth getTime() {
        return time;
    }

    public int getCount() {
        return count;
    }
    
    
}
