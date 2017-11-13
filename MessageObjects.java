package dto;

public class MessageObjects 
{
	
	private String dd_value;
	private String dd_id;
	private String days_of_week;
	private int time = 0;
	private int Crn = 0;
	private String prerequisite_id;
	private String course_id;
	private String Instructor_name;
	private int Capacity = 0;
	private int Enrollment = 0;
	private String section;
	private String room;
	private String courses_taken;
	private String username;
	private String password;
	private String student_id;
	private String fName;
	private String lName;
	private String email;


	public String getCourses_taken() 
	{
		return courses_taken;
	}
	public void setCourses_taken(String courses_taken) 
	{
		this.courses_taken = courses_taken;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public int getCapacity() {
		return Capacity;
	}
	public void setCapacity(int capacity) {
		Capacity = capacity;
	}
	public int getEnrollment() {
		return Enrollment;
	}
	public void setEnrollment(int enrollment) {
		Enrollment = enrollment;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getInstructor_name() {
		return Instructor_name;
	}
	public void setInstructor_name(String instructor_name) {
		Instructor_name = instructor_name;
	}
	public String getPrerequisite_id() 
	{
		return prerequisite_id;
	}
	public int getCrn() {
		return Crn;
	}
	public void setCrn(int crn) {
		Crn = crn;
	}
	public String getDays_of_week() {
		return days_of_week;
	}
	public void setDays_of_week(String days_of_week) {
		this.days_of_week = days_of_week;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getdd_id() 
	{
		return dd_id;
	}
	public void setdd_id(String dd_id) 
	{
		this.dd_id = dd_id;
	}
	public String getdd_value() 
	{
		return dd_value;
	}
	public void setdd_value(String dd_value) 
	{
		this.dd_value = dd_value;
	}
	public void setPrerequisite_id(String prerequisite_id2) 
	{
		this.prerequisite_id = prerequisite_id2;
	}
	public String getUsername() 
	{
		return username;
	}
	public void setUsername(String username) 
	{
		this.username = username;
	}
	public String getPassword() 
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
		
}
	

