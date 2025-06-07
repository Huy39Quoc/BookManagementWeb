/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Core.Entities.Book;
import Core.Entities.MonthBorrow;
import Core.Entities.Most5;
import Core.Entities.Statistic;
import Core.Interfaces.IStatistic;
import JDBC.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.YearMonth;
import java.util.ArrayList;

/**
 *
 * @author ChanRoi
 */
public class CheckStatistic implements IStatistic{
    public Statistic getStatistic(){
        String TotalBook = "SELECT SUM(available_copies) FROM books";
        String TotalUser = "SELECT COUNT(id) FROM users WHERE role = 'user'";
        String currentBorrowed = "SELECT COUNT(id) FROM borrow_records WHERE status = 'borrowed' OR status = 'overdue'";
        String MostBorrowed = "SELECT TOP 5 b.title, COUNT(br.id) AS times_borrowed "
                        + "FROM borrow_records br "
                        + "JOIN books b ON br.book_id = b.id "
                        + "GROUP BY b.id, b.title "
                        + "ORDER BY times_borrowed DESC";
        String MonthBorrow = "SELECT FORMAT(borrow_date, 'yyyy-MM') AS borrow_month"
                        + ", COUNT(*) AS borrow_count "
                        + "FROM borrow_records GROUP BY FORMAT(borrow_date, 'yyyy-MM') ORDER BY borrow_month";
        String AverageTime = "SELECT AVG(DATEDIFF(DAY, borrow_date, return_date)) AS average_days_kept "
                        + "FROM borrow_records WHERE return_date IS NOT NULL";
        
        int getTotalBook = getTotal(TotalBook);
        int getTotalUser = getTotal(TotalUser);
        int getcurrentBorrowed = getTotal(currentBorrowed);
        
        ArrayList<Most5> getTop = getTop5(MostBorrowed);
        ArrayList<MonthBorrow> getData = getCountMonth(MonthBorrow);
        
        double getDuration = getAverage(AverageTime);
        
        return new Statistic(getTotalBook, getTotalUser, getcurrentBorrowed, getTop, getData, getDuration);
    }
    public int getTotal(String Total){
        Connection cn = null;
        try{
            cn = DBUtils.getConnection();
            if(cn != null){
                PreparedStatement st = cn.prepareStatement(Total);
                ResultSet rs = st.executeQuery();
                if(rs.next()){
                    return rs.getInt(1);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(cn != null){
                    cn.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return 0;
    }
    
    public double getAverage(String Total){
        Connection cn = null;
        try{
            cn = DBUtils.getConnection();
            if(cn != null){
                PreparedStatement st = cn.prepareStatement(Total);
                ResultSet rs = st.executeQuery();
                if(rs.next()){
                    return rs.getInt(1);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(cn != null){
                    cn.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return 0;
    }
    
    public ArrayList<Most5> getTop5(String top){
        Connection cn = null;
        ArrayList<Most5> getTop = new ArrayList();
        try{
            cn = DBUtils.getConnection();
            if(cn != null){
                PreparedStatement st = cn.prepareStatement(top);
                ResultSet rs = st.executeQuery();
                if(rs != null){
                    while(rs.next()){
                        getTop.add(new Most5(rs.getString("title"), rs.getInt("times_borrowed")));
                    }
                    return getTop;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(cn != null){
                    cn.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public ArrayList<MonthBorrow> getCountMonth(String MonthBorrow){
        Connection cn = null;
        ArrayList<MonthBorrow> getData = new ArrayList();
        try{
            cn = DBUtils.getConnection();
            if(cn != null){
                PreparedStatement st = cn.prepareStatement(MonthBorrow);
                ResultSet rs = st.executeQuery();
                if(rs != null){
                    while(rs.next()){
                        getData.add(new MonthBorrow(YearMonth.parse(rs.getString("borrow_month")), rs.getInt("borrow_count")));
                    }
                    return getData;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(cn != null){
                    cn.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
