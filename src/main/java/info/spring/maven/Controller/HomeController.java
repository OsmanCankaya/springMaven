package info.spring.maven.Controller;

<<<<<<< HEAD
import javax.print.DocFlavor.STRING;
=======

>>>>>>> origin/master
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

<<<<<<< HEAD
import info.spring.maven.Model.Users;
import info.spring.maven.Service.CaptchasDotNet;
import info.spring.maven.Service.UserService;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Autowired
	private UserService userService;

	/**
	 * Returns the list of users
	 * 
	 * @param The
	 *            model coming from client
	 * @return The name of home page
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getUserLists(Model model) {
		try {
			model.addAttribute("userlist", userService.listUser());
		} catch (Exception ex) {
			logger.error("--- error: " + ex);
		}
		return "home";
	}

	/**
	 * Adds a new user to the list.
	 * 
	 * @param request
	 *            The request coming from client
	 * @return A string includes html data
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public @ResponseBody String addUser(HttpServletRequest request) {
		String html = "";
		try {
			// Read the form values
			String password = request.getParameter("captcha");
			Users user = new Users(request.getParameter("name"),
					request.getParameter("surname"),
					request.getParameter("telephone"));
			System.out.println(""+user.toString());
			// /

			CaptchasDotNet captchas = new CaptchasDotNet(
					request.getSession(true), // Ensure session
					"demo", // client
					"secret" // secret
			);
			char captchasCheck = captchas.check(password);

			// Backend validation controls
			if (user.getName() == null || user.getName().equals("")
					|| user.getSurname() == null
					|| user.getSurname().equals("") || user.getPhone() == null 
					|| (user.getPhone().length() != 15
					&& user.getPhone().length() != 0) || captchasCheck == 's'
					|| captchasCheck == 'm' || captchasCheck == 'w'){
				System.out.println("erken return "+user.toString()+" "+captchasCheck +"  "+user.getPhone().length());
				return html;
			}
			System.out.println("val geçildi "+user.toString()+" "+captchasCheck +"  "+user.getPhone().length());
			
			System.out.println("girmesi lazým");
			Users addedUser = userService.addUser(user);

			html = "<tr id = userTable"
					+ addedUser.getId()
					+ ">"
					+ "<td>"
					+ addedUser.getName()
					+ "</td>"
					+ "<td>"
					+ addedUser.getSurname()
					+ "</td>"
					+ "<td>"
					+ addedUser.getPhone()
					+ "</td>"
					+ "<td>"
					+ "<input type=\"submit\" class=\"btn btn-danger\" name=\"deleteButton\" value=\"Delete\" onclick=\"deleteConfirm('"
					+ addedUser.getId()
					+ "');\"/>"
					+ "<input type=\"submit\" class=\"btn btn-success\" name=\"updateButton\" value=\"Update\" onclick=\"updateConfirm('"
					+ addedUser.getId() + "','" + addedUser.getName() + "','"
					+ addedUser.getSurname() + "','" + addedUser.getPhone()
					+ "');\"/>" + "</td>" + "</tr>";
			System.out.println(html);
		} catch (Exception ex) {
			logger.error("--- error: " + ex);
			System.out.println("hata "+html);
		}
		return html;
	}

	/**
	 * Deletes the user with given identity
	 * 
	 * @param request
	 *            The request coming from client
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public @ResponseBody void deleteUser(HttpServletRequest request) {
		try {
			userService.deleteUser(request.getParameter("id"));
		} catch (Exception ex) {
			logger.error("error:" + ex);
		}
	}

	/**
	 * Updates the user with given identity
	 * 
	 * @param request
	 *            The request coming from client
	 * @return A string includes html data
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public @ResponseBody String updateUser(HttpServletRequest request) {
		String html = null;
		try {
			// Read the form values
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String phone = request.getParameter("telephone");

			// Backend validation controls
			if (id == null || name.equals("") || surname == null
					|| surname.equals("") || phone == null 
							|| (phone.length() != 15
							&& phone.length() != 0))
				return html;
			
			userService.updateUser(id, name, surname, phone);

			html = "<tr id = userTable"
					+ id
					+ ">"
					+ "<td>"
					+ name
					+ "</td>"
					+ "<td>"
					+ surname
					+ "</td>"
					+ "<td>"
					+ phone
					+ "</td>"
					+ "<td>"
					+ "<input type=\"submit\" class=\"btn btn-danger\" name=\"deleteButton\" value=\"Delete\" onclick=\"deleteConfirm('"
					+ id
					+ "');\"/>"
					+ "<input type=\"submit\" class=\"btn btn-success\" name=\"updateButton\" value=\"Update\" onclick=\"updateConfirm('"
					+ id + "','" + name + "','" + surname + "','" + phone
					+ "');\"/>" + "</td>" + "</tr>";

		} catch (Exception ex) {
			logger.error("error:" + ex);
		}
		return html;
	}
=======
import info.spring.maven.Model.User;
import info.spring.maven.Service.CaptchasDotNet;
import info.spring.maven.Service.UserService;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	
	@Autowired
	private UserService userService;
	
	
	// User list method-home.jpg
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getUserLists(Model model){
		
		
		 try {
			
			 model.addAttribute("userlist", userService.listUser());
		model.addAttribute("asd",userService.listUser().size());
		 }catch (Exception ex) {
		      logger.error("--- error: " + ex);
		 }return "home";
	}	
	//Users add method and use captcha
	
	@RequestMapping(value="/", method=RequestMethod.POST)		   
    public String addUser(Model model,HttpServletRequest request){    
		
		
		try {
		
			
			CaptchasDotNet captchas = new CaptchasDotNet(
					  request.getSession(true),     // Ensure session
					  "demo",                       // client
					  "secret"                      // secret
					  );
					// Read the form values
					String message  = request.getParameter("message");
					String password = request.getParameter("captcha");
					
					// Check captcha
					char captchasCheck=captchas.check(password);
					
					if(captchasCheck!='s' && captchasCheck!='m' && captchasCheck!='w' )
					{
						User user = new User(request.getParameter("name"), request.getParameter("surname"), request.getParameter("telephone"));	
						User addedUser = userService.addUser(user);
						 
						
					}
					model.addAttribute("userlist", userService.listUser());
					model.addAttribute("asd",userService.listUser().size());
		
		}catch (Exception ex) {
			 logger.error("--- error: " + ex);
		 }
		return "home";
	}
	//  User delete method
	
	
	/** 
	 * 
	 * 
	 * 				Alttaki metod Ajax mantýðýna uygun bir þekilde yazýlmýþtýr.
	 * 
	 *    Butona týklanýlýnca gerçekleþen ajax isteði   
	 * 
	 * görevini yerine getirmektedir fakat  farklý bir sayfaya yönlendirmektedir.
	 * 
	 *   Anasayfaya tekrar geri gelmek gerekiyor.Bunu engelleyemedim.
	 * 
	 * Bu yüzden addUser metodunu yukarýda görüldüðü þekilde (Ajax mantýðý olmadan) yazdým.
	 * 
	 * View de de form yorum satýrý içindedir.
	 * 
	 * */
	
	
	
	
	
	/** *
	 * 
	 * @RequestMapping(value="/add", method=RequestMethod.GET)		   
    public @ResponseBody String addUser(HttpServletRequest request){    
		System.out.println("adddddddddddddddddd");
		String html=null;
		try {
			System.out.println("asd");
			
			CaptchasDotNet captchas = new CaptchasDotNet(
					  request.getSession(true),     // Ensure session
					  "demo",                       // client
					  "secret"                      // secret
					  );
					// Read the form values
					String message  = request.getParameter("message");
					String password = request.getParameter("captcha");
					System.out.println(password);
					// Check captcha
					String body;
					switch (captchas.check(password)) {
					  case 's':
					    body = "Session seems to be timed out or broken. ";
					    body += "Please try again or report error to administrator.";
					    break;
					  case 'm':
					    body = "Every CAPTCHA can only be used once. ";
					    body += "The current CAPTCHA has already been used. ";
					    body += "Please use back button and reload";
					    break;
					  case 'w':
					    body = "You entered the wrong password. ";
					    body += "Please use back button and try again. ";
					    break;
					  default:
					    body = "Your message was verified to be entered by a human and is \"" + message + "\"";
					    User user = new User(request.getParameter("name"), request.getParameter("surname"), request.getParameter("telephone"));	
						
						User addedUser = userService.addUser(user);
					html = "<tr id = userTable" + addedUser.getId() + ">" +			
							  "<td>" + addedUser.getName()+ "</td>" + 					
							  "<td>" + addedUser.getSurname()+ "</td>" +
							  "<td>" + addedUser.getPhone()+ "</td>" +
							  "<td>" +
							  	"<input type=\"button\" class=\"button\" name=\"deleteButton\" value=\"Delete\" onclick=\"deleteConfirm('" + addedUser.getId() + "');\"/>" +
							  	"<input type=\"button\" class=\"button\" name=\"updateButton\" value=\"Update\" onclick=\"updateConfirm('" + addedUser.getId()+ "');\"/>" +
							  "</td>" +				
						  "</tr>";
					    break;
					}
					System.out.println(body+html);

		
		}catch (Exception ex) {
			 logger.error("--- error: " + ex);
		 }
		return html;
	}
	 */
	
	
	// User delete method
	@RequestMapping(value = "/delete", method = RequestMethod.GET) 
	public @ResponseBody void deleteUser(HttpServletRequest request){
		//User user = userService.findUser(request.getParameter("id"));
		
		try {
			
			
			userService.deleteUser(request.getParameter("id"));
		}catch(Exception ex){
			logger.error("error:"+ex);
		}
	}	
	
	/**
	    * Updates the user with given identity
	    * @param request The request coming from client
	    * @return A string includes html data 
	*/
	//User Update method
	@RequestMapping(value = "/update", method = RequestMethod.GET) 
	public @ResponseBody String updateUser(HttpServletRequest request){
		
		String html=null;
		try{
		String Id = request.getParameter("id");
		String Name = request.getParameter("name");
		String Surname = request.getParameter("surname");
		String Phone = request.getParameter("telephone");
		userService.updateUser(Id, Name, Surname, Phone);
		
		html = "<tr id = userTable" + Id + ">" +			
						  "<td>" + Name+ "</td>" + 					
						  "<td>" + Surname + "</td>" +
						  "<td>" + Phone + "</td>" +
						  "<td>" +
						  	"<input type=\"button\" class=\"button\" name=\"deleteButton\" value=\"Delete\" onclick=\"deleteConfirm('" + Id + "');\"/>" +
						  	"<input type=\"button\" class=\"button\" name=\"updateButton\" value=\"Update\" onclick=\"updateConfirm('" + Id + "');\"/>" +
						  "</td>" +				
					  "</tr>";
		 
	
		}catch(Exception ex){
			System.out.println("hata");
			logger.error("error:"+ex);
		}
		return html;
	}	
>>>>>>> origin/master
}
