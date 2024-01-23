<%@ page import="com.gym.ConnectionProvider"%>
<%@ page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Enquiry</title>
<%@include file="/css/allCss.jsp"%>
</head>
<body>
	<%@ include file="navbar.jsp"%>
	<span style="color: red"><h4 style="margin-top: 10px">Chào
		</span>:
	<span style="color: blue"> Admin
		</h4>
	</span>
	<br>
	<%
		String msg = request.getParameter("msg");
		if ("deleted".equals(msg)) {
	%>
	<script>
		alert("Deleted Successfully..");
		window.location.assign("viewPlan.jsp");
	</script>
	<%
		}
	%>
	<div class="container mt-5">
		<h1 class="text-success text-center"></h1>


		<h4 class="text-center text-danger">Danh sách gói tập</h4>
		<br> <br>
		<table class="table table-bordered" id="example">
			<thead style="background-color: #d7ccc8">
				<tr>
					<th>S.No</th>
					<th>Plan Name</th>
					<th>Amount</th>
					<th>Duration(In Month)</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<%
					try {
						Connection con = ConnectionProvider.getCon();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("select * from plan");
						int i = 0;
						while (rs.next()) {
							i++;
				%>
				<td><%=i%></td>
				<td><%=rs.getString("pname")%></td>
				<td><%=rs.getString("amount")%></td>
				<td><%=rs.getString("duration")%></td>
				<td><a href="editPlan.jsp?id=<%=rs.getString("id")%>"
					class="btn btn-sm btn-primary"><i class="fas fa-edit"></i></a> <a
					href="../DeletePlanServlet?id=<%=rs.getString("id")%>"
					class="btn btn-sm btn-danger"><i class="fas fa-trash-alt"></i></a></td>
				</tr>
				<%
					}
					} catch (Exception e) {
						System.out.println(e);
					}
				%>
			</tbody>
		</table>

	</div>

</body>
</html>