/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import cart.CartObj;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import order.OrderDAO;

/**
 *
 * @author trant
 */
@WebServlet(name = "ConfirmOrderServlet", urlPatterns = {"/ConfirmOrderServlet"})
public class ConfirmOrderServlet extends HttpServlet {

    private final String CHECKOUT_PAGE = "checkOut.jsp";
    private final String LOGIN_PAGE = "login.jsp";
    private final String ORDER_STATUS_PAGE = "orderStatus.jsp";
    private String VIEW_CART_PAGE = "viewCart.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("btAction");
        PrintWriter out = response.getWriter();
        String receiver = request.getParameter("receiver");
        String receiverPhone = request.getParameter("receiverPhone");
        String deliveryAddress = request.getParameter("deliveryAddress");

        String TOTAL = request.getParameter("TOTAL");

        String url = LOGIN_PAGE;

        HttpSession session = request.getSession(false);
        try {
            if (session.getAttribute("MEMBER") != null) {
                if (action.equals("Cancel")) {
                    url = CHECKOUT_PAGE;
                }
                if (action.equals("OK")) {
                    url = ORDER_STATUS_PAGE;
                    CartObj cart = (CartObj) session.getAttribute("CART");
                    LinkedHashMap<String, HashMap<Integer, Integer>> items = cart.getItems();
                    String custID = cart.getCustID();
                    OrderDAO dao = new OrderDAO();

                    String updateShoesDetailsQuantity = dao.updateShoesDetailsQuantity(cart);
                    if (updateShoesDetailsQuantity.equals("true")) {
                        boolean resultOrder = dao.addOrder(dao.generateOrderID(), custID, Float.parseFloat(TOTAL));
                        boolean resultOrderDetails = dao.addOrderDetail(cart);
                        boolean updateShoesQuantity = dao.updateShoesQuantity(cart);
                        if (updateShoesQuantity && resultOrderDetails && resultOrder) {
                            request.setAttribute("STATUS", "Thank you for your Order!!");
                            session.removeAttribute("CART");
                        }
                    } else if (updateShoesDetailsQuantity.contains("size")) {
                        request.setAttribute("NOTIFICATION", updateShoesDetailsQuantity);
                        url = VIEW_CART_PAGE;
                    } else {
                        request.setAttribute("STATUS", "Sorry, Fail to process your Order!!");
                    }
                }
            }
        } catch (NamingException ex) {
            log("ConfirmOrderServlet_NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            log("ConfirmOrderServlet_SQLException: " + ex.getMessage());
        } finally {
            RequestDispatcher dis = request.getRequestDispatcher(url);
            dis.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
