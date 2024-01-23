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
 * Servlet implementation class AddEnquipmentServlet
 */
@WebServlet("/AddEnquipmentServlet")
public class AddEnquipmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEnquipmentServlet() {
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
			String ename = request.getParameter("ename");
			String price = request.getParameter("price");
			String unit = request.getParameter("unit");
			String purchasedate = request.getParameter("purchasedate");
			String description = request.getParameter("description");

			try {

				Connection con = ConnectionProvider.getCon();
				String sql = "insert into equipment(ename,price,unit,purchasedate,description) values(?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(sql);

				ps.setString(1, ename);
				ps.setString(2, price);
				ps.setString(3, unit);
				ps.setString(4, purchasedate);
				ps.setString(5, description);
				ps.executeUpdate();
				response.sendRedirect("./admin/addEquipment.jsp?msg=valid");
				
			} catch (Exception e) {
				System.out.println(e);
				response.sendRedirect("./admin/addEquipment.jsp?msg=invalid");
			}
	}

}
