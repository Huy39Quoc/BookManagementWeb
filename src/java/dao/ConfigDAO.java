/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Core.Entities.Config;
import Core.Interfaces.IConfig;
import JDBC.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ChanRoi
 */
public class ConfigDAO implements IConfig{
    ArrayList<Config> configList = new ArrayList();
    public ConfigDAO(){
        Connection cn = null;
        try{
            cn = DBUtils.getConnection();
            if(cn != null){
                String sql = "SELECT * FROM system_config";
                PreparedStatement st = cn.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                if(rs != null){
                    while(rs.next()){
                        int id = rs.getInt("id");
                        String key = rs.getString("config_key");
                        String value = rs.getString("config_value");
                        String description = rs.getString("description");
                        configList.add(new Config(id, key, value, description));
                    }
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
    }
    
    public ArrayList<Config> configList(){
       return configList;
   }
    
    public int EditConfig(int id, String value){
        Connection cn = null;
        Config editConfig = getConfig(id);
        if(editConfig == null){
            return 0;
        }
        int result = 0;
        try{
            Double textNumber = Double.parseDouble(value);
            cn = DBUtils.getConnection();
            if(cn != null){
                String sql = "UPDATE system_config SET config_value = ? WHERE id = ?";
                PreparedStatement ps = cn.prepareStatement(sql);
                ps.setString(1, value);
                ps.setInt(2, editConfig.getId());
                result = ps.executeUpdate();
                if(result > 0){
                    for(Config getEdit: configList){
                        if(getEdit.getId() == editConfig.getId()){
                            getEdit.setValue(value);
                            break;
                        }
                    }
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
        return result;
    }
    
    public Config getConfig(int id){
        Config config = configList.stream().filter(i -> i.getId() == id).findAny().orElse(null);
        if(config == null){
            return null;
        }
        return config;
    }
}
