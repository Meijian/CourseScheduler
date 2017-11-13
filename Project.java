package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

import dto.MessageObjects;


public class Project 
{
	public ArrayList<MessageObjects> InsertMessage(Connection connection, HttpServletRequest request,
			HttpServletResponse response) throws Exception 
	{
		String id = null;
		ArrayList<MessageObjects> messageData = new ArrayList<MessageObjects>();
		try 
		{
			id = request.getParameter("id");
			PreparedStatement ps = connection
					.prepareStatement("SELECT Course_id,CRN,Time,Days_of_week,Capacity,Enrollment,Section,Room from class where Course_id=(?)");
			PreparedStatement ps1 = connection
					.prepareStatement("SELECT Prerequisite_id from prerequisite where Course_id=(?)");
			PreparedStatement ps2 = connection.prepareStatement("SELECT concat(Fname,' ',Lname) As Name FROM instructor where Instructor_id In(Select Instructor_id from class where Course_id = (?))");
			String query = "SELECT Course_id from course_taken where Student_id = '1'";
			Statement ps3 = (Statement) connection.createStatement();
			ps.setString(1,id);
			ps1.setString(1,id);
			ps2.setString(1,id);
			ResultSet rs = ps.executeQuery();
			ResultSet rs1 = ps1.executeQuery();
			ResultSet rs2 = ps2.executeQuery();
			ResultSet rs3 = ps3.executeQuery(query);
			String prerequisite_id = "";
			String name ="";
			String courses_taken = "";
			while(rs3.next())
			{
				if(courses_taken.equals(""))
				{
					courses_taken =  "" + rs3.getString("Course_id");
				}
				else
				{
					courses_taken = courses_taken + ", "+ rs3.getString("Course_id");
				}
				
			}
			
			if(rs2.next())
			{
				name = rs2.getString("Name");
			}
			while(rs1.next())
			{
				if( prerequisite_id == "")
				{
					prerequisite_id =  "" + rs1.getInt("Prerequisite_id");
				}
				else{
					prerequisite_id = prerequisite_id + ", "+ rs1.getInt("Prerequisite_id");
				}
				
			}
			while(rs.next())
			{
				MessageObjects messageObject = new MessageObjects();
				messageObject.setDays_of_week(rs.getString("Days_of_week"));
				messageObject.setCrn(rs.getInt("CRN"));
				messageObject.setTime(rs.getInt("Time"));
				messageObject.setPrerequisite_id(prerequisite_id);
				messageObject.setCourse_id(rs.getString("Course_id"));
				messageObject.setCapacity(rs.getInt("Capacity"));
				messageObject.setEnrollment(rs.getInt("Enrollment"));
				messageObject.setInstructor_name(name);
				messageObject.setSection(rs.getString("Section"));
				messageObject.setRoom(rs.getString("Room"));
				messageObject.setCourses_taken(courses_taken);
				messageData.add(messageObject);
			}
			return messageData;

		} catch (Exception e) {
			throw e;
		}
}
	
	public ArrayList<MessageObjects> GetDropDownValues(Connection connection,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		ArrayList<MessageObjects> messageData = new ArrayList<MessageObjects>();
		try
		{
			
			PreparedStatement ps = connection.prepareStatement("SELECT Id,Name from course");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				MessageObjects messageObject = new MessageObjects();
				messageObject.setdd_id(rs.getString("Id"));
				messageObject.setdd_value(rs.getString("Name"));
				messageData.add(messageObject);
			}
			return messageData;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	public ArrayList<MessageObjects> Authentication(Connection connection,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
		String username=null;
		MessageObjects messageObject1 = null;
		ArrayList<MessageObjects> messageData1 = new ArrayList<MessageObjects>();
		try
		{
			username = request.getParameter("username");
			String query = "Select * from student where Email= '"+ username +"'";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			messageObject1= new MessageObjects();
			while(rs.next())
			{
				messageObject1.setPassword(rs.getString("Password"));
				messageObject1.setfName(rs.getString("Fname"));
				messageObject1.setlName(rs.getString("Lname"));
				messageObject1.setEmail(rs.getString("Email"));
				messageData1.add(messageObject1);
			}
			return messageData1;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public boolean Registration(Connection connection, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		boolean result = false;
		String fname = request.getParameter("firstname");
        String lname= request.getParameter("lastname");
        String email = request.getParameter("emailid");
        String pass = request.getParameter("password");
		try 
		{
			
			PreparedStatement ps= connection.prepareStatement("insert into student values(?,?,?,?)");
			ps.setString(1, fname);
	        ps.setString(2, lname);
	        ps.setString(3, pass);
	        ps.setString(4, email);
	        int i=ps.executeUpdate();
	        if(i>0)
	        {
	            result = true;
	        }
	        else
	        {
	        	result=false;
	        }
					
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	
	
	
}
