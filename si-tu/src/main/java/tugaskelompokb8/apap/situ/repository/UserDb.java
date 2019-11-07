package tugaskelompokb8.apap.situ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugaskelompokb8.apap.situ.model.*;

@Repository
public interface UserDb extends JpaRepository<UserModel, Long>{
	UserModel findByUsername(String username);
}
