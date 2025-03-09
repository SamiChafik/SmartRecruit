package com.example.smartrecruit.DAO;

import com.example.smartrecruit.model.Candidature;
import com.example.smartrecruit.model.OffreEmploi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CandidatureDAO {
    private Connection conn;

    public CandidatureDAO() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/smartrecuit","root","");
            System.out.println("Connected to the database successfully");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to connect to database");;
        }
    }
    public  void Apply(Candidature cand){
        String sql ="insert into candidature ( offer_id , user_id , c_id) values(?,?,?)";
        try(PreparedStatement ps =conn.prepareStatement(sql)){
          ps.setInt(1,cand.getOffer_id());
          ps.setInt(2,cand.getUser_id());
          ps.setInt(3,cand.getC_id());
          ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Candidature> getCandidatures(){
        List<Candidature> candlist =new ArrayList<Candidature>();
        String sql ="select * from candidature";
        try(PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs =ps.executeQuery()){
            while (rs.next()){
                Candidature cand = new Candidature();
                cand.setC_id(rs.getInt("c_id"));
                cand.setOffer_id(rs.getInt("offer_id"));
                cand.setUser_id(rs.getInt("user_id"));
                candlist.add(cand);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return candlist;
    }
    public Candidature getcanById(int id){
        Candidature cand = null;
        String sql ="select * from candidature where c_id=?";
        try(PreparedStatement ps=conn.prepareStatement(sql);ResultSet rs= ps.executeQuery()){
            if (rs.next()){
                cand = new Candidature();
                cand.setC_id(rs.getInt("c_id"));
                cand.setOffer_id(rs.getInt("offer_id"));
                cand.setUser_id(rs.getInt("user_id"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return cand;
    }
    public List<Candidature> getCandidaturesByOfferId(int offer_id){
        List<Candidature> candlist =new ArrayList<>();
        String sql="select * from candidature where offer_id=?";
        try(PreparedStatement ps=conn.prepareStatement(sql);ResultSet rs=ps.executeQuery()){
            ps.setInt(1,offer_id);

        }catch (Exception e){
            e.printStackTrace();
        }
        return candlist;
    }
   public  List<Candidature> getCandidaturesByUserId(int user_id){
        List<Candidature> candlist =new ArrayList<>();
        String sql ="select * from candidature where user_id=?";
        try (PreparedStatement ps =conn.prepareStatement(sql);ResultSet rs=ps.executeQuery()){
            ps.setInt(1,user_id);

        }catch (Exception e){
            e.printStackTrace();
        }
        return candlist;
   }
   public void updateCandidature(Candidature cand){
        String sql="UPDATE candidature SET title=?,description=?,pub_date=? where c_id=?";
      try(PreparedStatement ps=conn.prepareStatement(sql)){


      }catch (Exception e){
          e.printStackTrace();
      }
   }
   public void deleteCandidature(int c_id){
        String sql ="delete from candidature where c_id=?";
        try(PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,c_id);
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
   }
}

