<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminSidebar.css">
  
  
  </head>
<body>
  <aside class="sidebar">
    <div class="panel_logo">
      <i class="fa-solid fa-a"></i>
      <a href="#" class="panel_type">Admin Panel</a>
    </div>
    <div class="sidebar_halfline"></div>
    
    <nav class="sidebar-nav">
      <ul class="nav-list">
        <li class="nav-item">
          <a href="./dashboard.jsp" class="nav-link">
            <span class="material-symbols-rounded">dashboard</span>
            <span class="nav-label">Dashboard</span>
          </a>
        </li>

        <li class="nav-item">
          <a href="./manage_rooms.jsp" class="nav-link">
            <span class="material-symbols-rounded">settings</span>
            <span class="nav-label">Manage Rooms</span>
          </a>
        </li>

        <li class="nav-item">
          <a href="./room_status.jsp" class="nav-link">
            <span class="material-symbols-rounded">hotel</span>
            <span class="nav-label">Room Status</span>
          </a>
        </li>

        <li class="nav-item">
          <a href="./booking_request.jsp" class="nav-link">
            <span class="material-symbols-rounded">notifications</span>
            <span class="nav-label">Booking Request</span>
          </a>
        </li>

        <li class="nav-item">
          <a href="#" class="nav-link">
            <span class="material-symbols-rounded">bookmark_added</span>
            <span class="nav-label">Past Bookings</span>
          </a>
        </li>

        <li class="nav-item">
          <a href="#" class="nav-link">
            <span class="material-symbols-rounded">groups</span>
            <span class="nav-label">See Users</span>
          </a>
        </li>
      </ul>

      <ul class="nav-list">
        <li class="nav-item">
          <a href="#" class="nav-link">
            <span class="material-symbols-rounded">help</span>
            <span class="nav-label">Manage Staff</span>
          </a>
        </li>
        <li class="nav-item">
          <a href="#" class="nav-link">
            <span class="material-symbols-rounded">logout</span>
            <span class="nav-label">Sign Out</span>
          </a>
        </li>
      </ul>
    </nav>
  </aside>
</body>



<body>

</body>
</html>