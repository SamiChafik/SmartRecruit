<% User user = null; %>
<%@ page import="com.example.smartrecruit.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="styles/addUser.css">
</head>
<body>
<header>
    <nav>
        <a href="/user?action=edit_form&id=<%=user != null ? user.getId() : ""%>"><button class="btn btn-primary">Modifier</button></a>
        <a href="/user?action=list"><button class="btn btn-warning">Liste of users</button></a>
        <a href="/logout"><button class="btn btn-danger">Déconnecter</button></a>
    </nav>
</header>
<main>
    <section>
        <div id="form">
            <%
                user = (User) request.getAttribute("user");
                String loggedInUserRole = (String) session.getAttribute("role");
            %>
            <form id="form1" class="form1" action="user?action=update" method="post">
                <h2>Modifier</h2>
                <input type="hidden" name="id" value="<%=user != null ? user.getId() : ""%>">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputNom">Nom</label>
                        <input type="text" class="form-control" id="inputNom" name="last_name" value="<%=user != null ? user.getLast_name() : ""%>">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputPrenom">Prénom</label>
                        <input type="text" class="form-control" id="inputPrenom" name="first_name" value="<%=user != null ? user.getFirst_name() : ""%>">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail">Email</label>
                    <input type="email" class="form-control" id="inputEmail" name="email" value="<%=user != null ? user.getEmail() : ""%>">
                </div>
                <div class="form-group">
                    <label for="password">Mot de passe</label>
                    <input type="password" class="form-control" id="password" name="password" value="<%=user != null ? user.getPassword() : ""%>">
                </div>
                <div class="form-group">
                    <label>Rôle</label>
                    <div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="role" id="roleCandidat" value="candidat" <%=user != null && user.getRole().equalsIgnoreCase("candidat") ? "checked" : ""%>>
                            <label class="form-check-label" for="roleCandidat">Candidat</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="role" id="roleRecruiter" value="recruiteur" <%=user != null && user.getRole().equalsIgnoreCase("recruiteur") ? "checked" : ""%>>
                            <label class="form-check-label" for="roleRecruiter">Recruteur</label>
                        </div>

                        <%
                            if (loggedInUserRole != null && loggedInUserRole.equalsIgnoreCase("admin")) {
                        %>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="role" id="roleAdmin" value="admin"
                                <%=user != null && user.getRole().equalsIgnoreCase("admin") ? "checked" : ""%>>
                            <label class="form-check-label" for="roleAdmin">Admin</label>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
                <div id="btn">
                    <button type="submit" class="btn btn-primary">Modifier</button>
                </div>
            </form>
            <form id="deleteForm" action="user?action=delete" method="post" style="display: inline;">
                <input type="hidden" name="id" value="<%=user != null ? user.getId() : ""%>">
                <button type="submit" class="btn btn-danger">Supprimer</button>
            </form>
        </div>
    </section>
</main>
</body>
</html>