package co.nilin.mvc.service;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import co.nilin.mvc.data.entity.User;

@Component
public interface IUserService {

	public List<User> findAll();
	public void addUser(String fullname,String username,String password,String email,Date birthdate);
	public Boolean isValid(String username,String password);
	public Boolean isAvailable(String usrname);
	public Long findIdByUserName(String username);
	User findById(Long ID);
	void updateUser(User usr);
}
