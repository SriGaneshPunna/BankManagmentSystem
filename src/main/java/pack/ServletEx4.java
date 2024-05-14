package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletEx4
 */
@WebServlet("/ServletEx4")
public class ServletEx4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEx4() {
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
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		pw.println("drver loded");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankprj","root","gan123");
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM bank WHERE id=? AND password=?");
        ps.setInt(1, id);
        ps.setString(2, pass);
        
        ResultSet rs = ps.executeQuery();
       while(rs.next())
       {
    	   pw.println("Customer id  :   "+rs.getInt(1)+"\n"+"customer name:  "+ rs.getString(2)+"\n"+"Customer password: *******"+"\n"+"account balance"+rs.getInt(4));
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
