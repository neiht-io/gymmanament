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
 * Servlet implementation class EditEnquiryServlet
 */
@WebServlet("/EditEnquiryServlet")
public class EditEnquiryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEnquiryServlet() {
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
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String mobileNumber = request.getParameter("mobileNumber");
			String email = request.getParameter("email");
			String age = request.getParameter("age");

			try {
				
				Connection con = ConnectionProvider.getCon();
				PreparedStatement ps = con
						.prepareStatement("update enquiry set name=?,mobileNumber=?,email=?,age=? where id=?");
				ps.setString(1, name);
				ps.setString(2, mobileNumber);
				ps.setString(3, email);
				ps.setString(4, age);
				ps.setInt(5, id);
				ps.executeUpdate();
				response.sendRedirect("./admin/editEnquiry.jsp?msg=valid");
			} catch (Exception e) {
				response.sendRedirect("./admin/editEnquiry.jsp?msg=invalid");
			}
	}

}
