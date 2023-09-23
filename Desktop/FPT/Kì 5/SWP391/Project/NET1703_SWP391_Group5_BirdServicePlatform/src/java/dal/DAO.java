/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Service;

/**
 *
 * @author TINH KHUNG
 */
//dao chỉ dùng cho một bảng
public class DAO extends DBContext {

//    public String getFullname(String username) throws ClassNotFoundException {
//        try {
//            String name = null;
//
//            PreparedStatement stm = connection.prepareStatement("select * from Services where ServiceID like =1");
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                name = rs.getString(2);
//            }
//            return name;
//        } catch (SQLException ex) {
//            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
//    }

    public List<Service> getService(String keyword) {
        try {
            List<Service> list = new ArrayList<Service>();
            PreparedStatement stm = connection.prepareStatement("select * from Services where ServiceName like '%" + keyword + "%'");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Service service = new Service(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getFloat(5), rs.getInt(6), rs.getInt(7));
                list.add(service);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean checkLogin(String username, String password) throws SQLException {
        try {
            String sql = "select * from Registration where username='" + username + "' and password='" + password + "'";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void delete(String username) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("delete from Registration where username='" + username + "'");
        stm.executeUpdate();
    }

    public void insert(String username, String password, String fullname) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("insert into Registration(username, password,fullname,role) values('" + username + "','" + password + "','" + fullname + "','0')");
        stm.executeUpdate();
    }
}
