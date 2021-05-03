package Servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MultiplicationTableServlet", urlPatterns = {"/MultiplicationTableServlet"})
public class MultiplicationTableServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int upperLimit = Integer.parseInt(request.getParameter("upperLimit"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        request.setAttribute("upperLimit", upperLimit);
        request.setAttribute("quantity", quantity);

        request.getRequestDispatcher("multiplicationTableResult.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }
}