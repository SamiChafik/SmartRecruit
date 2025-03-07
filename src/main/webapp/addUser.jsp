<%@ page import="com.example.smartrecruit.DAO.Role" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="styles/addUser.css">
    <title>Ajouter</title>
</head>
<body>
<main>
    <section>
        <div id="form">
            <form id="form1" class="form1" action="user?action=insert" method="post">
                <h2>Créer un compte</h2>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputNom">Nom</label>
                        <input type="text" class="form-control" id="inputNom" name="last_name" placeholder="Nom">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputPrenom">Prénom</label>
                        <input type="text" class="form-control" id="inputPrenom" name="first_name" placeholder="Prénom">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail">Email</label>
                    <input type="email" class="form-control" id="inputEmail" name="email" placeholder="Email@exemple.com">
                </div>
                <div class="form-group">
                    <label for="password">Mot de passe</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe">
                </div>
                <!-- Role Selection -->
                <div class="form-group">
                    <label>Rôle</label>
                    <div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="role" id="roleCandidat" value="candidat" >
                            <label class="form-check-label" for="roleCandidat">Candidat</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="role" id="roleRecruiter" value="recruiteur">
                            <label class="form-check-label" for="roleRecruiter">Recruiteur</label>
                        </div>
                    </div>
                </div>
                <div id="btn">
                    <button type="submit" class="btn btn-primary">S'inscrire</button>
                </div>
            </form>
            <a href="/login"><button class="btn btn-primary btnLogin">Se connecter</button></a>
        </div>
    </section>
</main>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>