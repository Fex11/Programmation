<%-- 
    Document   : Annonce_RH
    Created on : 29 sept. 2023, 08:58:44
    Author     : SABI
--%>

<%@page import="model.Annonce"%>
<%@page import="model.Langue"%>
<%@page import="model.Degre"%>
<%@page import="model.Diplome"%>
<%@page import="model.Nationalite"%>
<%@page import="model.Poste"%>
<%@page import="java.util.Vector"%>
<%
    Annonce s=(Annonce)request.getAttribute("annonce");
    Vector<Nationalite> nationalite=(Vector<Nationalite>) request.getAttribute("nationalite");
    Vector<Diplome> diplome=(Vector<Diplome>) request.getAttribute("diplome");
    Vector<Degre> degre=(Vector<Degre>) request.getAttribute("degre");
    Vector<Langue> langue=(Vector<Langue>) request.getAttribute("langue");
    int ida = s.getIdAnnonce();
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
      <a href="index.html" class="logo d-flex align-items-center">
        <img src="assets/img/logo.png" alt="">
        <span class="d-none d-lg-block">NiceAdmin</span>
      </a>
      <i class="bi bi-list toggle-sidebar-btn"></i>
    </div><!-- End Logo -->

    <div class="search-bar">
      <form class="search-form d-flex align-items-center" method="POST" action="#">
        <input type="text" name="query" placeholder="Search" title="Enter search keyword">
        <button type="submit" title="Search"><i class="bi bi-search"></i></button>
      </form>
    </div><!-- End Search Bar -->

    <nav class="header-nav ms-auto">
      <ul class="d-flex align-items-center">

        <li class="nav-item dropdown pe-3">

          <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
            <img src="assets/img/profile-img.jpg" alt="Profile" class="rounded-circle">
            <span class="d-none d-md-block dropdown-toggle ps-2"><%= session.getAttribute("username")%></span>
          </a><!-- End Profile Iamge Icon -->

          <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
            <li class="dropdown-header">
              <h6><%= session.getAttribute("username")%></h6>
