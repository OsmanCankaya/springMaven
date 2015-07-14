	//to input mask:
	$(document).ready(function(){ 
		$('#telephone0').mask("(999) 999 99 99");
		$('#telephone1').mask("(999) 999 99 99");
		
	}); 
	// end of input mask
	
	//to using ajax

	function addUser(count){	   
		var name = $('#name' + count).val();
		var surname = $('#surname' + count).val();
		var telephone = $('#telephone' + count).val();
		var captcha = $('#captcha' + count).val();
 		var dataVar = 'name=' + name + '&surname=' + surname + '&telephone=' + telephone+'&captcha='+captcha;	
 		hideValidationFields(count);
		if(validateFields(count)){
			return;
		} 
		$('#loading').css('display' , 'block');	// to display loading image	
	    $.ajax({
	    	
	        url: 'add', 
	        dataType: 'json', 
	        data: dataVar , 
	        contentType: 'application/json',
	        mimeType: 'application/json',
	        success: function(response) {
	        	//alert("name: "+response.name);
	        	var	row = "<tr id = userTable" + response.id + ">"
				+ "<td>"+ response.name + "</td>"
				+ "<td>"+ response.surname + "</td>"
				+ "<td>"+ response.phone + "</td>"
				+ "<td>" + "<input type=\"submit\" class=\"btn btn-danger\" name=\"deleteButton\" value=\"Delete\" onclick=\"deleteConfirm('"
				+ response.id + "');\"/>"
				+ "<input type=\"submit\" class=\"btn btn-success\" name=\"updateButton\" value=\"Update\" onclick=\"updateConfirm('"
				+ response.id + "','" + response.name + "','"
				+ response.surname + "','" + response.phone
				+ "');\"/>" + "</td>" + "</tr>";
        	 $('#userTable').append(row);
        	
        	$('#name' + count).val("");
    		$('#surname' + count).val("");
    		$('#telephone' + count).val("");
    		$('#captcha' + count).val("");
    		$('#loading').css('display' , 'none'); // to hide loading image
    		$("#captchaDiv").load(location.href + " #captchaDiv");
    		
	        } ,
    	error: function(){
    	//	alert("failure");
    		$('#captcha' + count).val("");
    		$('#loading').css('display' , 'none'); // to hide loading image
    		$("#captchaDiv").load(location.href + " #captchaDiv");
    	}
	    
	    });	  
	}
	
	
	
	
	function hideValidationFields(count){
		  // alert("hide kısmı "+count);
		$('#nameEmptyValidation' + count).hide();
		$('#surnameEmptyValidation' + count).hide();
		$('#telephoneEmptyValidation' + count).hide(); 
		$('#captchaEmptyValidation' + count).hide();
	}
	
	function validateFields(count){
		var name = $('#name' + count).val();
		var surname = $('#surname' + count).val();
		var telephone = $('#telephone' + count).val();
		var captcha = $('#captcha' + count).val();
		var validationResult = false;
		if (name==null || name=="")
		  {
			$('#nameEmptyValidation' + count).text("This field is required ");
			$('#nameEmptyValidation' + count).show();
			validationResult = true;
		  }
		if (surname==null || surname=="")
		  {
			$('#surnameEmptyValidation' + count).text("This field is required ");
			$('#surnameEmptyValidation' + count).show();
			validationResult = true;
		  }
		if ((telephone==null || telephone.length!=15) && (telephone.length!=0) ) 
		  {
			$('#telephoneEmptyValidation' + count).text("This isn't phone number");
			$('#telephoneEmptyValidation' + count).show();
			validationResult = true;
		  }
		if(count == 0)
			if (captcha==null || captcha=="")
			{
				$('#captchaEmptyValidation' + count).text("This field is required ");
				$('#captchaEmptyValidation' + count).show();
				validationResult = true;
			}
		return validationResult;
	}
	
	function deleteUser(id){	
		console.log(id);
 		var dataId = 'id=' + id;
		console.log(dataId);
		$('#loading').css('display' , 'block');	// to display loading image	
	    $.ajax({	        
	        url: 'delete',
	        data: dataId,
	        success: function(response) {
	        	$('#userTable' + id).fadeIn(1000).fadeOut(200, function(){
	        		$('#userTable' + id).remove();})
	        	        $('#loading').css('display' , 'none'); // to hide loading image
	        } ,
	        error: function(){
	    		$('#loading').css('display' , 'none'); // to hide loading image
	    	}
	    
	    });	  
	}
	
	function deleteConfirm(id, count){
	//	alert("deleteConfirm");
		$("#deleteDialog").html("Are you sure you want to delete user?");
	    $("#deleteDialog").dialog({
	        resizable: false,
	        modal: true,
	        title: "Confirm Delete Operation",
	        height: 250,
	        width: 400,
	        buttons: {
	            	"Yes": function () {
	            		
	                $(this).dialog('close');
	           
	                deleteUser(id);
	              
	            },
	                "No": function () {
	                $(this).dialog('close');
	            }  
	        }
	   
	    });
	}
	
	function updateUser(id, count){	
		var name = $('#name' + count).val();
		var surname = $('#surname' +  count).val();
		var telephone = $('#telephone' + count).val();
 		var dataId = 'id=' + id + '&name=' + name + '&surname=' + surname + '&telephone=' + telephone;
 	//	alert("fvrvrf");
 		hideValidationFields(count);
		if(validateFields(count)){
		//	alert("validate hatası");
			return false;
		}   
		$('#loading').css('display' , 'block');	// to display loading image
	    $.ajax({	        
	        url: 'update',
	        data: dataId,
	        dataType: 'json' , 
	        contentType: 'application/json',
	        mimeType: 'application/json',
	        success: function(response) {  
	        	var	row = "<tr id = userTable" + response.id + ">"
				+ "<td>"+ response.name + "</td>"
				+ "<td>"+ response.surname + "</td>"
				+ "<td>"+ response.phone + "</td>"
				+ "<td>" + "<input type=\"submit\" class=\"btn btn-danger\" name=\"deleteButton\" value=\"Delete\" onclick=\"deleteConfirm('"
				+ response.id + "');\"/>"
				+ "<input type=\"submit\" class=\"btn btn-success\" name=\"updateButton\" value=\"Update\" onclick=\"updateConfirm('"
				+ response.id + "','" + response.name + "','"
				+ response.surname + "','" + response.phone
				+ "');\"/>" + "</td>" + "</tr>";
	        	$('#userTable' + id).replaceWith(row);
	        	
	        	$('#loading').css('display' , 'none'); // to hide loading image
	        //	alert("true dönüyor");
	        	return true;
	        } , 
	        error: function(){
	        	//alert("hatalı");
	    		$('#loading').css('display' , 'none'); // to hide loading image
	    		return false;
	    	}
	        
	    }
	   );	  
	    return true;
	}
	
	function updateConfirm(id,name,surname,telephone){
		 
		$('#name' + 1).val(name);
		$('#surname' + 1).val(surname);
		$('#telephone' + 1).val(telephone);
		hideValidationFields(1);
	    $("#updateDialog").dialog({
	        resizable: false,
	        modal: true,
	        title: "Update user information",
	        height: 350,
	        width: 400,
	        buttons: {
	           	    "Update user": function () {
	           	   //  alert(" updaten öncesi ");
	           	  isSuccess = updateUser(id, 1); 
	           	 // alert("isSuccess :"+isSuccess);
	           	  if(isSuccess){
	           		
	           		  $(this).dialog('close');	                
	           	  }
	            },
	                "Cancel": function () {
	                $(this).dialog('close');
	            }
	        }
	    });
	}
	
	
	