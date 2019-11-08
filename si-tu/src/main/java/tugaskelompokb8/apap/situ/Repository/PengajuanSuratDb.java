package tugaskelompokb8.apap.situ.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tugaskelompokb8.apap.situ.model.PengajuanSuratModel;


@Repository
public interface PengajuanSuratDb extends JpaRepository<PengajuanSuratModel, Long>{
}
