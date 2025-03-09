package com.example.smartrecruit.controllers;

import com.example.smartrecruit.DAO.CandidatureDAO;
import com.example.smartrecruit.DAO.OffreEmploiDAO;
import com.example.smartrecruit.model.Candidature;
import com.example.smartrecruit.model.OffreEmploi;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/CandidatureServlet")
public class CandidatureServlet extends HttpServlet {
    private CandidatureDAO cDao;

    public void init() {
        cDao = new CandidatureDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("user_id");

        if ("viewAppliedOffers".equals(action)) {
            List<Candidature> appliedOffers = cDao.getCandidaturesByUserId(userId);
            req.setAttribute("appliedOffers", appliedOffers);
            req.getRequestDispatcher("appliedOffers.jsp").forward(req, resp);

        } else if ("viewCandidates".equals(action)) {
            int offerId = Integer.parseInt(req.getParameter("offer_id"));
            List<Candidature> candidates = cDao.getCandidaturesByOfferId(offerId);

            OffreEmploiDAO offreEmploiDAO = new OffreEmploiDAO();
            OffreEmploi offer = offreEmploiDAO.getOffreById(offerId);
            req.setAttribute("offerTitle", offer != null ? offer.getTitle() : "N/A");

            req.setAttribute("candidates", candidates);
            req.getRequestDispatcher("viewCandidates.jsp").forward(req, resp);

        } else if ("apply".equals(action)) {
            int offerId = Integer.parseInt(req.getParameter("offer_id"));
            Candidature cand = new Candidature();
            cand.setOffer_id(offerId);
            cand.setUser_id(userId);
            cDao.apply(cand);
            resp.sendRedirect(req.getContextPath() + "/OfferServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}