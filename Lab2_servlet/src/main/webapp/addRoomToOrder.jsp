<%@ page import="com.booking.koval.entities.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.booking.koval.entities.Room" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Making an offer</title>
    </head>
    <body>
        <a style = "float: right; display: inline; margin: 0 10px; text-decoration: none; padding: 10px; background-color: #ffad33; broom-radius: 3px; color: black;" href = "/allOrders">Back</a>
        <div style="width: 200; margin: 0 auto; text-align: center;">
            <h1 style="text-align: center; margin: 50px auto">Choose room to offer</h1>
            <%
            Order order = (Order) request.getAttribute("order");
            out.println("<p><b>Ordered room capacity: </b> " + order.getCapacity() + "</p>" +
                        "<p><b>Ordered room class: </b> " + order.getClass_() + "</p><br>");
            %>
            <%long order_id = Long.valueOf(request.getParameter("order_id"));%>
            <form style="text-align: left;" action="/addRoomToOrder" method="POST">
                <input type="hidden" name="order_id" value="<%=order_id%>"/>
                Room id: <label><br>
                <input type="number" min="1" step="1" style="width: 200;" name="room_id"/>
                </label> <br><br>
              <input type="submit" class="form-submit-button" value="Offer"/>
            </form>
        </div>
        <div>
            <h2 style = "display: inline-block; text-align: center; margin: 20px 10px">Existing rooms:</h2><br>
            <%
            List<Room> rooms = (List<Room>) request.getAttribute("rooms");
            if (rooms == null) {
                out.println("<p>No rooms created.</p>");
                return;
            }
            for (Room room : rooms) {
                out.println("<div style =\"width: 240px; background-color: #fffd8f; text-align: left; display: inline-block; padding: 10px 20px; margin: 10px\">");
                out.println("<p><b>Room id: </b> " + room.getRoom_id() + "</p>" +
                            "<p><b>Room's price per night: </b> " + room.getPrice_per_night() + "</p>" +
                            "<p><b>Room's capacity: </b> " + room.getCapacity() + "</p>" +
                            "<p><b>Room's class: </b> " + room.getClass_() + "</p><br>");
                out.println("</div>");
            }%>
        </div>
    </body>
</html>