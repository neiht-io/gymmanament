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
import com.mysql.cj.xdevapi.Statement;

/**
 * Servlet implementation class DeleteEnquiryServlet
 */
@WebServlet("/DeleteEnquiryServlet")
public class DeleteEnquiryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteEnquiryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			Connection con = ConnectionProvider.getCon();
			String sql = "delete from enquiry where id='" + id + "'";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();
			response.sendRedirect("./admin/viewEnquiry.jsp?msg=deleted");
		} catch (Exception e) {
			System.out.println(e);
		}
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
