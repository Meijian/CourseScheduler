package controls;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import dao.Database;
import dto.MessageObjects;
import model.ProjectManager;


/**
 * Servelet implementation class GetMessages
 */

public class GetMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMessages() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try
		{
	
			Database database= new Database();
			ProjectManager projectManager= new ProjectManager();
			ArrayList<MessageObjects> messagesData = null;
			Connection connection = database.Get_Connection();
			messagesData = projectManager.GetDropDownValues(connection, request, response);
			Gson gson = new Gson();
			String messages = gson.toJson(messagesData);
			out.println("{\"Messages\":"+messages+"}");	
		}
		catch (Exception ex)
		{
		out.println("Error: " + ex.getMessage());
		}
		finally
		{
		out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

}
