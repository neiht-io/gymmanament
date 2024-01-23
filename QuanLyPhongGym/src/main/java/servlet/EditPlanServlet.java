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
 * Servlet implementation class EditPlanServlet
 */
@WebServlet("/EditPlanServlet")
public class EditPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPlanServlet() {
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
			String pname = request.getParameter("pname");
			String amount = request.getParameter("amount");
			String duration = request.getParameter("duration");

			try {
				
				Connection con = ConnectionProvider.getCon();
				PreparedStatement ps = con.prepareStatement("update plan set pname=?,amount=?,duration=? where id=?");
				ps.setString(1, pname);
				ps.setString(2, amount);
				ps.setString(3, duration);
				ps.setInt(4, id);
				ps.executeUpdate();
				response.sendRedirect("./admin/editPlan.jsp?msg=valid");
			} catch (Exception e) {
				response.sendRedirect("./admin/editPlan.jsp?msg=invalid");
			}
	}

}
