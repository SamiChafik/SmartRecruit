<%@ page import="com.example.smartrecruit.model.User" %>
<%@ page session="true" %>
<%
    User user = (User) session.getAttribute("user");
    String role = (String) session.getAttribute("role");
    String firstName = user != null ? user.getFirst_name() : "";
    String lastName = user != null ? user.getLast_name() : "";
%>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <!-- Brand/Logo with User's Name -->
        <a class="navbar-brand" href="#">
            Welcome, <%= firstName %> <%= lastName %> | <%= role%>
        </a>

        <!-- Toggle Button for Mobile -->
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <!-- Navbar Links -->
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <!-- Common actions for all roles -->
                <li class="nav-item">
                    <a class="nav-link" href="/OfferServlet">
                        <button class="btn btn-outline-primary">List of Offers</button>
                    </a>
                </li>

                <!-- Actions for admin -->
                <% if ("admin".equals(role)) { %>
                <li class="nav-item">
                    <a class="nav-link" href="/user?action=list">
                        <button class="btn btn-outline-warning">List of Users</button>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/user?action=edit_form&id=<%=user != null ? user.getId() : ""%>">
                        <button class="btn btn-outline-primary">Edit Profile</button>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/addOffer.jsp">
                        <button class="btn btn-outline-success">Create Offer</button>
                    </a>
                </li>
                <% } %>

                <!-- Actions for candidat -->
                <% if ("candidat".equals(role)) { %>
                <li class="nav-item">
                    <a class="nav-link" href="/user?action=edit_form&id=<%=user != null ? user.getId() : ""%>">
                        <button class="btn btn-outline-primary">Edit Profile</button>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/CandidatureServlet?action=viewAppliedOffers">
                        <button class="btn btn-outline-info">Offers I Applied To</button>
                    </a>
                </li>
                <% } %>

                <!-- Actions for recruiteur -->
                <% if ("recruiteur".equals(role)) { %>
                <li class="nav-item">
                    <a class="nav-link" href="/addOffer.jsp">
                        <button class="btn btn-outline-success">Create Offer</button>
                    </a>
                </li>
                <% } %>

                <!-- Logout button for all roles -->
                <li class="nav-item">
                    <a class="nav-link" href="/logout">
                        <button class="btn btn-danger">Logout</button>
                    </a>
                </li>
            </ul>
        </div>
    </nav>
</header>
