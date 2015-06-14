package info.spring.maven.Model;

public class User {

	
	@org.springframework.data.annotation.Id private String Id;
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public User(String name, String surname, String phone) {
		super();
		
		Name = name;
		Surname = surname;
		Phone = phone;
	}
	public User()
	{
		
		
	}
	public User(String id, String name, String surname, String phone) {
		super();
		Id = id;
		Name = name;
		Surname = surname;
		Phone = phone;
	}

	private String Name;
	private String Surname;

	private String Phone;
	@Override
	public String toString() {
		return "User [Id=" + Id + ", Name=" + Name + ", Surname=" + Surname
				+ ", Phone=" + Phone + "]";
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSurname() {
		return Surname;
	}
	public void setSurname(String surname) {
		Surname = surname;
	}
	public String getPhone() {
		return Phone;
	}
	
	public void setPhone(String phone) {
		Phone = phone;
	}
}
