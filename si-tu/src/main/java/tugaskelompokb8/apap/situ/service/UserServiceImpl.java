package tugaskelompokb8.apap.situ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tugaskelompokb8.apap.situ.model.UserModel;
import tugaskelompokb8.apap.situ.repository.UserDb;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDb userDb;
	
	@Override
	public UserModel addUser(UserModel user) {
		String pass = encrypt(user.getPassword());
		user.setPassword(pass);
		return userDb.save(user);
	}

	@Override
	public UserModel changeUser(UserModel oldUser, String newPass) {
		String pass = encrypt(newPass);
		oldUser.setPassword(pass);
		return userDb.save(oldUser);
	}

	@Override
	public String encrypt(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	@Override
	public UserModel getUserCurrentLoggedIn(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";

		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		}
		else {
			username = principal.toString();
		}

		return userDb.findByUsername(username);
	}

}
