package tugaskelompokb8.apap.situ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tugaskelompokb8.apap.situ.model.JenisLowonganModel;
import tugaskelompokb8.apap.situ.model.JenisSuratModel;

import java.util.Optional;

public interface JenisLowonganDb extends JpaRepository<JenisLowonganModel, Long> {
    Optional<JenisLowonganModel> findByIdJenisLowongan(Long id);
}
