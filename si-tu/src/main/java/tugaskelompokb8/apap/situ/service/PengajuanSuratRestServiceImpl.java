package tugaskelompokb8.apap.situ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tugaskelompokb8.apap.situ.model.PengajuanSuratModel;
import tugaskelompokb8.apap.situ.repository.PengajuanSuratDb;

@Service
@Transactional
public class PengajuanSuratRestServiceImpl implements PengajuanSuratRestService{
	@Autowired
	PengajuanSuratDb pengajuanSuratDb;
	

	@Override
	public PengajuanSuratModel getPengajuanSuratById(Long id) {
		return pengajuanSuratDb.findByIdPengajuanSurat(id);
	}

}
