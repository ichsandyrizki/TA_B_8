package tugaskelompokb8.apap.situ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tugaskelompokb8.apap.situ.model.JenisLowonganModel;
import tugaskelompokb8.apap.situ.repository.JenisLowonganDb;

import java.util.List;

@Service
@Transactional
public class JenisLowonganServiceImpl implements JenisLowonganService {

    @Autowired
    JenisLowonganDb jenisLowonganDb;

    @Override
    public JenisLowonganModel getJenisById(long id) {
        return jenisLowonganDb.findById(id).get();
    }

    @Override
    public JenisLowonganModel getJenisByNama(String nama) {
        return jenisLowonganDb.findByNama(nama).get();
    }


    @Override
    public boolean addJenisLowongan(JenisLowonganModel jenisLowongan) {
        List<JenisLowonganModel> listLowongan = jenisLowonganDb.findAll();
        for(int i = 0; i < jenisLowonganDb.findAll().size(); i++){
            if(jenisLowongan.getNama().toLowerCase().equals(listLowongan.get(i).getNama().toLowerCase())){
                return true;
            }
        }
        jenisLowonganDb.save(jenisLowongan);
        return false;

    }

    @Override
    public List<JenisLowonganModel> getJenisList() {
        return jenisLowonganDb.findAll();
    }

    @Override
    public JenisLowonganModel changeJenis(JenisLowonganModel jenisModel) {
        return null;
    }

    @Override
    public void deleteJenisLowongan(JenisLowonganModel jenisModel) {
        jenisLowonganDb.delete(jenisModel);
    }

}
