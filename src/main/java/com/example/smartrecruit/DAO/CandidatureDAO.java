package com.example.smartrecruit.DAO;

import com.example.smartrecruit.model.Candidature;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidatureDAO {

    // Use the getConnection() method to get a connection for each database operation
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

    public void createCandidatureTable() {
        String sqlQuery = "CREATE TABLE IF NOT EXISTS candidature (" +
                "c_id INT PRIMARY KEY AUTO_INCREMENT, " +
                "user_id INT NOT NULL, " +
                "offer_id INT NOT NULL" +
                ")";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlQuery);
            System.out.println("Table 'candidature' created successfully!");
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        }
    }

    public void apply(Candidature cand) {
        String sql = "INSERT INTO candidature (offer_id, user_id) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cand.getOffer_id());
            ps.setInt(2, cand.getUser_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Candidature> getCandidatures() {
        List<Candidature> candList = new ArrayList<>();
        String sql = "SELECT * FROM candidature";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Candidature cand = new Candidature();
                cand.setC_id(rs.getInt("c_id"));
                cand.setOffer_id(rs.getInt("offer_id"));
                cand.setUser_id(rs.getInt("user_id"));
                candList.add(cand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candList;
    }

    public Candidature getCandidatureById(int id) {
        Candidature cand = null;
        String sql = "SELECT * FROM candidature WHERE c_id=?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cand = new Candidature();
                    cand.setC_id(rs.getInt("c_id"));
                    cand.setOffer_id(rs.getInt("offer_id"));
                    cand.setUser_id(rs.getInt("user_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cand;
    }

    public List<Candidature> getCandidaturesByOfferId(int offer_id) {
        List<Candidature> candList = new ArrayList<>();
        String sql = "SELECT * FROM candidature WHERE offer_id=?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, offer_id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Candidature cand = new Candidature();
                    cand.setC_id(rs.getInt("c_id"));
                    cand.setOffer_id(rs.getInt("offer_id"));
                    cand.setUser_id(rs.getInt("user_id"));
                    candList.add(cand);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candList;
    }

    public List<Candidature> getCandidaturesByUserId(int user_id) {
        List<Candidature> candList = new ArrayList<>();
        String sql = "SELECT * FROM candidature WHERE user_id=?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, user_id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Candidature cand = new Candidature();
                    cand.setC_id(rs.getInt("c_id"));
                    cand.setOffer_id(rs.getInt("offer_id"));
                    cand.setUser_id(rs.getInt("user_id"));
                    candList.add(cand);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return candList;
    }

    public void updateCandidature(Candidature cand) {
        String sql = "UPDATE candidature SET offer_id=?, user_id=? WHERE c_id=?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, cand.getOffer_id());
            ps.setInt(2, cand.getUser_id());
            ps.setInt(3, cand.getC_id());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCandidature(int c_id) {
        String sql = "DELETE FROM candidature WHERE c_id=?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, c_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}