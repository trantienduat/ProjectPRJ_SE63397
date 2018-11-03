/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package account;

import customer.CustomerDAO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import utils.DBUtil;

/**
 *
 * @author trant
 */
public class AccountDAO implements Serializable {

    public AccountDTO checkLogin(String username, String password) throws NamingException, SQLException {
        Connection c = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        AccountDTO account = null;
        if (c != null) {
            try {

                String sql = "SELECT * FROM tbl_account WHERE username=? AND password=?";
                ps = c.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                while (rs.next()) {
                    account = new AccountDTO(rs.getString("username"), rs.getString("password"), rs.getString("custID"));
                }
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (c != null) {
                    c.close();
                }
            }
        }
        return account;
    }

    public boolean createNewAccount(String username, String password, String custID) throws NamingException, SQLException {
        Connection c = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO tbl_account VALUES(?,?,?)";
            ps = c.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, custID);
            int result = ps.executeUpdate();
            if (result > 0) {
                return true;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return false;
    }

    public boolean isExistedAccount(String username) throws NamingException, SQLException {
        Connection c = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean isExisted = false;
        if (c != null) {
            try {

                String sql = "SELECT * FROM tbl_account WHERE username=?";
                ps = c.prepareStatement(sql);
                ps.setString(1, username);
                rs = ps.executeQuery();
                if (rs.next()) {
                    isExisted = true;
                }
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (c != null) {
                    c.close();
                }
            }
        }
        return isExisted;
    }
}
