/**
 * 
 */

$(document).ready(function()
{
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
	
	
	
	$("#register").hide();
	
	
	return false;
});

function checkLogin()
{
	var username = "username="+$("#username").val();;
	$.ajax
	({
		type: "POST",
		url: "Authentication",
		data: username,
		dataType:"json",
		cache: false,
		success: function(data)
		{
			if(data.Messages.length)
			{
				if(data.Messages[0].password == $("#password").val())
				{
				
					sessionStorage.user = data.Messages[0].fName;
					window.location.href='Calendar.html?'+username;
				}
				else	
				{
					alert("Incorrect Password");
				}
			}
			else
			{
				if($("#username").val()== "" ||  $("#password").val()== "")
				{
					alert("Both fields are required");
				}
				else
				{
					alert("authentication failed!");
				}
				
			}
		}
	});
}

function newUser()
{
	$("#login").hide();
	$("#register").show();	

}
function register()
{
	var fname = "firstname="+$("#firstname").val();
	var lname = "lastname="+$("#lastname").val();
	var email = "emailid="+$("#emailid").val();
	var password = "password="+$("#newpassword").val();
	var page ="register=register";
	var items = [];
	$('#multiple option:selected').each(function(){ items.push($(this).text()); });
	var result = items.join(', ');
	var dataString = fname+"&"+lname+"&"+email+"&"+password+"&"+page;
	$.ajax
	({
		type: "POST",
		url: "Authentication",
		data: dataString,
		dataType:"json",
		cache: false,
		success: function(data)
		{
			if(data.Messages === "Success")
			{
				alert("Registration Successful");
				window.location.href='Calendar.html?'+$("#emailid").val();
			}else{
				window.location.href='Login.html';
			}			
		}
	});
}