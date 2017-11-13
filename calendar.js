$(document).ready(function()
{
	$('#loggedin').text("Welcome: " + sessionStorage.user)
    //This code will fill the drop down from the JSON Object data we get from database
	$.ajax
	({
	type: "GET",
	url: "GetMessages",
	dataType:"json",
	success: function(data)
	{
	if(data.Messages.length)
	{
	$.each(data.Messages, function(i,data)
	{
		var option = $('<option></option>').text(data.dd_value).val(data.dd_id);
		$('select').append(option);	
		
	});
	}
	else
	{
		$("#content").html("No Results");
	}
	}
	});
	//This code will execute when a new value is selected in the courses dropdown 
	$('#courses').change(function() 
	{
		var $dropdown = $(this);
		var dataString = "id="+$dropdown.val();
		$.ajax({
			type: "POST",
			url: "InsertMessage",
			data: dataString,
			dataType : "json",
			cache: false,
			success: function(data)
			{
				if(data.Messages.length > 0)
				{
					//Check for prerequisites
					if(data.Messages[0].prerequisite_id != "")
						{
							alert("Prerequisites for the course - " + data.Messages[0].prerequisite_id + ". If you have taken this course, ignore this warning, otherwise clear the course from Selected Courses.");
						}
					//Check for capacity of class
					if(data.Messages[0].Enrollment == data.Messages[0].Capacity)
						{
						 alert("Enrollment is full! Please select another course.");
						 return 0;
						}
					//check whether student has taken the course or not
					if(data.Messages[0].courses_taken.indexOf(data.Messages[0].course_id) > -1)
						{
						    alert("It seems you have already taken this course. Please clear the course from Selected Courses if this addition was unintentional.”");
						}
					
					pop_sel_course();

					for(var i = 0; i < data.Messages.length ; i ++ ){
						var dataObj = data.Messages[i];
						populateTable(dataObj);
					}
				}
			 }
			 });
		
	});
	//This function populates the values in corresponding cells of table
	function populateTable(obj)
	{
		
		var startTime = getStartTime(obj.time);
		var endTime = getEndTime(obj.time);
		var days = obj.days_of_week;
		var id = getTrId(startTime);
		var days = obj.days_of_week;
		var successful ="";
		var dd_id="";
		for(var i = 0 ; i < days.length ; i ++)
		{
			
			var day = days.charAt(i);
			var td = $('#'+id).find("."+day);
			dd_id = $('#courses').val();
			if($(td).hasClass("course"+dd_id))
			{
				
			}
			else
			{
				//Code to check if two schedules have conflict
				if($(td).hasClass("booked"))
				{
					alert("Schedule Conflict.Please select a different course");
					deleteLabel(dd_id);
					break;
				}
				else
				{
					$(td).html("<div>Course Id -"+obj.course_id +" "+ obj.section+"<br/>CRN - "+obj.Crn+"<br/>Instructor - "+obj.Instructor_name+ "<br/>Room - "+obj.room +"<br/>Time - "+add_colon(startTime)+" - "+add_colon(endTime) +"</div>");
					$(td).addClass("booked");
					$(td).addClass("course"+dd_id);
				}
			}
			
		}
	}
	
	//This function displays the selected courses on the screen
	function pop_sel_course(str)
	{
		var dd = $('#courses');
		if($("#labels").html().indexOf("showlabels"+$(dd).val()) > 0 ){
			
		}else{
			var sel_course = $('<div id="showlabels'+$(dd).val()+'">'+$('#courses option:selected').text()+' <a courseid="'+$(dd).val()+'" id="link'+$(dd).val()+'" href="#">&nbsp;&nbsp;&nbsp;Clear&nbsp;&nbsp;&nbsp;</a></div>');
			$('#labels').append(sel_course);
			var labelno = 
			$('#link'+$(dd).val()).click(function () {
				$(this).parent().remove();
				deleteLabel($(this).attr("courseid"));
			});
		}
		
	}
	
	//This function gets the row id from the database (JSON Object) 
	function getTrId(time)
	{ 
		var hr= time;
		if(time.length<4)
		  return "slot"+ hr.substring(0,1);
		else
		  return "slot"+ hr.substring(0,2);
	}
	
	function add_colon(time)
	{
		var hr= time;
		if(time.length<4)
			{
			  hr = hr.substring(0,1)+":"+hr.substring(1);
			  return  hr;
			}
		else
			{
			hr = hr.substring(0,2)+":"+hr.substring(2);
			  return  hr;
			}
		
	}
	
	
	//This functions gets the start time from the database(JSON Object) 
	function getStartTime(str){
		str = str.toString();
		if(str.length < 8){
			return str.substring(0,3);
		}else{
			return str.substring(0,4);			
		}
	}
	//This functions gets the end time from the database(JSON Object) 
	function getEndTime(str){
		str = str.toString();
		if(str.length < 8){
			return str.substring(3);	
		}else{
			return str.substring(4);	
		}
	}

	//This function deletes the courses when cleared
	function deleteLabel(id){;
		$('.course'+id).text("").removeClass("booked").removeClass("course"+id);
		$('#showlabels'+id).remove();
	}
	
	return false;
	});
function logOut()
{
	sessionStorage.user=" ";
	window.location.href='Login.html';
}
	
		