package servlets;

import models.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

import static utils.PasswordEncrypting.encrypt;
import static utils.SessionRetrieval.getSessionUser;
import static utils.Validating.validateUser;


@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends Servlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("email") != null) {
            response.sendRedirect("UserProfile");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email").toLowerCase();
        String password = encrypt(request.getParameter("password"));

        if (validateUser(email, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);

            User user = getSessionUser(request);
            session.setAttribute("user", user);

            response.sendRedirect("UserProfile?message=" + URLEncoder.encode("Welcome to Norges Roforbund!", "UTF-8"));
        } else {
            response.sendRedirect("index.jsp?errorMessage=" + URLEncoder.encode("Incorrect email or password! Try again", "UTF-8"));
        }
    }
}