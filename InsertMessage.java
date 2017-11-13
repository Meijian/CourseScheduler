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

import model.ProjectManager;
import dao.Database;
import dto.MessageObjects;

/**
 * Servlet implementation class InsertMessage
 */
public class InsertMessage extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try
		{
			Database database= new Database();
			ProjectManager projectManager= new ProjectManager();
			ArrayList<MessageObjects> messagesData = null;
			Connection connection = database.Get_Connection();
			messagesData= projectManager.InsertMessage(connection, request, response);
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
}
