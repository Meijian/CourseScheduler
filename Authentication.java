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
 * Servlet implementation class Authentication
 */

public class Authentication extends HttpServlet 
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
			Connection connection = database.Get_Connection();
			ArrayList<MessageObjects> messagesData = null;
			
			if(request.getParameter("register")!=null && request.getParameter("register").equals("register"))
			{
				boolean result = projectManager.Registration(connection,request,response);
				if(result)
				{	
					System.out.println("In register");
					//response.sendRedirect("Calendar.html"); n ye wali commen ki
					out.println("{\"Messages\":\"Success\"}");
				}
				else
				{
					//response.sendRedirect("Login.html");
					out.println("{\"Messages\":\"Failure\"}");
				}
			}
			else
			{
				messagesData = projectManager.Authentication(connection, request, response);
				Gson gson = new Gson();
				String messages = gson.toJson(messagesData);
				out.println("{\"Messages\":"+messages+"}");	
			}
			
			
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
