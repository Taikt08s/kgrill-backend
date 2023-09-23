/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TINH KHUNG
 */
public class mainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String WELCOME = "welcome.jsp";
    private final String Home = "login.jsp";
    private final String LOGIN = "login";
    private final String DELETE = "delete";
    private final String UPDATE = "update";
    private final String SEARCH = "search";
    private final String REGISTER = "insert";
    private final String NULL = "null";
    private final String ADDCART = "shoppingAddCart";
    private final String VIEWCART = "shoppingViewCart";
    private final String REMOVECART = "shoppingRemoveCart";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url = "";
            String button = request.getParameter("btn");
            if (button == null) {
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    boolean check = false;
                    for (int j = 0; j < cookies.length; j++) {
                        if (cookies[j].getName().equals("user")) { // Duyet cho den khi tim thay cookie 
                            cookies[j].setMaxAge(60);
                            url = WELCOME;
                            check = true;
                            break;
                        }
                    } // for j
                    if (check != true) {
                        url = Home;
                    }
                } else {
                    url = Home;
                }
            } else if (button.equals("Login")) {
                url = LOGIN;
            } else if (button.equals("Logout")) {
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (int j = 0; j < cookies.length; j++) {
                        if (cookies[j].getName().equals("user")) { // Duyet cho den khi tim thay cookie can phai xoa
                            cookies[j].setValue("0");
                            cookies[j].setMaxAge(0);
                            response.addCookie(cookies[j]); // overide cookie moi 
                            break;
                        }
                    } // for j
                }
                url = Home;

            } else if (button.equals("Search")) {
                url = SEARCH;
            } else if (button.equals("Update")) {
                url = UPDATE;
            } else if (button.equals("Delete")) {
                url = DELETE;
            } else if (button.equals("Register")) {
                url = REGISTER;
            } else if (button.equals("Add")) {
                url = ADDCART;
            } else if (button.equals("View")) {
                url = VIEWCART;
            } else if (button.equals("Remove")) {
                url = REMOVECART;
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
