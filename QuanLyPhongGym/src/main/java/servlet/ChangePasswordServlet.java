package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gym.ConnectionProvider;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
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
		HttpSession session = request.getSession();
		 String email = session.getAttribute("email").toString();
		 String oldPassword = request.getParameter("oldPassword");
		 String newPassword = request.getParameter("newPassword");
		 String confirmPassword = request.getParameter("confirmPassword");
		 
		 if(!confirmPassword.equals(newPassword))
			 response.sendRedirect("./admin/changePassword.jsp?msg=notMatch");
		 else
		 {
			 int check=0;
			 try
			 {
				 Connection con = ConnectionProvider.getCon();
				 String sql = "select * from admin where email='"+email+"' and password='"+oldPassword+"'";
				 PreparedStatement ps = con.prepareStatement(sql);
				 ResultSet rs = ps.executeQuery();
				 while(rs.next())
				 {
					 check=1;
					 ps.executeUpdate("update admin set password='"+newPassword+"' where email='"+email+"'");
					 response.sendRedirect("./admin/changePassword.jsp?msg=done");
				 }
				 if(check==0)
					 response.sendRedirect("./admin/changePassword.jsp?msg=wrong");
			 }
			 catch(Exception e)
			 {
				 System.out.println(e);
			 }
		 }
	}

}
