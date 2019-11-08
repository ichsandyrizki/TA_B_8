package tugaskelompokb8.apap.situ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tugaskelompokb8.apap.situ.model.JenisSuratModel;
import tugaskelompokb8.apap.situ.model.PengajuanSuratModel;
import tugaskelompokb8.apap.situ.repository.PengajuanSuratDb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        pengajuanSuratDb.deleteById(id);
    }

    @Override
    public Optional<PengajuanSuratModel> getPengajuanById(long idPengajuanSurat){
        return pengajuanSuratDb.findById(idPengajuanSurat);
    }

    @Override
    public List<String> getAllNomor(){
        List<PengajuanSuratModel> listPengajuan = pengajuanSuratDb.findAll();
        List<String> listNomor = new ArrayList<>();
        for(PengajuanSuratModel model : listPengajuan){
            listNomor.add(model.getNomorSurat());
        }
        return listNomor;
    }

    @Override
    public String createNomor(){
        Date date = new Date();
        String nomor ="";
        List<String> listNomor = getAllNomor();
        DateFormat formatter = new SimpleDateFormat("ddMMyy");
        String strDate = formatter.format(date);
        String numbers ="1234567890";
        do {
            for(int i=0;i<8;i++){
                int randInt = (int)(numbers.length()*Math.random());
                nomor += numbers.charAt(randInt);
            }
        }while (listNomor.contains(nomor));


        nomor += strDate;
        return nomor;
    }

    @Override
    public PengajuanSuratModel updatePengajuan(PengajuanSuratModel model){
        PengajuanSuratModel modelFromDb = pengajuanSuratDb.findById(model.getIdPengajuanSurat()).get();

        modelFromDb.setStatus(model.getStatus());
        pengajuanSuratDb.save(modelFromDb);
        return modelFromDb;
    }

}
