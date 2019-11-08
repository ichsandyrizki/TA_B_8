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
    public void addJenisSurat(JenisSuratModel jenisSuratModel) {
        jenisSuratDb.save(jenisSuratModel);
    }

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
