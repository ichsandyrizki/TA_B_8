package tugaskelompokb8.apap.situ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tugaskelompokb8.apap.situ.model.JenisSuratModel;
import tugaskelompokb8.apap.situ.repository.JenisSuratDb;

import java.util.List;

@Service
@Transactional
public class JenisSuratServiceImpl implements JenisSuratService{

    @Autowired
    JenisSuratDb jenisSuratDb;

    @Override
    public boolean addJenisSurat(JenisSuratModel jenisSurat) {
        List<JenisSuratModel> jenisSuratList = jenisSuratDb.findAll();
        for(int i = 0; i < jenisSuratDb.findAll().size(); i++){
            if(jenisSurat.getNama().equalsIgnoreCase(jenisSuratList.get(i).getNama())){
                return true;
            }
        }
        jenisSuratDb.save(jenisSurat);
        return false;
    }


    /*@Override
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
*/
    @Override
    public void deleteJenisSurat(Long idJenisSurat) {
        jenisSuratDb.removeByIdJenisSurat(idJenisSurat);
    }

    @Override
    public JenisSuratModel getJenisSuratByNama(String nama) {
        return jenisSuratDb.findByNama(nama);
    }

    @Override
    public JenisSuratModel getJenisSuratById(Long id) {
        return jenisSuratDb.findByIdJenisSurat(id);
    }

    @Override
    public List<JenisSuratModel> getJenisSuratList() {
        return jenisSuratDb.findAll();
    }

}
