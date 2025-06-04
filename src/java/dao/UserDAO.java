/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Core.Entities.Account;
import Core.Interfaces.IUser;
import JDBC.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ChanRoi
 */
public class UserDAO implements IUser {
    ArrayList<Account> searchUser = new ArrayList();
    ArrayList<Account> UserList = new ArrayList();
    public UserDAO(){
        Connection cn = null;
        try{
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                cn = DBUtils.getConnection();
                if(cn != null){
                    String sql = "SELECT * FROM users";
                    PreparedStatement ps = cn.prepareStatement(sql);
                    ResultSet st = ps.executeQuery();
                    while(st.next()){
                        int id = st.getInt("id");
                        String name = st.getString("name");
                        String email = st.getString("email");
                        String password = st.getString("password");
                        String role = st.getString("role");
                        String status = st.getString("status");
                        UserList.add(new Account(id, name, email, password, role, status));
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public ArrayList<Account> getUserList(){
        return UserList;
    }
    
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
    
    
    
    public ArrayList<Account> searchUserByEmail(String inputEmail){
        Connection cn = null;
        try{
            cn = DBUtils.getConnection();
            if(cn != null){
                String sql = "SELECT [id], [name], [email], [password], [role], [status] "
                        + "FROM  [dbo].[users] WHERE [email] LIKE '%" + "?" + "%'";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, inputEmail);
                ResultSet rs = ps.executeQuery();
                if(rs != null){
                    searchUser.clear();
                    while(rs.next()){
                         int id = rs.getInt("id");
                         String name = rs.getString("name");
                         String email = rs.getString("email");
                         String password = rs.getString("password");
                         String role = rs.getString("role");
                         String status = rs.getString("status");
                         searchUser.add(new Account(id, name, email, password, role, status));
                    }
                    return searchUser;
                }else{
                    return null;
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
