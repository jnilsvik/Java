package Servlets;

import Classes.SimpleCalculator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SimpleCalculatorServlet", urlPatterns = "/SimpleCalculatorServlet")
public class SimpleCalculatorServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        float firstNumber = Float.parseFloat(request.getParameter("firstNumber"));
        float secondNumber = Float.parseFloat(request.getParameter("secondNumber"));
        request.setAttribute("firstNumber", firstNumber);
        request.setAttribute("secondNumber", secondNumber);

        String operator = request.getParameter("operator");

        SimpleCalculator sc = new SimpleCalculator();
        float result = sc.calculate(firstNumber, secondNumber, operator);

        request.setAttribute("result", result);
        request.getRequestDispatcher("simpleCalculator.jsp").forward(request, response);
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