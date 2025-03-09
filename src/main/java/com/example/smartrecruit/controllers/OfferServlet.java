package com.example.smartrecruit.controllers;

import com.example.smartrecruit.DAO.OffreEmploiDAO;
import com.example.smartrecruit.model.OffreEmploi;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
@WebServlet("/OfferServlet")
public class OfferServlet extends HttpServlet {
  private OffreEmploiDAO offerdao;
    public void init(){
    offerdao=new OffreEmploiDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if("update".equals(action)){
            int id = Integer.parseInt(req.getParameter("id"));
            OffreEmploi offer= offerdao.getOffreById(id);
            req.setAttribute("offer",offer);
            req.getRequestDispatcher("UpdateOffer.jsp").forward(req,resp);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            offerdao.deleteOffer(id);
            resp.sendRedirect(req.getContextPath()+"/OfferServlet");

        }else {
            List<OffreEmploi> offerlist = offerdao.displayOffers();
            req.setAttribute("offerlist",offerlist);
            req.getRequestDispatcher("OfferList.jsp").forward(req,resp);
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println("Action: " + action);
        if ("update".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String title= req.getParameter("title");
            String description= req.getParameter("description");
            String pub_date= req.getParameter("pub_date");

            OffreEmploi offer= new OffreEmploi(title,description,pub_date);
            offer.setOffer_id(id);
            offerdao.updateOffer(offer);
        }else {
            String title= req.getParameter("title");
            String description= req.getParameter("description");
            String pub_date= req.getParameter("pub_date");
            OffreEmploi offer= new OffreEmploi(title,description,pub_date);
            offerdao.addOffreEmploi(offer);
        }
        resp.sendRedirect(req.getContextPath()+"/OfferServlet");
    }
}
