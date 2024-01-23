package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gym.ConnectionProvider;

/**
 * Servlet implementation class AddEnquiryServlet
 */
@WebServlet("/AddEnquiryServlet")
public class AddEnquiryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEnquiryServlet() {
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

        String name = request.getParameter("name");
        String mobileNumber = request.getParameter("mobileNumber");
        String email = request.getParameter("email");
        String age = request.getParameter("age");
        String gender = request.getParameter("gender");

        try {
            Connection con = ConnectionProvider.getCon();
            String sql = "insert into enquiry(name, mobileNumber, email, age, gender) values(?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, mobileNumber);
            ps.setString(3, email);
            ps.setString(4, age);
            ps.setString(5, gender);
            ps.executeUpdate();

            response.sendRedirect("./admin/addEnquiry.jsp?msg=valid");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("./admin/addEnquiry.jsp?msg=invalid");
        }
    
	}

}
