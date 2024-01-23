package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gym.ConnectionProvider;

/**
 * Servlet implementation class AddMemberServlet
 */
@WebServlet("/AddMemberServlet")
public class AddMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String mobileNumber = request.getParameter("mobileNumber");
			String email = request.getParameter("email");
			String gender = request.getParameter("gender");
			String mplan = request.getParameter("mplan");
			String joindate = request.getParameter("joindate");
			String initamount = request.getParameter("initamount");

			try {

				Connection con = ConnectionProvider.getCon();
				String sql = "insert into member(name,mobileNumber,email,gender,mplan,joindate,initamount) values(?,?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(sql);

				ps.setString(1, name);
				ps.setString(2, mobileNumber);
				ps.setString(3, email);
				ps.setString(4, gender);
				ps.setString(5, mplan);
				ps.setString(6, joindate);
				ps.setString(7, initamount);
				ps.executeUpdate();
				response.sendRedirect("./admin/addMember.jsp?msg=valid");
			} catch (Exception e) {
				System.out.println(e);
				response.sendRedirect("./admin/addMember.jsp?msg=invalid");
			}
	}

}
