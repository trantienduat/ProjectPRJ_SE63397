/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order;

import cart.CartObj;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.naming.NamingException;
import shoe.ShoeDAO;
import utils.DBUtil;

/**
 *
 * @author trant
 */
public class OrderDAO implements Serializable {

    public String generateOrderID() throws NamingException, SQLException {
        String orderID = null;
        Connection c = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT orderID FROM dbo.tbl_order [to] WHERE [to].orderDate = \n"
                    + "(SELECT max([to].orderDate) FROM dbo.tbl_order [to])";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            String lastedOrderID = null;
            if (rs.next()) {
                lastedOrderID = rs.getString("orderID");
            }
            if (lastedOrderID != null) {
                int index = Integer.parseInt(lastedOrderID.substring(5));
                orderID = "Order" + ++index;
            } else {
                orderID = "Order1";
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
        return orderID;
    }

    public String getOrderID() throws NamingException, SQLException {
        Connection c = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String lastedOrderID = null;
        try {
            String sql = "SELECT orderID FROM dbo.tbl_order [to] WHERE [to].orderDate = \n"
                    + "(SELECT max([to].orderDate) FROM dbo.tbl_order [to])";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                lastedOrderID = rs.getString("orderID");
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
        return lastedOrderID;
    }

    public boolean addOrder(String orderID, String custID, Float total) throws NamingException, SQLException {
        Connection c = DBUtil.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "INSERT INTO dbo.tbl_order VALUES(?,DEFAULT,?,ROUND(?,2))";
            ps = c.prepareStatement(sql);
            ps.setString(1, orderID);
//            ps.setDate(2, orderDate);
            ps.setString(2, custID);
            ps.setFloat(3, total);
            int result = ps.executeUpdate();
            if (result > 0) {
                return true;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return false;
    }

    public boolean addOrderDetail(CartObj cart) throws NamingException, SQLException {
        LinkedHashMap<String, HashMap<Integer, Integer>> items = cart.getItems();
        Connection c = DBUtil.getConnection();
        PreparedStatement ps = null;
        int count = 0;
        try {
            String sql = "INSERT INTO tbl_orderDetail VALUES(?,?,ROUND(?,2),ROUND(?,2),?,?)";
            ps = c.prepareStatement(sql);
            for (Map.Entry<String, HashMap<Integer, Integer>> entry : items.entrySet()) {
                ShoeDAO dao = new ShoeDAO();
                String shoesID = entry.getKey();
                HashMap<Integer, Integer> sizeAndQuantites = entry.getValue();
                for (Map.Entry<Integer, Integer> sizeAndQuantity : sizeAndQuantites.entrySet()) {
                    float price = dao.getPriceByShoeIDAndSizeID(shoesID, String.valueOf(sizeAndQuantity.getKey()));
                    ps.setString(1, shoesID);
                    ps.setInt(2, sizeAndQuantity.getValue());
                    ps.setFloat(3, price);
                    ps.setFloat(4, price * sizeAndQuantity.getValue());
                    ps.setString(5, getOrderID());
                    ps.setInt(6, sizeAndQuantity.getKey());
                    ps.addBatch();
                    count++;
                }
            }
            int[] result = ps.executeBatch();
            if (count == result.length) {
                return true;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return false;
    }

    public boolean updateShoesQuantity(CartObj cart) throws NamingException, SQLException {
        LinkedHashMap<String, HashMap<Integer, Integer>> items = cart.getItems();
        Connection c = DBUtil.getConnection();
        PreparedStatement ps = null;
        int count = 0;
        try {
            String sql = "UPDATE dbo.tbl_shoes SET dbo.tbl_shoes.quantity = dbo.tbl_shoes.quantity - ? -- int\n"
                    + "WHERE dbo.tbl_shoes.shoesID = ?";
            ps = c.prepareStatement(sql);
            for (Map.Entry<String, HashMap<Integer, Integer>> entry : items.entrySet()) {
                int totalQuantity = 0;
                ShoeDAO dao = new ShoeDAO();
                String shoesID = entry.getKey();
                HashMap<Integer, Integer> sizeAndQuantites = entry.getValue();
                for (Map.Entry<Integer, Integer> sizeAndQuantity : sizeAndQuantites.entrySet()) {
                    totalQuantity += sizeAndQuantity.getValue();
                }
                ps.setInt(1, totalQuantity);
                ps.setString(2, shoesID);
                ps.addBatch();
                count++;
            }
            int result[] = ps.executeBatch();
            if (result.length == count) {
                return true;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return false;
    }

    public String updateShoesDetailsQuantity(CartObj cart) throws NamingException, SQLException {
        LinkedHashMap<String, HashMap<Integer, Integer>> items = cart.getItems();
        Connection c = DBUtil.getConnection();
        PreparedStatement ps = null;
        int count = 0;
        try {
            if (c != null) {
                String sql = "UPDATE dbo.tbl_shoesDetail\n"
                        + "SET dbo.tbl_shoesDetail.quantity = dbo.tbl_shoesDetail.quantity - ? -- int\n"
                        + "WHERE dbo.tbl_shoesDetail.shoesID = ? \n"
                        + "AND dbo.tbl_shoesDetail.sizeID =(SELECT ts.id FROM dbo.tbl_sizes ts WHERE ts.sizes = ?)"
                        + "AND dbo.tbl_shoesDetail.quantity - ? >= 0";
                ps = c.prepareStatement(sql);
                for (Map.Entry<String, HashMap<Integer, Integer>> entry : items.entrySet()) {
                    ShoeDAO dao = new ShoeDAO();
                    String shoesID = entry.getKey();
                    HashMap<Integer, Integer> sizeAndQuantites = entry.getValue();
                    for (Map.Entry<Integer, Integer> sizeAndQuantity : sizeAndQuantites.entrySet()) {
                        int quantityInDB = quantityInDB(shoesID, sizeAndQuantity.getKey());
                        if (sizeAndQuantity.getValue() <= quantityInDB) {
                            ps.setInt(1, sizeAndQuantity.getValue());
                            ps.setString(2, shoesID);
                            ps.setInt(3, sizeAndQuantity.getKey());
                            ps.setInt(4, sizeAndQuantity.getValue());
                            ps.addBatch();
                            count++;
                        } else {
                            return shoesID + " size "
                                    + sizeAndQuantity.getKey() + " has " + quantityInDB + " left!!";
                        }
                    }
                }
                int result[] = ps.executeBatch();
                if (result.length == count) {
                    return "true";
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return "false";
    }

    public int quantityInDB(String shoesID, int size) throws NamingException, SQLException {
        Connection c = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int quantityInDB = -1;
        try {
            if (c != null) {
                String sql = "SELECT quantity FROM dbo.tbl_shoesDetail tsd\n"
                        + "WHERE tsd.shoesID = ? \n"
                        + "AND tsd.sizeID =(SELECT ts.id FROM dbo.tbl_sizes ts WHERE ts.sizes = ?)";
                ps = c.prepareStatement(sql);
                ps.setString(1, shoesID);
                ps.setInt(2, size);
                rs = ps.executeQuery();
                if (rs.next()) {
                    quantityInDB = rs.getInt("quantity");
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return quantityInDB;
    }
}
