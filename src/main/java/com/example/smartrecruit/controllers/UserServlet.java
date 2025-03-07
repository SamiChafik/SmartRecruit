package com.example.smartrecruit.controllers;

import com.example.smartrecruit.DAO.Role;
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
import java.sql.SQLException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
        userDAO.createUserTable();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertUser(request, response);
                    break;
                case "list":
//                    listUser(request, response);
                    break;
                case "delete":
                    deleteUser(request, response);
                    break;
                case "edit_form":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateUser(request, response);
                    break;
                default:
//                    showNewForm(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("addUser.jsp");
        dispatcher.forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String last_name = request.getParameter("last_name");
        String first_name = request.getParameter("first_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        User newUser = new User();
        newUser.setLast_name(last_name);
        newUser.setFirst_name(first_name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setRole(role);

        userDAO.addUser(newUser);

        response.sendRedirect("/log_in.jsp");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("user_id");

        User existingUser = userDAO.selectUser(userId);
        if (existingUser == null) {

            response.sendRedirect("user?action=new");
            return;
        }

        request.setAttribute("user", existingUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editUser.jsp");
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("user_id"); // Get user ID from session

        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Create the User object with the correct parameters
        User user = new User(id, last_name, first_name, email, password, role);

        boolean updated = userDAO.updateUser(user);

        if (updated) {
            response.sendRedirect("logout"); // Redirect to the user list or profile page
        } else {
            request.setAttribute("errorMessage", "Failed to update user");
            RequestDispatcher dispatcher = request.getRequestDispatcher("editUser.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("user_id"); // Get user ID from session

        boolean deleted = userDAO.deleteUser(userId);

        if (deleted) {
            session.invalidate(); // Invalidate the session after account deletion
            response.sendRedirect("/log_in.jsp"); // Redirect to the login page
        } else {
            request.setAttribute("errorMessage", "Failed to delete user");
            RequestDispatcher dispatcher = request.getRequestDispatcher("editUser.jsp");
            dispatcher.forward(request, response);
        }
    }
}
