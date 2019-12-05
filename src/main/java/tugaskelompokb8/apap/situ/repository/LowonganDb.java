package tugaskelompokb8.apap.situ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugaskelompokb8.apap.situ.model.JenisLowonganModel;
import tugaskelompokb8.apap.situ.model.LowonganModel;
import tugaskelompokb8.apap.situ.service.LowonganRestService;

@Repository
public interface LowonganDb extends JpaRepository<LowonganModel, Long> {
   LowonganModel findByIdLowongan(Long id);
   LowonganModel findByJudul(String nama);
}
