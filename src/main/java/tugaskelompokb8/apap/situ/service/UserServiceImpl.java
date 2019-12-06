package tugaskelompokb8.apap.situ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tugaskelompokb8.apap.situ.model.RoleModel;
import tugaskelompokb8.apap.situ.model.UserModel;
import tugaskelompokb8.apap.situ.repository.RoleDb;
import tugaskelompokb8.apap.situ.repository.UserDb;
import tugaskelompokb8.apap.situ.rest.UserSivitasModel;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDb userDb;

	@Autowired
	RoleDb roleDb;
	
	@Override
	public UserModel addUser(UserSivitasModel user) {
		String pass = encrypt(user.getPassword());
		UserModel userModel = new UserModel();
		userModel.setPassword(pass);
		userModel.setUsername(user.getUsername());
		RoleModel role = roleDb.findByIdRole(user.getIdRole());
		userModel.setRole(role);
		return userDb.save(userModel);
	}

	@Override
	public List<UserModel> getListUser(){
		return userDb.findAll();
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

	@Override
	public void deleteUser(UserModel userModel) {
		userDb.delete(userModel);
	}

}
