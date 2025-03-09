//package com.example.smartrecruit.authentification;
//
//import com.example.smartrecruit.model.User;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//
//@WebFilter("/*")
//public class AppFilter implements Filter {
//
//    User user = new User();
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        HttpSession session = httpRequest.getSession(false);
//
//        String requestURI = httpRequest.getRequestURI();
//
//        if (requestURI.endsWith("/login") || requestURI.endsWith("/log_in.jsp") || requestURI.contains("/static/")) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        if (session == null || session.getAttribute("user") == null) {
//            httpResponse.sendRedirect(httpRequest.getContextPath() + "/log_in.jsp");
//            return;
//        }
//
//        String role = (String) session.getAttribute("role");
//
//        if (isAccessAllowed(requestURI, role)) {
//            chain.doFilter(request, response);
//        } else {
//            httpResponse.sendRedirect(httpRequest.getContextPath() + "/access-denied.jsp");
//        }
//    }
//
//    @Override
//    public void destroy() {
//    }
//
//    /**
//     * Check if the user's role is allowed to access the requested page.
//     *
//     * @param requestURI The requested URI.
//     * @param role       The user's role.
//     * @return True if access is allowed, false otherwise.
//     */
//    private boolean isAccessAllowed(String requestURI, String role) {
//        if (role == null) {
//            return false;
//        }
//
//        // Admin has access to everything
//        if ("admin".equals(role)) {
//            return true;
//        }
//
//        // Common pages accessible to all roles
//        if (requestURI.endsWith("/OfferServlet") || requestURI.endsWith("/logout")) {
//            return true;
//        }
//
//        // Candidat-specific pages
//        if ("candidat".equals(role)) {
//            return requestURI.endsWith("/user?action=edit_form&id=" + user.getId()) || // Edit profile
//                    requestURI.endsWith("/user?action=delete") ||   // Delete profile
//                    requestURI.endsWith("/OfferServlet") ||         // View all offers
//                    requestURI.endsWith("/CandidatureServlet?action=apply"); // Apply to offer
//        }
//
//        // Recruiteur-specific pages
//        if ("recruiteur".equals(role)) {
//            return requestURI.endsWith("/OfferServlet") ||                     // View all offers
//                    requestURI.endsWith("/addOffer.jsp") ||                    // Add offer
//                    requestURI.endsWith("/OfferServlet?action=update") ||      // Update offer
//                    requestURI.endsWith("/OfferServlet?action=delete") ||      // Delete offer
//                    requestURI.endsWith("/CandidatureServlet?action=viewCandidates"); // View candidates for offer
//        }
//
//        // Deny access by default
//        return false;
//    }
//}