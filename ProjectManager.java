package model;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Project;
import dto.MessageObjects;

public class ProjectManager {
	public ArrayList<MessageObjects> InsertMessage(Connection connection, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ArrayList<MessageObjects> messages = null;
		try {
			//message = request.getParameter("message");
			Project project= new Project();
			messages=project.InsertMessage(connection, request, response);

			
		} catch (Exception e) {
			throw e;
		}
		return messages;
	}
	
	public ArrayList<MessageObjects> GetDropDownValues(Connection connection, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ArrayList<MessageObjects> messages = null;
		try {
			
				Project project= new Project();
				messages=project.GetDropDownValues(connection, request, response);
	
		} catch (Exception e) {
			throw e;
		}
		return messages;
	}
	
	public boolean Registration(Connection connection, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		boolean result = false;
		try {
			
				Project project= new Project();
				result = project.Registration(connection, request, response);
	
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	public ArrayList<MessageObjects> Authentication(Connection connection,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		ArrayList<MessageObjects> messages1 = null;
		try 
		{
			Project project= new Project();
			messages1 =project.Authentication(connection, request, response);
		} 
		catch (Exception e)
		{
				throw e;
		}
		return messages1;
	}

}
