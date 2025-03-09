package com.example.smartrecruit.authentification;

import com.example.smartrecruit.DAO.CandidatureDAO;
import com.example.smartrecruit.DAO.OffreEmploiDAO;
import com.example.smartrecruit.DAO.UserDAO;
import com.example.smartrecruit.model.User;
import jakarta.servlet.RequestDispatcher;
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
        userDAO.createUserTable();

        OffreEmploiDAO offreEmploiDAO = new OffreEmploiDAO();
        offreEmploiDAO.createOfferTable();

        CandidatureDAO candidatureDAO = new CandidatureDAO();
        candidatureDAO.createCandidatureTable();
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
            session.setAttribute("user", user);


            switch (user.getRole()) {
                case "admin":
                    response.sendRedirect(request.getContextPath() + "/OfferServlet");
                    break;
                case "recruiteur":
                    response.sendRedirect(request.getContextPath() + "/OfferServlet");
                    break;
                case "candidat":
                    response.sendRedirect(request.getContextPath() + "/OfferServlet");
                    break;
                default:
                    request.setAttribute("errorMessage", "Invalid role");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/log_in.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } else {
            request.setAttribute("errorMessage", "Invalid username or password");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/log_in.jsp");
            dispatcher.forward(request, response);
        }
    }
}