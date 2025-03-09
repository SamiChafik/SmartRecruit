<%@ page import="java.util.List" %>
<%@ page import="com.example.smartrecruit.model.Candidature" %>
<%@ page import="com.example.smartrecruit.model.User" %>
<%@ page import="com.example.smartrecruit.model.OffreEmploi" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Candidates</title>
</head>
<body>
<jsp:include page="header.jsp" />
<main class="container mt-5">
    <section>
        <h2 class="mb-4">Candidates for Offer: <%= request.getAttribute("offerTitle") %></h2>
        <table class="table table-bordered table-striped">
            <thead class="thead-dark">
            <tr>
                <th>Candidate ID</th>
                <th>Candidate Name</th>
                <th>Offer ID</th>
                <th>Offer Title</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Candidature> candidates = (List<Candidature>) request.getAttribute("candidates");
                for (Candidature cand : candidates) {
                    User candidate = cand.getUser();
                    OffreEmploi offer = cand.getOffer();
            %>
            <tr>
                <td><%= cand.getC_id() %></td>
                <td><%= candidate != null ? candidate.getFirst_name() + " " + candidate.getLast_name() : "N/A" %></td>
                <td><%= offer != null ? offer.getOffer_id() : "N/A" %></td>
                <td><%= offer != null ? offer.getTitle() : "N/A" %></td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </section>
</main>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>