<!--              <span>Web Designer</span>-->
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
        <a class="nav-link " href="Inint_listeAnnonce">
          <i class="bi bi-grid"></i>
          <span>Dashboard</span>
        </a>
      </li>
    </ul>

  </aside><!-- End Sidebar-->

  <main id="main" class="main">
       <div class="pagetitle">
        <h1>REGISTER CV</h1>
        <nav>
          <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="indexUtilisateur.jsp">Home</a></li>
            <li class="breadcrumb-item active">Register cv</li>
          </ol>
        </nav>
      </div>
    <!--////-->
       <h6></h6>
    <div class="container">
        <div class="card p-5">
            <div class="card-body">
                 <h5 class="card-title">POSTULER POUR LE POSTE DE : <%= s.getPoste().getNomPoste() %></h5>
                 <form method="GET" action="Save_CV">
                     <input type="hidden" name="ida" value=<%= ida %> >
                     <input type="hidden" name="idClient" value=<%=session.getAttribute("id")%> >

                     
                     <div class="row">
                       <div class="input-group mb-3 col-lg-6">
                            <label class="input-group-text" for="nom">Nom</label>
                            <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" name="nom">
                        </div>
                    </div>
                     <div class="row">
                       <div class="input-group mb-3 col-lg-6">
                            <label class="input-group-text" for="perenom">Prénom</label>
                            <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" name="prenom">
                        </div>
                    </div>
                    <div class="row">
                       <div class="input-group mb-3 col-lg-6">
                            <label class="input-group-text" for="dtn">Date de naissance</label>
                            <input type="date" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" name="dtn">
                        </div>
                    </div>
                    <div class="row" id="dd">
                        <div class=" col-lg-6">
                            <div class="input-group mb-3 ">
                                <label class="input-group-text" for="diplome">Diplome</label>
                                  <select class="form-select" id="diplome" name="diplome">
                                      <option value="">Choisir....</option>
                                    <% for(int i=0;i<diplome.size();i++) { %>
                                        <option value=<%= diplome.get(i).getIdDiplome() %>><%= diplome.get(i).getDiplome() %></option>
                                    <% } %>
                                  </select>
                           </div>
                        </div>
                        <div class=" col-lg-6">
                            <div class="input-group mb-3 ">
                                <label class="input-group-text" for="degre">Degree</label>
                                <select class="form-select" id="degre" name="degre">
                                    <% for(int i=0;i<degre.size();i++) { %>
                                        <option value=<%= degre.get(i).getIdDegre() %>><%= degre.get(i).getDegre() %></option>
                                    <% } %>
                                </select>
                           </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-group mb-3">
                            <div class="col-lg-2"><button type="button" class="btn btn-outline-success " id="plus">PLUS</button></div>
                            <div class="col-lg-2"><button type="button" class="btn btn-outline-danger " id="moin">MOIN</button></div>
                        </div>
                    </div>
                   
                    <div class="row ">
                        <div class="input-group mb-3  col-lg-12">
                            <label class="input-group-text" for="exp">Experience</label>
                            <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" name="exp">
                       </div>
                    </div>
                    <div class="row ">
                         <div class="input-group mb-3 col-lg-12 ">
                            <label class="input-group-text" for="sexe">Sexe</label>
                            <select class="form-select" id="inputGroupSelect01" name="sexe">
                              <option value="Homme">Homme</option>
                              <option value="Femme">Femme</option>
                            </select>
                        </div>
                    </div>
                    <div class="row ">
                        <div class="input-group mb-3 col-lg-12 ">
                            <label class="input-group-text" for="nat">Nationalite</label>
                            <select class="form-select" id="inputGroupSelect01" name="nat">
                                <% for(int i=0;i<nationalite.size();i++) { %>
                                    <option value=<%= nationalite.get(i).getIdNationalite() %>><%= nationalite.get(i).getNationalite() %></option>
                                <% } %>
                            </select>
                       </div>
                    </div>
                    <div class="row">
                        <div  class=" input-group mb-3">
                             <label class="input-group-text" for="iG">Niveau Linguistique</label>
                        </div>
                    </div>
                    <% for(int i=0;i<langue.size();i++) { %>
                        <div class="row">
                            <div  class=" input mb-3 col-lg-2">
                                 <label class="input-group-text" for="langue"><%= langue.get(i).getLangue() %></label>
                            </div>
                            <div class="form-check col-lg-5  ">
                                    <input class="form-check-input mb-5" type="checkbox" id="langue" name="langue" value=<%= langue.get(i).getIdLangue() %>ye>
                                    <label class="form-check-label" for="flexCheckIndeterminate">
                                      Ecrit
                                    </label>
                            </div>
                            <div class="form-check col-lg-5 ">
                                <input class="form-check-input mb-3" type="checkbox" id="langue" name="langue" value=<%= langue.get(i).getIdLangue() %>yp>
                                <label class="form-check-label" for="flexCheckIndeterminate">
                                  Parler
                                </label>
                            </div>
                        </div>
                    <% } %>
                    <div class="row ">
                        <div class="input-group mb-3 col-lg-12 ">
                            <label class="input-group-text" for="situation">Situation Matrimoniale</label>
                            <select class="form-select" id="situation" name="sm">
                              <option value="Marié">Marié</option>
                              <option value="Célibataire">Célibataire</option>
                            </select>
                       </div>
                    </div>
                    <div class="row ">
                        <div class="input-group mb-3  col-lg-12">
                            <label class="input-group-text" for="email">Email</label>
                            <input type="email" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" name="email">
                        </div>
                    </div>
                    <div class="row ">
                        <div class="input-group mb-3  col-lg-12">
                            <label class="input-group-text" for="num">Numero</label>
                            <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" name="num">
                        </div>
                    </div>
                    <div class="row ">
                        <div class="input-group mb-3  col-lg-12">
                            <label class="input-group-text" for="adresse">Adresse</label>
                            <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" name="adresse">
                        </div>
                    </div>
                    <div class="row">
                        <div class=" col-lg-12">
                            <button type="submit" class="btn btn-primary ">ENVOYER CV</button>
                        </div>
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
    document.getElementById("diplome").addEventListener("change", function() {
        var diplome = document.getElementById("diplome");
        var degre = document.getElementById("degre");
        
        if (diplome.value === "1" || diplome.value === "2"){
            degre.disabled = true;
        }else{
            degre.disabled = false;
        }

    });
        var dd = document.getElementById("dd");
        var btn1 =  document.getElementById("plus");
        var btn2 = document.getElementById("moin");
        var moov = ` <div class="row">
                        <div class=" col-lg-6">
                            <div class="input-group mb-3 ">
                                <label class="input-group-text" for="diplome">Diplome</label>
                                  <select class="form-select" id="diplome" name="diplome">
                                    <option value="">Choisir....</option>
                                    <% for(int i=0;i<diplome.size();i++) { %>
                                        <option value=<%= diplome.get(i).getIdDiplome() %>><%= diplome.get(i).getDiplome() %></option>
                                    <% } %>
                                  </select>
                           </div>
                        </div>
                        <div class=" col-lg-6">
                            <div class="input-group mb-3 ">
                                <label class="input-group-text" for="degre">Degree</label>
                                <select class="form-select" id="degre" name="degre">
                                    <% for(int i=0;i<degre.size();i++) { %>
                                        <option value=<%= degre.get(i).getIdDegre() %>><%= degre.get(i).getDegre() %></option>
                                    <% } %>
                                </select>
                           </div>
                        </div>
                    </div>`;

        btn1.addEventListener('click', () => {
            let po  = document.createElement("div");
            po.innerHTML = moov;
            dd.appendChild(po); 
        });
        btn2.addEventListener('click', () => {
          var remove = dd.lastElementChild;
          if (dd.children.length > 2 ) {
            dd.removeChild(remove);
          }
        });
  </script>
</body>

</html>
