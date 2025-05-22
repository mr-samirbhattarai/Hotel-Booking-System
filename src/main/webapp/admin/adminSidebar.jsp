
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminSidebar.css" />
  
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
          <a class="nav-link ${activePage == 'dashboard' ? 'active' : ''}"
   				href="${pageContext.request.contextPath}/dashboard">
            <span class="material-symbols-rounded">dashboard</span>
            <span class="nav-label">Dashboard</span>
          </a>
        </li>

        <li class="nav-item">
			<a href="${pageContext.request.contextPath}/roomDetails?page=manageRooms" class="nav-link ${activePage == 'manageRooms' ? 'active' : ''}">
            <span class="material-symbols-rounded">settings</span>
            <span class="nav-label">Manage Rooms</span>
          </a>
        </li>

        <li class="nav-item">
			<a href="${pageContext.request.contextPath}/roomDetails?page=roomStatus" class="nav-link ${activePage == 'roomStatus' ? 'active' : ''}">
            <span class="material-symbols-rounded">hotel</span>
            <span class="nav-label">Room Status</span>
          </a>
        </li>

        <li class="nav-item">
		    <a href="${pageContext.request.contextPath}/CheckInController" class="nav-link ${activePage == 'checkIn' ? 'active' : ''}">
		        <span class="material-symbols-rounded">notifications</span>
		        <span class="nav-label">Check-In</span>
		    </a>
		</li>

        <li class="nav-item">
		    <a href="${pageContext.request.contextPath}/GetBookingByStatus" class="nav-link ${activePage == 'bookings' ? 'active' : ''}">
		        <span class="material-symbols-rounded">notifications</span>
		        <span class="nav-label">Bookings</span>
		    </a>
		</li>

        <li class="nav-item"><a
			href="${pageContext.request.contextPath}/ViewCustomerController"
			class="nav-link ${activePage == 'viewCustomer' ? 'active' : ''}">
				<span class="material-symbols-rounded">groups</span> <span
				class="nav-label">Users Details</span>
		</a></li>
      </ul>

      <div class="nav-bottom">
	    <ul class="nav-list">
	      <li class="nav-item">
	        <a href="${pageContext.request.contextPath}/LogoutController" class="nav-link">
	            <span class="material-symbols-rounded">logout</span>
	            <span class="nav-label">Sign Out</span>
	         </a>
	      </li>
	    </ul>
  </div>
    </nav>
  </aside>
</body>
</html>
