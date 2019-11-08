package tugaskelompokb8.apap.situ.service;

import tugaskelompokb8.apap.situ.model.JenisSuratModel;
import tugaskelompokb8.apap.situ.model.PengajuanSuratModel;

import java.util.List;
import java.util.Optional;

public interface PengajuanSuratService {

    void addPengajuanSurat(PengajuanSuratModel surat);
    List<PengajuanSuratModel> getPengajuanSuratList();
    void deletePengajuanSurat(Long id);
    Optional<PengajuanSuratModel> getPengajuanById(long idPengajuanSurat);
    String createNomor();
    List<String> getAllNomor();
    PengajuanSuratModel updatePengajuan(PengajuanSuratModel model);
}
