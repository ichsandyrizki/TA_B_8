package tugaskelompokb8.apap.situ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tugaskelompokb8.apap.situ.model.PengajuanSuratModel;
import tugaskelompokb8.apap.situ.repository.PengajuanSuratDb;
import tugaskelompokb8.apap.situ.rest.PengajuanSuratDetail;

@Service
@Transactional
public class PengajuanSuratRestServiceImpl implements PengajuanSuratRestService{
	@Autowired
	PengajuanSuratDb pengajuanSuratDb;
	

	@Override
	public PengajuanSuratDetail getPengajuanSuratById(Long id) {
		PengajuanSuratModel pengajuan =  pengajuanSuratDb.findByIdPengajuanSurat(id);
		PengajuanSuratDetail pengajuanApi = new PengajuanSuratDetail();
		pengajuanApi.setIdUser(pengajuan.getUser().getIdUser());
		pengajuanApi.setJenisSurat(pengajuan.getJenisSurat().getNama());
		pengajuanApi.setKeterangan(pengajuan.getKeterangan());
		pengajuanApi.setStatus(pengajuan.getStatus());
		return pengajuanApi;
	}

}
