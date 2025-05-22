/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Core.Entities.Account;
import JDBC.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ChanRoi
 */
public class UserDAO {
    public Account accessUser(String email, String password){
        Account user = null;
        Connection cn = null;
        try{
            cn = DBUtils.getConnection();
            if(cn != null){
                String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if(rs != null && rs.next()){
                    user = new Account(rs.getInt("id"), rs.getString("name")
                            , rs.getString("email"), rs.getString("password")
                            , rs.getString("role"), rs.getString("status"));
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
           return user;
    }
}
