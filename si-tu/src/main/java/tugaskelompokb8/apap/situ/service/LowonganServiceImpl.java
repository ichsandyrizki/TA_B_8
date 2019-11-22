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

	@Override
	public LowonganModel changeLowongan(LowonganModel lowongan, Long idLowongan) {
		LowonganModel oldLowongan = lowonganDb.findByIdLowongan(idLowongan);
		oldLowongan.setIdLowongan(idLowongan);
		oldLowongan.setKeterangan(lowongan.getKeterangan());
		oldLowongan.setJumlah(lowongan.getJumlah());
		oldLowongan.setTanggalDibuka(lowongan.getTanggalDibuka());
		oldLowongan.setTanggalDitutup(lowongan.getTanggalDitutup());
		return lowonganDb.save(lowongan);
	}
}
