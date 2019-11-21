package tugaskelompokb8.apap.situ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugaskelompokb8.apap.situ.model.JenisSuratModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface JenisSuratDb extends JpaRepository<JenisSuratModel, Long> {
    List<JenisSuratModel> removeByIdJenisSurat(Long id);
    JenisSuratModel findByNama(String nama);
    JenisSuratModel findByIdJenisSurat(Long id);
    List<JenisSuratModel> findAll();
}
