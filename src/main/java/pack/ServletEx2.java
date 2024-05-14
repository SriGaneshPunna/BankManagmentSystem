package pack;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletEx2
 */
@WebServlet("/ServletEx2")
public class ServletEx2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEx2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=response.getWriter();
		try {
		int id=Integer.parseInt(request.getParameter("id"));
		String pass=request.getParameter("pass");
		int amt=Integer.parseInt(request.getParameter("amt"));
		Class.forName("com.mysql.cj.jdbc.Driver");
		pw.println("drver loded");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankprj","root","gan123");
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM bank WHERE id=? AND password=?");
        ps.setInt(1, id);
        ps.setString(2, pass);
        
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            // Update amount using a prepared statement
            PreparedStatement updateStatement = conn.prepareStatement("UPDATE bank SET amount = amount + ? WHERE id = ?");
            updateStatement.setInt(1, amt);
            updateStatement.setInt(2, id);
            updateStatement.executeUpdate();
            pw.println("Amount updated successfully.");
        } else {
            pw.println("Invalid username or password.");
        }
		conn.close();
		}
		catch(ClassNotFoundException e)
		{
			pw.println(e);
		}
		catch(SQLException e1)
		{
			pw.println(e1);
		}
		
	}

}
