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
import model.RegistrationDTO;

/**
 *
 * @author TINH KHUNG
 */
//dao chỉ dùng cho một bảng
public class DAO extends DBContext {

    public boolean check() {
        try {
            String sql = "select * from [User]";
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

    public List<RegistrationDTO> getLetter(String fullname) {
        try {
            List<RegistrationDTO> list = new ArrayList<RegistrationDTO>();

            PreparedStatement stm = connection.prepareStatement("select * from Registration where fullname like '%" + fullname + "%'");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                RegistrationDTO user = new RegistrationDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(user);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String getFullname(String username) throws ClassNotFoundException {
        try {
            String name = null;

            PreparedStatement stm = connection.prepareStatement("select * from Registration where username like '%" + username + "%'");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                name = rs.getString(3);
            }
            return name;
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void delete(String username) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("delete from Registration where username='" + username + "'");
        stm.executeUpdate();
    }

    public void update(RegistrationDTO user) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("update Registration set username='" + user.getUsername() + "',password='" + user.getPassword() + "',fullname='" + user.getFullname() + "',role='" + user.getRoles() + "' where username='" + user.getUsername() + "'");
        stm.executeUpdate();
    }

    public void insert(String username, String password, String fullname) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("insert into Registration(username, password,fullname,role) values('" + username + "','" + password + "','" + fullname + "','0')");
        stm.executeUpdate();
    }
}
