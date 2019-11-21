package tugaskelompokb8.apap.situ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tugaskelompokb8.apap.situ.model.UserModel;



@Repository
public interface UserDb extends JpaRepository<UserModel, Long>{
	UserModel findByUsername(String Username);
	UserModel findByIdUser(String idUser);
}