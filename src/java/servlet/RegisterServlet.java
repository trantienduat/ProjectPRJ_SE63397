/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import account.AccountDAO;
import account.AccountDTO;
import customer.CustomerDAO;
import customer.CustomerDTO;
import errors.CustomerRegisterInfoErrors;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author trant
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

    private String REGISTER_PAGE = "register.jsp";
    private String LOGIN_PAGE = "login.jsp";

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

        String url = REGISTER_PAGE;

        String username = request.getParameter("username");
        String lastname = request.getParameter("lastname");
        String middlename = request.getParameter("middlename");
        String firstname = request.getParameter("firstname");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        CustomerRegisterInfoErrors errors = new CustomerRegisterInfoErrors();
        boolean error = false;
        try {

            CustomerDAO custDAO = new CustomerDAO();
            AccountDAO accDAO = new AccountDAO();
            String custID = custDAO.generateCustomerID();
            AccountDTO accDTO = new AccountDTO(username, password, custID);

            if (lastname.trim().length() > 15 || lastname.trim().length() == 0) {
                error = true;
                errors.setLastnameLengthError("Lastname is required with < 15 chars and not empty");
            }
            if (middlename.trim().length() > 30 || middlename.trim().length() == 0) {
                error = true;
                errors.setMiddlenameLengthError("Middlename is required with < 30 chars and not empty");
            }
            if (firstname.trim().length() > 15 || firstname.trim().length() == 0) {
                error = true;
                errors.setFirstnameLengthError("Firstname is required with < 15 chars and not empty");
            }
            if (address.trim().length() > 250 || address.trim().length() == 0) {
                error = true;
                errors.setAddressLengthError("Address is required with < 250 chars and not empty");
            }
            if (phone.trim().length() > 11 || phone.trim().length() == 0) {
                error = true;
                errors.setPhoneFormatError("Phone is required with < 11 chars and not empty");
            }
            if (username.trim().length() > 20 || username.trim().length() == 0) {
                error = true;
                errors.setUsernameLengthError("Username is required with < 20 chars and not empty");
            }
            if (password.trim().length() > 11 || password.trim().length() == 0) {
                error = true;
                errors.setPasswordLengthError("Password is required with < 11 chars and not empty");
            }
            if (error) {
                request.setAttribute("ERRORS", errors);
            } else {
                CustomerDTO custDTO = new CustomerDTO(custID, lastname, middlename, firstname, address, phone, 1);
                boolean dupicatedAccount = accDAO.isExistedAccount(username);
                if (dupicatedAccount == false) {
                    boolean resultCreateCustInfo = custDAO.addNewCustomer(custDTO);
                    boolean resultCreateAcc = accDAO.createNewAccount(username, password, custID);
                    if (resultCreateCustInfo && resultCreateAcc) {
                        request.setAttribute("STATUS", "Your Account has been created!!");
                    }
                } else {
                    errors.setDuplicateUsernameError("Username is existed already!!");
                    request.setAttribute("ERRORS", errors);

                }
            }
        } catch (NamingException ex) {
            log("RegisterServlet_NamingException: " + ex.getMessage());
        } catch (SQLException ex) {
            log("RegisterServlett_SQLException: " + ex.getMessage());
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
