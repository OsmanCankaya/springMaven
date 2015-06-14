package info.spring.maven.Service;

<<<<<<< HEAD
import info.spring.maven.Model.Users;
import java.util.List;

public interface IUserService {
	public Users addUser(Users user);
	public List<Users> listUser() ;
=======




import info.spring.maven.Model.User;

import java.util.List;

public interface IUserService {
	public User addUser(User user);
	public List<User> listUser() ;
>>>>>>> origin/master
	public void deleteUser(String id);
	public void updateUser(String id, String name, String surname, String telephone);
}
