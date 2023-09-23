/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author TINH KHUNG
 */
public class shoppingAddCart extends HttpServlet {

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
            String title = request.getParameter("booklist");
            Cookie[] cookies = request.getCookies(); // lay cookie
            if (cookies == null) { // truong hop chua co cookie
                Cookie cookie = new Cookie(title, "1"); 
                cookie.setMaxAge(60 * 5);
                response.addCookie(cookie); // tao moi 1 cookie
            } else { // Truong hop da co cookie
                
                // Truong hop: da co cookie can tang so luong
                boolean bFound = false;

                for (int i = 0; i < cookies.length; i++) { 
                    if (cookies[i].getName().equals(title)) { // tim ra cookie cang tang so luong
                        bFound = true;
                        String value = cookies[i].getValue(); // lay gia tri cookie
                        int quantity = Integer.parseInt(value) + 1; // tang gia tri cookie len 1
                        Cookie cookie = new Cookie(title, String.valueOf(quantity)); // ep kieu
                        cookie.setMaxAge(60 * 5); // set lai thoi gian cookie
                        response.addCookie(cookie); // override cookie moi 
                        request.setAttribute("act","add");
                        break;
                    }

                }
                // Truong hop: chua co cookie can tang so luong
                if (!bFound) { 
                    Cookie cookie = new Cookie(title, "1");
                    cookie.setMaxAge(60 * 5);
                    response.addCookie(cookie);
                }
            }
            response.sendRedirect("shopping.jsp");
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
