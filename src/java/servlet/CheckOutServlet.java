/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import errors.CustomerConfirmInfoErrors;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author trant
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.jsp";
    private final String VIEW_CART_PAGE = "viewCart.jsp";
    private final String CONFIRM_ORDER_PAGE = "confirmOrder.jsp";
    private final String CHECK_OUT_PAGE = "checkOut.jsp";

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
        PrintWriter out = response.getWriter();
        String action = request.getParameter("btAction");

        String searchValue = request.getParameter("searchValue");
        String receiver = request.getParameter("receiver");
        String receiverPhone = request.getParameter("receiverPhone");
        String deliveryAddress = request.getParameter("deliveryAddress");

        String url = LOGIN_PAGE;

        HttpSession session = request.getSession(false);
        try {
            if (session.getAttribute("MEMBER") != null) {
                if (session.getAttribute("CART") != null) {
                    if (action.equals("checkOut")) {
                        url = CHECK_OUT_PAGE;
                    }
                    if (action.equals("Back")) {
                        url = VIEW_CART_PAGE;
                    }
                    if (action.equals("Confirm")) {
                        CustomerConfirmInfoErrors errors = new CustomerConfirmInfoErrors();
                        boolean error = false;
                        if (deliveryAddress.trim().length() == 0 || deliveryAddress.trim().length() > 250) {
                            error = true;
                            errors.setAddressDeliveryLengthError("Address is required with > 0 and < 250 chars!!");
                        }
                        if (receiver.trim().length() == 0) {
                            error = true;
                            errors.setReceiverLengthError("Receiver is required!!");
                        }
                        if (!receiverPhone.matches("\\d+$") || receiverPhone.trim().length() == 0) {
                            error = true;
                            errors.setReceiverPhoneNumberFormatError("Phone number is required with digits");
                        }
                        if (error) {
                            request.setAttribute("ERRORS", errors);
                            url = CHECK_OUT_PAGE;
                        } else {
                            url = CONFIRM_ORDER_PAGE;
                        }
                    }
                    if (action.equals("DestroyCart")) {
                        session.removeAttribute("CART");
                        url = VIEW_CART_PAGE;
                    }
                    if (action.equals("Cancel")) {
                        url = CHECK_OUT_PAGE;
                    }
                }
            }
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
