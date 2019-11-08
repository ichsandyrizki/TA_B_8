package tugaskelompokb8.apap.situ.Service;


import tugaskelompokb8.apap.situ.model.PengajuanSuratModel;

import java.util.List;
import java.util.Optional;

public interface PengajuanSuratService {

    List<PengajuanSuratModel> getAllPengajuan();
    Optional<PengajuanSuratModel> getPengajuanById(long idPengajuanSurat);
    String createNomor();
    List<String> getAllNomor();
}
