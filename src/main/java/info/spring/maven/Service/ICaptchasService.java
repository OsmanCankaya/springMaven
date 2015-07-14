package info.spring.maven.Service;

import javax.servlet.http.HttpSession;

public interface ICaptchasService {
	public char check(String password);
	public String imageUrl();
	public void setSess(HttpSession httpSess);
}
