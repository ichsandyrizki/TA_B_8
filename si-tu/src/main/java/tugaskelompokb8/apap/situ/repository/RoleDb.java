package tugaskelompokb8.apap.situ.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tugaskelompokb8.apap.situ.model.RoleModel;


@Repository
public interface RoleDb extends JpaRepository<RoleModel, Long>{
	List<RoleModel> findAll();

    RoleModel findByIdRole(long idRole);
}

