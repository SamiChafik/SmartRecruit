<%@ page import="java.util.List" %>
<%@ page import="com.example.smartrecruit.model.OffreEmploi" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="styles/OfferList.css">
    <title>Offers</title>
</head>
<body>
<main>
    <section>
      <h2 id="tt">List of job offers</h2>
        <table class="table table-bordered table-striped">
            <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>TITLE</th>
                <th>DESCRIPTION</th>
                <th>DATE OF PUBLICATION</th>

            </tr>
            </thead>
              <tbody>
              <% List<OffreEmploi> offerlist =(List<OffreEmploi>) request.getAttribute("offerlist");
              for(OffreEmploi offer :offerlist){


              %>
              <tr>
                  <td><%= offer.getOffer_id()%></td>
                  <td><%= offer.getTitle()%></td>
                  <td><%= offer.getDescription()%></td>
                  <td><%= offer.getPubDate()%></td>
                  <td>
                      <a href="OfferServlet?action=update&id=<%= offer.getOffer_id() %>" class="btn btn-primary">Update</a>
                      <a href="OfferServlet?action=delete&id=<%= offer.getOffer_id() %>" class="btn btn-primary">Delete</a>
                  </td>

              </tr>
              <% } %>
              </tbody>

        </table>
        <a href="addOffer.jsp" class="btn btn-primary" id="addbtn">Add new Offer</a>
    </section>
</main>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>