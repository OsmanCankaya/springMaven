package info.spring.maven.Controller;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.util.JSON;

import info.spring.maven.Model.Users;
import info.spring.maven.Service.CaptchasDotNet;
import info.spring.maven.Service.ICaptchasService;
import info.spring.maven.Service.IUserService;
import info.spring.maven.Service.UserService;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	
	@Autowired
	private IUserService userService;
	
	
	@Autowired
	private ICaptchasService captchas;
	
	
	/**
	 * Returns the list of users
	 * 
	 * @param The model coming from client
	 * @return The name of home page
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getUserLists(HttpServletRequest request,Model model) {
		try {
			
			model.addAttribute("userlist", userService.listUser());
			captchas.setSess(request.getSession(true));
			model.addAttribute("captchas", captchas);
			
		} catch (Exception ex) {
			logger.error("--- error: " + ex);
		}
		return "home";
	}

	/**
	 * Adds a new user to the list
	 * 
	 * @param request 
	 *            The request coming from client
	 * @return A string includes html data
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public @ResponseBody Users addUser(HttpServletRequest request) {
		Users addedUser= null;
		try {
			// Read the form values
			String password = request.getParameter("captcha");
			Users user = new Users(request.getParameter("name"),
					request.getParameter("surname"),
					request.getParameter("telephone"));

			
			captchas.setSess(request.getSession(true));
			char captchasCheck = captchas.check(password);
			
			// Backend validation controls
			if (user.getName() == null
					|| user.getName().equals("")
					|| user.getSurname() == null
					|| user.getSurname().equals("")
					|| user.getPhone() == null
					|| (user.getPhone().length() != 15 && user.getPhone()
							.length() != 0) || captchasCheck == 's'
					|| captchasCheck == 'm' || captchasCheck == 'w') {
				return addedUser;
			}
			addedUser = userService.addUser(user);

		} catch (Exception ex) {
			logger.error("error:" + ex);
			return null;
		}
		return addedUser;
	}

	
	/**
	 * Deletes the user with given identity
	 * @param request The request coming from client
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public @ResponseBody void deleteUser(HttpServletRequest request) {
		try {
			userService.deleteUser(request.getParameter("id"));
		} catch (Exception ex) {
			logger.error("error:" + ex);
		}
	}

	
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public @ResponseBody Users updateUser(HttpServletRequest request) {
		Users updatedUser = null;
		try {
			// Read the form values
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String phone = request.getParameter("telephone");

			// Backend validation controls
			if (id == null || id.equals("") || name == null || name.equals("")
					|| surname == null || surname.equals("") || phone == null
					|| (phone.length() != 15 && phone.length() != 0))
				return updatedUser;
			updatedUser = new Users(name, surname, phone);
			userService.updateUser(id, name, surname, phone);
			
		} catch (Exception ex) {
			logger.error("error:" + ex);
			return null;
		}
		return updatedUser;
	}
}
