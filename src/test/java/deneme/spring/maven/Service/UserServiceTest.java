package deneme.spring.maven.Service;

import static org.junit.Assert.*;
<<<<<<< HEAD
import info.spring.maven.Model.Users;
=======
import info.spring.maven.Model.User;
>>>>>>> origin/master
import info.spring.maven.Service.UserService;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserServiceTest {

	
	 UserService uservice=new UserService();
<<<<<<< HEAD
	 Users user=new Users();
=======
	 User user=new User();
>>>>>>> origin/master
	 
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		uservice.addUser(user);
		uservice.listUser();
		
		fail("Not yet implemented");
	}

}
