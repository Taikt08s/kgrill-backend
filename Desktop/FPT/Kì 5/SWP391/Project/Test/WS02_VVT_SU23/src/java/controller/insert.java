/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Registration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.RegistrationInsertError;

/**
 *
 * @author TINH KHUNG
 */
@WebServlet(name = "insert", urlPatterns = {"/insert"})
public class insert extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            String fullname = request.getParameter("fullname");
            String url = null;
            boolean rs = true;
            RegistrationInsertError error = new RegistrationInsertError();

            if (username.length() <= 5 || username.length() >= 13) {
                rs = false;
                error.setUsernameLengthErr(username);
            }

            if (password.length() <= 7 || password.length() >= 21) {
                rs = false;
                error.setPasswordLengthErr(password);
            }

            if (!confirm.equals(password)) {
                rs = false;
                error.setConfirmNotMatch(confirm);
            }

            if (fullname.length() <= 1 || fullname.length() >= 41) {
                rs = false;
                error.setFullnameLengthErr(fullname);
            }

            try {
                if (rs == true) {
                    DAO dao = new DAO();
                    dao.insert(username, password, fullname);
                    request.setAttribute("url", "insert");
                    url = "thongbao.jsp";
                } else if (rs == false) {
                    throw new Exception();
                }

            } catch (Exception e) {
                e.printStackTrace();
                error.setUsernameIsExisted(username);
                request.setAttribute("url", "invalid");
                url = "thongbao.jsp";
            } finally {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);

            }

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
