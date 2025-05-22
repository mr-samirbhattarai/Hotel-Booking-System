<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>Contact Us | Hotel RockStar</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"> 
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/contactUs.css" />

</head>
<body>
    <c:set var="activePage" value="contactUs" scope="request" />   
    <jsp:include page="/customer/header.jsp" />
    

    <div class="contact-Us">

        <h2>Contact Us</h2>
        <p>
            At Hotel RockStar, we’re committed to making your stay unforgettable. Reach out with any inquiries, feedback, or support requests, and our dedicated team will respond promptly.
        </p>

        <div class="contact-grid">
            <!-- Contact Form -->
            <div class="contact-form">
                <h3>Send Us a Message</h3>
                <form id="contact-form">
                    <div class="form-group">
                        <label for="name" class="label-for-form">Name</label>
                        <input type="text" id="name" class="form-control" placeholder="Your name" required>
                    </div>
                    <div class="form-group">
                        <label for="email" class="label-for-form">Email</label>
                        <input type="email" id="email" class="form-control" placeholder="Your email" required>
                    </div>
                    <div class="form-group">

                        <label for="message" class="label-for-form">Message</label>

                        <textarea id="message" class="form-control" placeholder="Your message" required></textarea>
                    </div>
                    <button type="submit" class="btn-login">Send Message</button>
                </form>
            </div>

            <!-- Contact Info -->
            <div class="contact-info">
                <h3>Get in Touch</h3>
                <div>
                    <h4>Find Us</h4>
                    <div class="map-placeholder">
                        <!-- Google Maps iframe for Informatics College Pokhara with adjusted zoom -->
                        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1758.125504439698!2d83.9984413150625!3d28.21489058259079!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x39959434ad2a5bf9%3A0xf4e7f9c749f63113!2sInformatics%20College%20Pokhara!5e0!3m2!1sen!2sus!4v1731501234567!5m2!1sen!2sus" width="100%" height="300" style="border:0;" aria-label="Map of Informatics College Pokhara"></iframe>
                    </div>
                </div>
            </div>
        </div>
    </div>

  <jsp:include page="/customer/footer.jsp" />


    <script>
        document.getElementById('contact-form').addEventListener('submit', (e) => {
            e.preventDefault();

            alert('Thank you for your feedback!.');

            e.target.reset();
        });
    </script>
</body>
</html>