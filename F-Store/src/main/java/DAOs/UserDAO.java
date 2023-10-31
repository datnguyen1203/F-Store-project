/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Modals.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class UserDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public UserDAO() {
        conn = DBContext.DBContext.getConnection();
    }

    public String hashPassword(String password, String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashedBytes = digest.digest(password.getBytes());

            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }

            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User GetUserId(String id) {
        User u = new User();
        try {
            ps = conn.prepareStatement("select * from [User] where userID=?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                u = new User(rs.getInt("userID"), rs.getString("userEmail"), rs.getString("userPassword"), rs.getString("userName"),
                        rs.getDate("userDayOfBirth"), rs.getString("userPhone"), rs.getString("userAddress"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public User GetUser(String email) {
        User user = new User();
        try {
            ps = conn.prepareStatement("select * from [User] where userEmail=?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt("userID"), rs.getString("userEmail"), rs.getString("userPassword"), rs.getString("userName"),
                        rs.getDate("userDayOfBirth"), rs.getString("userPhone"), rs.getString("userAddress"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public boolean Login(User user) throws SQLException {
        ResultSet rs = null;
        String sql = "Select * from [User] where userEmail = ? and userPassword=?";
        String password = user.getUserPassword();
        String hashPassword = hashPassword(password, "MD5");
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUserEmail());
            ps.setString(2, hashPassword);
            rs = ps.executeQuery();
        } catch (Exception e) {

        }

        return rs.next();
    }

    public int AddNew(User us) throws SQLException {
        String sql = "insert into [User](userEmail,userPassword, userName, userDayOfBirth, userPhone, userAddress) values (?,?,?,?,?,?);";
        String password = us.getUserPassword();
        String hashPassword = hashPassword(password, "MD5");
        int ketqua = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, us.getUserEmail());
            ps.setString(2, hashPassword.toUpperCase());
            ps.setString(3, us.getUserName());
            ps.setDate(4, us.getUserDOB());
            ps.setString(5, us.getUserPhone());
            ps.setString(6, us.getUserAddress());
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ketqua;
    }

    public int Update(User u) {
        String sql = "update [User] set userEmail=?, userName=?, userDayOfBirth=?, userPhone=?, userAddress=? where userID=?";
        int ketqua = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, u.getUserEmail());
            ps.setString(2, u.getUserName());
            ps.setDate(3, u.getUserDOB());
            ps.setString(4, u.getUserPhone());
            ps.setString(5, u.getUserAddress());
            ps.setInt(6, u.getUserID());
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }
    
    public int UpdatePassword(User u) {
        String password = u.getUserPassword();
        String hashPassword = hashPassword(password, "MD5");
        String sql = "update [User] set userPassword=? where userID=?";
        int ketqua = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, hashPassword.toUpperCase());
            ps.setInt(2, u.getUserID());
            ketqua = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ketqua;
    }
}