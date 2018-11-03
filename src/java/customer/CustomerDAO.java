/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customer;

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
public class CustomerDAO implements Serializable {

    public CustomerDTO getCustomerById(String custID) throws NamingException, SQLException {
        CustomerDTO dto = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection c = DBUtil.getConnection();
        try {
            String sql = "SELECT * FROM dbo.tbl_customer tc WHERE tc.custID = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, custID);
            rs = ps.executeQuery();
            while (rs.next()) {
                dto = new CustomerDTO(custID, rs.getString("lastName"),
                        rs.getString("middleName"), rs.getString("firstName"),
                        rs.getString("address"), rs.getString("phone"),
                        rs.getInt("custLevel"));
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
        return dto;
    }

    public String generateCustomerID() throws NamingException, SQLException {
        String custID = null;
        Connection c = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT max(tc.custID) AS custID FROM dbo.tbl_customer tc ";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            String lastedCustID = null;
            if (rs.next()) {
                lastedCustID = rs.getString("custID");
            }
            if (lastedCustID != null) {
                int index = Integer.parseInt(lastedCustID.substring(4));
                custID = "cust" + ++index;
            } else {
                custID = "cust1";
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
        return custID;
    }

    public boolean addNewCustomer(CustomerDTO dto) throws NamingException, SQLException {
        Connection c = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "INSERT INTO tbl_customer VALUES(?,?,?,?,?,?,?)";
            ps = c.prepareStatement(sql);
            ps.setString(1, dto.getCustID());
            ps.setString(2, dto.getLastName());
            ps.setString(3, dto.getMiddleName());
            ps.setString(4, dto.getFirstName());
            ps.setString(5, dto.getAddress());
            ps.setString(6, dto.getPhone());
            ps.setInt(7, dto.getCustLevel());
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
}
