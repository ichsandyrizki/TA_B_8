package tugaskelompokb8.apap.situ.service;


import tugaskelompokb8.apap.situ.model.UserModel;

public interface UserService {
	UserModel addUser(UserModel user);
	UserModel changeUser(UserModel oldUser, String newPass);
	public String encrypt(String password);
}
