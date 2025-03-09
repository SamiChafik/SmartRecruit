package com.example.smartrecruit.DAO;

import com.example.smartrecruit.model.OffreEmploi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OffreEmploiDAO {
    private Connection conn;
    public OffreEmploiDAO() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/smartrecuit","root","");
            System.out.println("Connected to the database successfully");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to connect to database");;
        }
    }
 public void add_offre_emploi(OffreEmploi offreEmploi){
        String sql="insert into offer(title , description, pub_date) values(?,?,?)";
        try(PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setString(1,offreEmploi.getTitle());
            ps.setString(2,offreEmploi.getDescription());
            ps.setString(3,offreEmploi.getPubDate());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

 }
 public List<OffreEmploi> display_offers(){
        List<OffreEmploi> offerlist = new ArrayList<>();
        String sql ="select *from offer";
        try(PreparedStatement ps =conn.prepareStatement(sql); ResultSet res =ps.executeQuery()){
         while(res.next()){
             OffreEmploi offer = new OffreEmploi();
             offer.setOffer_id(res.getInt("offer_id"));
             offer.setTitle(res.getString("title"));
             offer.setDescription(res.getString("description"));
             offer.setPubDate(res.getString("pub_date"));
             offerlist.add(offer);
         }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return offerlist;
 }
 public OffreEmploi get_offre_byId(int offer_id){
OffreEmploi offer =null;
        String sql ="select *from offer where offer_id=?";
        try(PreparedStatement ps =conn.prepareStatement(sql)){
            ps.setInt(1,offer_id);
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()){
                    offer = new OffreEmploi();
                    offer.setOffer_id(rs.getInt("offer_id"));
                    offer.setTitle(rs.getString("title"));
                    offer.setDescription(rs.getString("description"));
                    offer.setPubDate(rs.getString("pub_date"));

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return offer;
 }
 public void update_offer(OffreEmploi offre){
     System.out.println("ggggg");
        String sql ="update offer set title=?,description=?,pub_date=? where offer_id=?";
     System.out.println("ghjk");
        try(PreparedStatement ps =conn.prepareStatement(sql)){
            System.out.println("mmmm");
            ps.setString(1,offre.getTitle());
            ps.setString(2,offre.getDescription());
            ps.setString(3, offre.getPubDate());
            ps.setInt(4, offre.getOffer_id());
            ps.executeUpdate();
            System.out.println("bjnk,");
        }catch (Exception e){
            e.printStackTrace();
        }
 }
public  void delete_offer(int offer_id){
        String sql ="delete from offer where offer_id=?";
        try(PreparedStatement ps =conn.prepareStatement(sql)){
            ps.setInt(1,offer_id);
            ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
}
}
