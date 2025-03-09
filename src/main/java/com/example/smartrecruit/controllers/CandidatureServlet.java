package com.example.smartrecruit.controllers;

import com.example.smartrecruit.DAO.CandidatureDAO;
import com.example.smartrecruit.model.Candidature;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/CandidatureServlet")
public class CandidatureServlet  extends HttpServlet {
    private CandidatureDAO cDao;
    public  void init(){
        cDao=new CandidatureDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");
        if("update".equals(action)){
            int id=Integer.parseInt(req.getParameter("c_id"));
            Candidature cand =cDao.getCandidatureById(id);
            req.setAttribute("candidature",cand);
            req.getRequestDispatcher("").forward(req,resp);
        } else if ("delete".equals(action)) {
            int id=Integer.parseInt(req.getParameter("c_id"));
            cDao.deleteCandidature(id);
            resp.sendRedirect(req.getContextPath()+"/CandidatureServlet");

        }else{
            List<Candidature> candlist = cDao.getCandidatures();
            req.setAttribute("candlist",candlist);
            req.getRequestDispatcher("").forward(req,resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");
        if("update".equals(action)){
            int id=Integer.parseInt(req.getParameter("c_id"));

        }else {
            Candidature cand=new Candidature();
            cDao.apply(cand);
        }
        resp.sendRedirect(req.getContextPath()+"/CandidatureServlet");
    }
}
