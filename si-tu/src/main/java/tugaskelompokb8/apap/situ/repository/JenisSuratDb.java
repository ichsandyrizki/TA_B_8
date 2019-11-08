package tugaskelompokb8.apap.situ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tugaskelompokb8.apap.situ.model.JenisSuratModel;

import java.util.List;
import java.util.Optional;

public interface JenisSuratDb extends JpaRepository<JenisSuratModel, Long> {
    List<JenisSuratModel> removeByIdJenisSurat(Long id);
    JenisSuratModel findByNama(String nama);
    JenisSuratModel findByIdJenisSurat(Long id);
}
