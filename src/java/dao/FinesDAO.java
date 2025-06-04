/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Core.Entities.Fines;
import JDBC.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ChanRoi
 */
public class FinesDAO {
    public Fines getFines(){
        Connection cn = null;
        try{
            cn = DBUtils.getConnection();
            if(cn != null){
                String sql = "SELECT * FROM fines";
                PreparedStatement st = cn.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                if(rs.next()){
                    return new Fines(rs.getInt("id"), rs.getInt("borrow_id"), rs.getDouble("fine_amount"), rs.getString("paid_status"));
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
