package tugaskelompokb8.apap.situ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tugaskelompokb8.apap.situ.model.JenisSuratModel;
import tugaskelompokb8.apap.situ.model.PengajuanSuratModel;
import tugaskelompokb8.apap.situ.model.UserModel;
import tugaskelompokb8.apap.situ.repository.PengajuanSuratDb;
import tugaskelompokb8.apap.situ.repository.UserDb;

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

    @Autowired
    UserDb userDb;

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

    @Override
    public Optional<PengajuanSuratModel> getPengajuanById(long idPengajuanSurat){
        return pengajuanSuratDb.findById(idPengajuanSurat);
    }

    @Override
    public List<String> getAllNomor(){
        List<PengajuanSuratModel> listPengajuan = pengajuanSuratDb.findAll();
        List<String> listNomor = new ArrayList<String>();
        for(PengajuanSuratModel model : listPengajuan){
            listNomor.add(model.getNomorSurat());
        }
        return listNomor;
    }

    @Override
    public String createNomor(PengajuanSuratModel model){
        PengajuanSuratModel modelFromDb = pengajuanSuratDb.findById(model.getIdPengajuanSurat()).get();
        String noSuratCurrent = modelFromDb.getNomorSurat();
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

        if (noSuratCurrent.length() == 14){
            return noSuratCurrent;
        }
        return nomor;
    }

    @Override
    public PengajuanSuratModel updatePengajuan(PengajuanSuratModel model){
        PengajuanSuratModel modelFromDb = pengajuanSuratDb.findById(model.getIdPengajuanSurat()).get();

        modelFromDb.setStatus(model.getStatus());
        modelFromDb.setNomorSurat(createNomor(model));
        pengajuanSuratDb.save(modelFromDb);
        return modelFromDb;
    }

    @Override
    public boolean getStatusKepsek(PengajuanSuratModel model){
        if(model.getStatus() == 0){
            return true;
        }
        return false;
     }

    @Override
    public boolean getStatusAdmin(PengajuanSuratModel model){
        if(model.getStatus() == 2){
            return true;
        }
        return false;
    }

    @Override
    public List<PengajuanSuratModel> findPengajuanByUser(UserModel user){
        UserModel modelFromDb = userDb.findByIdUser(user.getIdUser());
        List<PengajuanSuratModel> listPengajuan = pengajuanSuratDb.findAll();
        List<PengajuanSuratModel> listSama = new ArrayList<PengajuanSuratModel>();
        for(PengajuanSuratModel model : listPengajuan){
            if (model.getUser() == modelFromDb){
                listSama.add(model);
            }
        }
        return listSama;
    }

}