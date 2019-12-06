package tugaskelompokb8.apap.situ.service;


import tugaskelompokb8.apap.situ.model.UserModel;
import tugaskelompokb8.apap.situ.rest.UserSivitasModel;

import java.util.List;

public interface UserService {
	UserModel addUser(UserSivitasModel user);

    List<UserModel> getListUser();

    UserModel changeUser(UserModel oldUser, String newPass);
	public String encrypt(String password);
	UserModel getUserCurrentLoggedIn();

    void deleteUser(UserModel userModel);
}
