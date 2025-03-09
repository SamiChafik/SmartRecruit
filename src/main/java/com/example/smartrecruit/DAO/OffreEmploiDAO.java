package com.example.smartrecruit.DAO;

import com.example.smartrecruit.model.OffreEmploi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OffreEmploiDAO {

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartrecruit", "root", "123456789");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database: " + e.getMessage());
        }
        return connection;
    }

    public void createOfferTable() {
        String sqlQuery = "CREATE TABLE IF NOT EXISTS offer (" +
                "offer_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "title VARCHAR(100) NOT NULL, " +
                "description VARCHAR(250) NOT NULL, " +
                "pub_date DATE NOT NULL" +
                ")";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlQuery);
            System.out.println("Table 'offer' created successfully!");
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }

    public void addOffreEmploi(OffreEmploi offreEmploi) {
        String sql = "INSERT INTO offer (title, description, pub_date) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, offreEmploi.getTitle());
            ps.setString(2, offreEmploi.getDescription());
            ps.setDate(3, Date.valueOf(offreEmploi.getPubDate())); // Convert String to SQL Date
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<OffreEmploi> displayOffers() {
        List<OffreEmploi> offerList = new ArrayList<>();
        String sql = "SELECT * FROM offer";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                OffreEmploi offer = new OffreEmploi();
                offer.setOffer_id(rs.getInt("offer_id"));
                offer.setTitle(rs.getString("title"));
                offer.setDescription(rs.getString("description"));
                offer.setPubDate(rs.getDate("pub_date").toString()); // Convert SQL Date to String
                offerList.add(offer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offerList;
    }

    public OffreEmploi getOffreById(int offer_id) {
        OffreEmploi offer = null;
        String sql = "SELECT * FROM offer WHERE offer_id=?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, offer_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    offer = new OffreEmploi();
                    offer.setOffer_id(rs.getInt("offer_id"));
                    offer.setTitle(rs.getString("title"));
                    offer.setDescription(rs.getString("description"));
                    offer.setPubDate(rs.getDate("pub_date").toString()); // Convert SQL Date to String
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offer;
    }

    public void updateOffer(OffreEmploi offre) {
        String sql = "UPDATE offer SET title=?, description=?, pub_date=? WHERE offer_id=?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, offre.getTitle());
            ps.setString(2, offre.getDescription());
            ps.setDate(3, Date.valueOf(offre.getPubDate())); // Convert String to SQL Date
            ps.setInt(4, offre.getOffer_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOffer(int offer_id) {
        String sql = "DELETE FROM offer WHERE offer_id=?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, offer_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}