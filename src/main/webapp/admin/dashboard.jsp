<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Dashboard | Hotel RockStar</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded" />    
      <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
</head>
<body>
<c:set var="activePage" value="dashboard" scope="request" />
   	<jsp:include page="/admin/adminSidebar.jsp" />
    <section class="dashboard">
      <div class="dash-content">
        <div class="title">
          <span class="material-symbols-rounded">dashboard</span>
          <span class="text">Dashboard</span>
        </div>

        <div class="container">
          <div class="row g-4">
            <div class="col-md-4">
              <div class="card-box box1">
                <span class="material-symbols-rounded">groups</span>
                <div class="text">Total Users</div>
                <div class="number">11</div>
              </div>
            </div>
            <div class="col-md-4">
              <div class="card-box box2">
                <span class="material-symbols-rounded">notifications</span>
                <div class="text">Booking Pending</div>
                <div class="number">5</div>
              </div>
            </div>
            <div class="col-md-4">
              <div class="card-box box3">
                <span class="material-symbols-rounded">door_open</span>
                <div class="text">Available Rooms</div>
                <div class="number">4</div>
              </div>
            </div>
            <div class="col-md-4">
              <div class="card-box box4">
                <span class="material-symbols-rounded">hotel</span>
                <div class="text">Occupied Rooms</div>
                <div class="number">6</div>
              </div>
            </div>
            <div class="col-md-4">
              <div class="card-box box5">
                <span class="material-symbols-rounded">rate_review</span>
                <div class="text">Customer's Review</div>
                <div class="number">2</div>
              </div>
            </div>
            <div class="col-md-4">
              <div class="card-box box6">
                <span class="material-symbols-rounded">bookmark_added</span>
                <div class="text">Total Booking</div>
                <div class="number">2</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
</body>
</html>