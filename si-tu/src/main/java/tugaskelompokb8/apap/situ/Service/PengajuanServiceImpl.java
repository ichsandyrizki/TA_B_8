package tugaskelompokb8.apap.situ.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tugaskelompokb8.apap.situ.Repository.PengajuanDb;
import tugaskelompokb8.apap.situ.model.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional
public class PengajuanServiceImpl implements PengajuanService{

    @Autowired
    private PengajuanDb pengajuanDb;

    @Override
    public List<PengajuanSuratModel> getAllPengajuan(){
        return pengajuanDb.findAll();
    }

    @Override
    public Optional<PengajuanSuratModel> getPengajuanById(long idPengajuanSurat){
        return pengajuanDb.findById(idPengajuanSurat);
    }

    @Override
    public List<String> getAllNomor(){
        List<PengajuanSuratModel> listPengajuan = pengajuanDb.findAll();
        List<String> listNomor = new ArrayList<>();
        for(PengajuanSuratModel model : listPengajuan){
            listNomor.add(model.getNomorSurat());
        }
        return listNomor;
    }

    @Override
    public String createNomor(PengajuanSuratModel model){
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

        nomor += date;
        return nomor;
    }


}
