package co.nilin.mvc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.nilin.mvc.data.entity.User;
import co.nilin.mvc.repository.UserRepository;

@Component
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Override
	public List<User> findAll() {
		List<User> users=(List<User>)userRepository.findAll();
		return users;
	}
	@Override
	public void addUser(String fullname, String username, String password, String email, Date birthdate) {
		User user=new User();
		user.setBirthDate(birthdate);
		user.setEmail(email);
		user.setFullName(fullname);
		user.setPassword(password);
		user.setUserName(username);
		userRepository.save(user);
		System.out.println("user added");
		
	}
	@Override
	public Boolean isValid(String username, String password) {
		
		for(User e:userRepository.findAll()) {
			if(e.getUserName().equals(username)&&(e.getPassword().equals(password)))return true;
		}
		return false;
	}
	@Override
	public Boolean isAvailable(String username) {
		
		for(User e:userRepository.findAll()) {
			if(e.getUserName().equals(username))return false;
		}
		return true;
	}
	@Override
	public Long findIdByUserName(String username) {
		for(User u:userRepository.findAll())if(u.getUserName().equals(username))return u.getId();
		return null;
	}
	@Override
	public User findById(Long ID) {
		return userRepository.findById(ID).get();
		
	}
	@Override
	public void updateUser(User usr) {
		userRepository.save(usr);
		
	}

}
