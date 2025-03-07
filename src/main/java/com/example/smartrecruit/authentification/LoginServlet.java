package com.example.smartrecruit.authentification;

import com.example.smartrecruit.DAO.UserDAO;
import com.example.smartrecruit.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet({"/", "/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/log_in.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDAO.checkLogin(email, password);

        if (user != null) {

            HttpSession session = request.getSession();
            session.setAttribute("user_id", user.getId());
            session.setAttribute("role", user.getRole());

            switch (user.getRole()) {
                case "admin":
                    response.sendRedirect("/user?action=edit_form");
                    break;
                case "recruiteur":
                    response.sendRedirect("/user?action=edit_form");
                    break;
                case "candidat":
                    response.sendRedirect("/user?action=edit_form");
                    break;
                default:
                    request.setAttribute("errorMessage", "Invalid username or password");
                    response.sendRedirect("/log_in.jsp");
                    break;
            }
        } else {
            request.setAttribute("errorMessage", "Invalid username or password");
            response.sendRedirect("/log_in.jsp");
        }
    }
}

