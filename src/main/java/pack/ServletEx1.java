package pack;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletEx1
 */
@WebServlet("/ServletEx1")
public class ServletEx1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEx1() {
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
		String name=request.getParameter("name");
		String pass=request.getParameter("pass");
		int amt=Integer.parseInt(request.getParameter("amt"));
		Class.forName("com.mysql.cj.jdbc.Driver");
		pw.println("drver loded");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankprj","root","gan123");
		Statement st = conn.createStatement();
		st.execute("insert into bank values("+id+",'"+name+"','"+pass+"',"+amt+");");
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
