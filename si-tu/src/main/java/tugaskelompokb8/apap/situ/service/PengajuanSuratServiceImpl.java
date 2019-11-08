package tugaskelompokb8.apap.situ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tugaskelompokb8.apap.situ.model.JenisSuratModel;
import tugaskelompokb8.apap.situ.model.PengajuanSuratModel;
import tugaskelompokb8.apap.situ.repository.PengajuanSuratDb;

import java.util.List;

@Service
@Transactional
public class PengajuanSuratServiceImpl implements PengajuanSuratService {
    @Autowired
    PengajuanSuratDb pengajuanSuratDb;

    @Override
    public void addPengajuanSurat(PengajuanSuratModel surat){
        pengajuanSuratDb.save(surat);
    }

    @Override
    public List<PengajuanSuratModel> getPengajuanSuratList(){
            return pengajuanSuratDb.findAll();
    }

    @Override
    public void deletePengajuanSurat(Long id){
        pengajuanSuratDb.deleteByIdPengajuanSurat(id);
    }

}
