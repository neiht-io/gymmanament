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
 * Servlet implementation class EditEquipmentServlet
 */
@WebServlet("/EditEquipmentServlet")
public class EditEquipmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEquipmentServlet() {
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
			String ename = request.getParameter("ename");
			String price = request.getParameter("price");
			String unit = request.getParameter("unit");

			try {
				
				Connection con = ConnectionProvider.getCon();
				PreparedStatement ps = con.prepareStatement("update equipment set ename=?,price=?,unit=? where id=?");
				ps.setString(1, ename);
				ps.setString(2, price);
				ps.setString(3, unit);
				ps.setInt(4, id);
				ps.executeUpdate();
				response.sendRedirect("./admin/editEquipment.jsp?msg=valid");
			} catch (Exception e) {
				response.sendRedirect("./admin/editEquipment.jsp?msg=invalid");
			}
	}

}
