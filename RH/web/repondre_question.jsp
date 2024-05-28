<%-- 
    Document   : Annonce_RH
    Created on : 29 sept. 2023, 08:58:44
    Author     : SABI
--%>
<%@page import="model.Question"%>
<%@page import="model.Poste"%>
<%@page import="model.Langue"%>
<%@page import="model.Degre"%>
<%@page import="model.Diplome"%>
<%@page import="model.Nationalite"%>
<%@page import="model.Service"%>
<%@page import="java.util.Vector"%>
<%
    Vector<Question> question=(Vector<Question>) request.getAttribute("question");
    int idcv = (int) request.getAttribute("idcv");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Dashboard - NiceAdmin Bootstrap Template</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
<!--  <link href="https://fonts.gstatic.com" rel="preconnect">-->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
  <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">

  <!-- =======================================================
  * Template Name: NiceAdmin
  * Updated: May 30 2023 with Bootstrap v5.3.0
  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>

  <!-- ======= Header ======= -->
  <header id="header" class="header fixed-top d-flex align-items-center">

    <div class="d-flex align-items-center justify-content-between">
      <a href="index2.html" class="logo d-flex align-items-center">
        <img src="assets/img/logo.png" alt="">
        <span class="d-none d-lg-block">Admin</span>
      </a>
      <i class="bi bi-list toggle-sidebar-btn"></i>
    </div><!-- End Logo -->

<!--    <div class="search-bar">
      <form class="search-form d-flex align-items-center" method="POST" action="#">
        <input type="text" name="query" placeholder="Search" title="Enter search keyword">
        <button type="submit" title="Search"><i class="bi bi-search"></i></button>
      </form>
    </div> End Search Bar -->

    <nav class="header-nav ms-auto">
      <ul class="d-flex align-items-center">
        <li class="nav-item dropdown pe-3">
          <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
            <img src="assets/img/profile-img.jpg" alt="Profile" class="rounded-circle">
            <span class="d-none d-md-block dropdown-toggle ps-2"><%=session.getAttribute("username")%></span>
          </a><!-- End Profile Iamge Icon -->

          <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
            <li class="dropdown-header">
              <h6><%=session.getAttribute("username")%></h6>
<!--              <span>Gestion</span>-->
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>
            <li>
              <a class="dropdown-item d-flex align-items-center" href="index.jsp">
                <i class="bi bi-box-arrow-right"></i>
                <span>Sign Out</span>
              </a>
            </li>
          </ul><!-- End Profile Dropdown Items -->
        </li><!-- End Profile Nav -->
      </ul>
    </nav><!-- End Icons Navigation -->

  </header><!-- End Header -->

  <!-- ======= Sidebar ======= -->
  <aside id="sidebar" class="sidebar">

    <ul class="sidebar-nav" id="sidebar-nav">

      <li class="nav-item">
        <a class="nav-link " href="Init_listeAnnonce">
          <i class="bi bi-grid"></i>
          <span>Dashboard</span>
        </a>
      </li>
    </ul>

  </aside><!-- End Sidebar-->

  <main id="main" class="main">

   <div class="pagetitle">
      <h1>TEST D'ADMISSION</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="Inint_listeAnnonce">Home</a></li>
          <li class="breadcrumb-item active">TEST D'ADMISSION</li>
        </ol>
      </nav>
    </div>
    <!--////-->
    <div class="container">
        <div class="card p-5">
            <div class="card-body">
                 <h5 class="card-title">REPONDER AUX QUESTIONS CI-DESSOUS</h5>
                 <form method="GET" action="Save_test">
                    <input type="hidden" value="<%= idcv %>" name="idcv">
                    <% for(int i=0;i<question.size();i++) { %>
                        <div class="row">
                            <div  class=" input mb-3 col-lg-12" id="question">
                                <label class="card-title" ><%= i+1 %> - <%= question.get(i).getQuestion() %></label>
                            </div>
                            <div class="row">
                                <% for(int j=0;j<question.get(i).getReponse().size();j++) { %>
                                    <div class="form-check col-lg-3  ">
                                        <input class="form-check-input mb-5" type="checkbox" name="reponse" value="<%= question.get(i).getIdQuestion() %>y<%= question.get(i).getReponse().get(j).getIdReponse() %>" >
                                        <label class="form-check-label">
                                            <%= question.get(i).getReponse().get(j).getReponse() %>
                                        </label>
                                    </div>
                                <% } %>
                            </div>
                        </div>
                     <% } %>
                    <div class="row">
                            <button type="submit" class="btn btn-primary ">VALIDER</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <footer id="footer" class="footer">
    <div class="copyright">
      &copy; Copyright <strong><span>NiceAdmin</span></strong>. All Rights Reserved
    </div>
    <div class="credits">
      <!-- All the links in the footer should remain intact. -->
      <!-- You can delete the links only if you purchased the pro version. -->
      <!-- Licensing information: https://bootstrapmade.com/license/ -->
      <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
      Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
    </div>
  </footer><!-- End Footer -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

  <!-- Vendor JS Files -->
  <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="assets/vendor/chart.js/chart.umd.js"></script>
  <script src="assets/vendor/echarts/echarts.min.js"></script>
  <script src="assets/vendor/quill/quill.min.js"></script>
  <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
  <script src="assets/vendor/tinymce/tinymce.min.js"></script>
  <script src="assets/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>
<script>
document.getElementById("select1").addEventListener("change", function() {
  var select1 = document.getElementById("select1");
  var select2 = document.getElementById("select2");

  if (select1.value === "1") {
    select2.disabled = true; // Désactiver le deuxième select
  } else {
    select2.disabled = false; // Activer le deuxième select
  }
});
</script>
</body>

</html>
