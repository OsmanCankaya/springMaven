<<<<<<< HEAD
=======
	function maskTelephone() {
		
		$("#telephone0").mask("(999) 999-9999");
		$("#telephone1").mask("(999) 999-9999");
	}

	
>>>>>>> origin/master
	function addUser(count){	   
		var name = $('#name' + count).val();
		var surname = $('#surname' + count).val();
		var telephone = $('#telephone' + count).val();
		var captcha = $('#captcha' + count).val();
 		var dataVar = 'name=' + name + '&surname=' + surname + '&telephone=' + telephone+'&captcha='+captcha;	
 		//maskTelephone();
<<<<<<< HEAD
 		
 		hideValidationFields(count);
		if(validateFields(count)){
			return;
		} 
		$('#loading').css('display' , 'block');	// to display loading image	
	    $.ajax({
	    	
	        url: 'add', 
	        
	        data: dataVar,
	        success: function(response) {
	        	
	        	$('#userTable').append(response);
	        	$('#name' + count).val("");
	    		$('#surname' + count).val("");
	    		$('#telephone' + count).val("");
	    		$('#captcha' + count).val("");
	    		$('#loading').css('display' , 'none'); // to hide loading image
	    		$("#captchaDiv").load(location.href + " #captchaDiv");
	    		//$('#captchaDiv').reload(true);
=======
		hideValidationFields(count);
		if(validateFields(count)){
			return;
		}
		
	    $.ajax({	        
	        url: 'add', 
	        type: 'get',
	        data: dataVar,
	        success: function(response) {	
	        	$('#name'+count).val("buraya girildi");
	        	$('#userTable').append(response);   
	        	
	        	$('#surname'+count).val("");
	        	$('#telephone'+count).val("");
	        	
>>>>>>> origin/master
	        }
	    });	  
	}
	
	function hideValidationFields(count){
<<<<<<< HEAD
		    
		$('#nameEmptyValidation' + count).hide();
		$('#surnameEmptyValidation' + count).hide();
		$('#telephoneEmptyValidation' + count).hide(); 
=======
		$('#nameEmptyValidation' + count).hide();
		$('#surnameEmptyValidation' + count).hide();
		$('#telephoneEmptyValidation' + count).hide();
>>>>>>> origin/master
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
<<<<<<< HEAD
			$('#nameEmptyValidation' + count).text("This field is required ");
=======
>>>>>>> origin/master
			$('#nameEmptyValidation' + count).show();
			validationResult = true;
		  }
		if (surname==null || surname=="")
		  {
<<<<<<< HEAD
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
=======
			$('#surnameEmptyValidation' + count).show();
			validationResult = true;
		  }
		if (telephone==null || telephone=="")
		  {
			$('#telephoneEmptyValidation' + count).show();
			validationResult = true;
		  }
		if (captcha==null || captcha=="")
		  {
			$('#captchaEmptyValidation' + count).show();
			validationResult = true;
		  }
>>>>>>> origin/master
		return validationResult;
	}
	
	function deleteUser(id){	
		console.log(id);
 		var dataId = 'id=' + id;
		console.log(dataId);
<<<<<<< HEAD
		$('#loading').css('display' , 'block');	// to display loading image	
=======
>>>>>>> origin/master
	    $.ajax({	        
	        url: 'delete',
	        data: dataId,
	        success: function(response) {
	        	$('#userTable' + id).fadeIn(1000).fadeOut(200, function(){
	        		$('#userTable' + id).remove();})
<<<<<<< HEAD
	        	        $('#loading').css('display' , 'none'); // to hide loading image
	        }
	    
=======
	        	//$('#userTable' + id).remove();            
	        }
>>>>>>> origin/master
	    });	  
	}
	
	function deleteConfirm(id, count){
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
<<<<<<< HEAD
	           
=======
	            //    var x=document.getElementById("loading").style.display = "block";
>>>>>>> origin/master
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
<<<<<<< HEAD
 		//maskTelephone();
 		isSuccess = false;
 		hideValidationFields(count);
		if(validateFields(count)){
			isSuccess = false;
			return isSuccess;
		}   
		$('#loading').css('display' , 'block');	// to display loading image
	    $.ajax({	        
	        url: 'update',
	        data: dataId,
	        success: function(response) {  
	    	
	        	$('#userTable' + id).replaceWith(response);
	        	isSuccess = true;
	        	$('#loading').css('display' , 'none'); // to hide loading image
	        	return isSuccess;
	        }
	    });	  
	    isSuccess = true;
	    $('#loading').css('display' , 'none'); // to hide loading image
	    return isSuccess;
	}
	
	function updateConfirm(id,name,surname,telephone){
		
=======
 		/**maskTelephone();
 		hideValidationFields(count);
		if(validateFields(count)){
			return;
		}   */
	    $.ajax({	        
	        url: 'update',
	        data: dataId,
	        success: function(response) {    
	        	$('#userTable' + id).replaceWith(response);
	        }
	    });	  
	}
	
	function updateConfirm(id,name,surname,telephone){
>>>>>>> origin/master
		$('#name' + 1).val(name);
		$('#surname' + 1).val(surname);
		$('#telephone' + 1).val(telephone);
	    $("#updateDialog").dialog({
	        resizable: false,
	        modal: true,
	        title: "Update user information",
	        height: 350,
	        width: 400,
	        buttons: {
	           	    "Update user": function () {
<<<<<<< HEAD
	           	  isSuccess = updateUser(id, 1); 
	           	  if(isSuccess){
	           		
	           		  $(this).dialog('close');	                
	           	  }
=======
	           	    updateUser(id, 1);
	                $(this).dialog('close');	                
>>>>>>> origin/master
	            },
	                "Cancel": function () {
	                $(this).dialog('close');
	            }
	        }
	    });
<<<<<<< HEAD
	}
	
	//to input mask:
	$(document).ready(function(){ 
	      $("input.phone").keyup(function() { 
	                var val = $(this).val(); 
	                var bas = val.length > 15 ? val.substr(0,15) : val; 
	                var son = val.length > 15 ? val.substr(15,val.length) : ""; 
	                val = val.length > 15 ? String(bas.replace(/[\D]/g, '')) + 
	String(son.replace(/[^0-9\s\-]/g, '')) : String(val.replace(/[\D]/g, 
	'')); 
	                var str = ""; 
	                if(val.length == 11) { 
	                        str = "(" + val.substr(0,3) + ") " + val.substr(3,3) + " " + 
	val.substr(6,2) + " " + val.substr(8,2) + " - " + 
	val.substr(10,val.length); 
	                } else if(val.length >= 8) { 
	                        str = "(" + val.substr(0,3) + ") " + val.substr(3,3) + " " + 
	val.substr(6,2) + " " + val.substr(8,val.length); 
	                } else if(val.length >=6) { 
	                        str = "(" + val.substr(0,3) + ") " + val.substr(3,3) + " " + 
	val.substr(6,val.length); 
	                } else if(val.length >=3) { 
	                        str = "(" + val.substr(0,3) + ") " + val.substr(3,val.length); 
	                } else { 
	                        str = val; 
	                } 
	                $(this).val(str); 
	        }); 
	}); 
	
=======
	}
>>>>>>> origin/master
