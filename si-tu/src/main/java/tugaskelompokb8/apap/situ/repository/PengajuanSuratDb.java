package tugaskelompokb8.apap.situ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugaskelompokb8.apap.situ.model.JenisSuratModel;
import tugaskelompokb8.apap.situ.model.PengajuanSuratModel;

@Repository
public interface PengajuanSuratDb extends JpaRepository<PengajuanSuratModel, Long> {
    void deleteByIdPengajuanSurat(Long aLong);
    PengajuanSuratModel findByIdPengajuanSurat(Long id);
}
