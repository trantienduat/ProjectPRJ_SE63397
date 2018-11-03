/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoe;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.naming.NamingException;
import utils.DBUtil;

/**
 *
 * @author trant
 */
public class ShoeDAO implements Serializable {

    private List<ShoeDTO> listShoes;

    public List<ShoeDTO> getListShoes() {
        return listShoes;
    }

    public void getListShoeByDescription(String description) throws NamingException, SQLException {
        Connection c = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (c != null) {
                String sql = "SELECT * FROM dbo.tbl_shoes ts WHERE ts.description LIKE ? AND ts.quantity > 0";
                ps = c.prepareStatement(sql);
                ps.setString(1, "%" + description + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String shoesID = rs.getString("shoesID");
                    ShoeDTO dto = new ShoeDTO(shoesID, rs.getString("description"), rs.getInt("quantity"), getSizesAndPriceByShoesID(shoesID));

                    if (this.listShoes == null) {
                        this.listShoes = new ArrayList<>();
                    }
                    listShoes.add(dto);
                }
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

    private HashMap<Integer, Float> getSizesAndPriceByShoesID(String shoesID) throws NamingException, SQLException {
        HashMap<Integer, Float> sizeAndPrice = new HashMap<>();
        Connection c = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            if (c != null) {
                String sql = "SELECT tsd2.sizeID, tsd2.price FROM dbo.tbl_shoesDetail tsd2\n"
                        + "WHERE tsd2.shoesID = ? AND tsd2.quantity > 0";
                ps = c.prepareStatement(sql);
                ps.setString(1, shoesID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String sizeID = rs.getString("sizeID");
                    sizeAndPrice.put(getSizeBySizeID(sizeID), rs.getFloat("price"));
                }
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
        return sizeAndPrice;
    }

    public int getSizeBySizeID(String sizeID) throws SQLException, NamingException {
        int size = -1;
        Connection c = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (c != null) {
            try {
                String sql = "SELECT ts.sizes FROM dbo.tbl_sizes ts WHERE ts.id = ?";
                ps = c.prepareStatement(sql);
                ps.setString(1, sizeID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    size = rs.getInt("sizes");
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
        return size;
    }

    public String getDescriptionByID(String shoesID) throws SQLException, NamingException {
        String description = null;
        Connection c = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (c != null) {
            try {
                String sql = "SELECT ts.description FROM dbo.tbl_shoes ts WHERE ts.shoesID =  ?";
                ps = c.prepareStatement(sql);
                ps.setString(1, shoesID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    description = rs.getString("description");
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
        return description;
    }

    public float getPriceByShoeIDAndSizeID(String shoesID, String sizeID) throws SQLException, NamingException {
        float price = 0;
        Connection c = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (c != null) {
            try {
                String sql = "SELECT tsd.price FROM dbo.tbl_shoesDetail tsd WHERE tsd.shoesID = ? AND tsd.sizeID = \n"
                        + "(SELECT ts.id FROM dbo.tbl_sizes ts WHERE ts.sizes = ?)";
                ps = c.prepareStatement(sql);
                ps.setString(1, shoesID);
                ps.setString(2, sizeID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    price = rs.getFloat("price");
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
        return price;
    }
}
