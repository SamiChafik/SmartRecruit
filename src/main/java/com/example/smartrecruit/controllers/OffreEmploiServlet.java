package com.example.smartrecruit.controllers;

import com.example.smartrecruit.DAO.OffreEmploiDAO;
import com.example.smartrecruit.model.OffreEmploi;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class OffreEmploiServlet extends HttpServlet {
  private OffreEmploiDAO offerdao;
    public void init(){
    offerdao=new OffreEmploiDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if("update".equals(action)){
            int id = Integer.parseInt(req.getParameter("id"));
            OffreEmploi offer= offerdao.get_offre_byId(id);
            req.setAttribute("offer",offer);
            req.getRequestDispatcher("UpdatOffre.jsp").forward(req,resp);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            offerdao.delete_offer(id);
            resp.sendRedirect(req.getContextPath()+"/OffreEmploiServlet");

        }else {
            List<OffreEmploi> offerlist = offerdao.display_offers();
            req.setAttribute("offerlist",offerlist);
            req.getRequestDispatcher("OfferList.jsp").forward(req,resp);
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("update".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String title= req.getParameter("title");
            String description= req.getParameter("description");
           int pubdate = Integer.parseInt(req.getParameter("pubdate"));

            OffreEmploi offer= new OffreEmploi(title,description,pubdate);
            offer.setOffer_id(id);
            offerdao.update_offer(offer);
        }else {
            String title= req.getParameter("title");
            String description= req.getParameter("description");
            int pubdate = Integer.parseInt(req.getParameter("pubdate"));
            OffreEmploi offer= new OffreEmploi(title,description,pubdate);
            offerdao.add_offre_emploi(offer);
        }
        resp.sendRedirect(req.getContextPath()+"/OffreEmploiServlet");
    }
}
