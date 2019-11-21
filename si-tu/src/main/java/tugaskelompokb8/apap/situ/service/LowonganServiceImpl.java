package tugaskelompokb8.apap.situ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tugaskelompokb8.apap.situ.model.LowonganModel;
import tugaskelompokb8.apap.situ.repository.LowonganDb;

import java.util.List;

@Service
@Transactional
public class LowonganServiceImpl implements LowonganService {
    @Autowired
    LowonganDb lowonganDb;

    @Override
    public List<LowonganModel> getLowonganList(){
        return lowonganDb.findAll();
    }

    @Override
    public void addLowongan(LowonganModel lowonganModel){
        lowonganDb.save(lowonganModel);
    }
}
