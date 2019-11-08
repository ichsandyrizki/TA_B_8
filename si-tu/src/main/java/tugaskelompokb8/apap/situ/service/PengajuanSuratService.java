package tugaskelompokb8.apap.situ.service;

import tugaskelompokb8.apap.situ.model.JenisSuratModel;
import tugaskelompokb8.apap.situ.model.PengajuanSuratModel;

import java.util.List;

public interface PengajuanSuratService {
    void addPengajuanSurat(PengajuanSuratModel surat);
    List<PengajuanSuratModel> getPengajuanSuratList();
    public void deletePengajuanSurat(Long id);
}
