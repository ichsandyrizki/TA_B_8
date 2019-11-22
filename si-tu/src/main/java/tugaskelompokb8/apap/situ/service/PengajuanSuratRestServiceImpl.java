package tugaskelompokb8.apap.situ.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import tugaskelompokb8.apap.situ.rest.*;
import tugaskelompokb8.apap.situ.model.PengajuanSuratModel;
import tugaskelompokb8.apap.situ.repository.PengajuanSuratDb;

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


	@Override
	public PengajuanSuratModel createPengajuan(PengajuanSuratModel pengajuan) {
		return pengajuanSuratDb.save(pengajuan);
	}
	
	static RestTemplate restTemplate = new RestTemplate();
	
	//Method buat ngambil mock server retrieve pengsju
	@Override
	public Object getPengajuan() {
		ResponseEntity<Object> pengajuan = restTemplate.getForEntity(Setting.pengajuanURL +  "/ruangan" , Object.class);
        return pengajuan.getBody();
	}


	@Override
	public List<PengajuanSuratDetail> getAllPengajuanSuratRuangan() {
		List<PengajuanSuratModel> listPengajuan = pengajuanSuratDb.findAll();
		List<PengajuanSuratDetail> listPengajuanSuratRuangan = new ArrayList<PengajuanSuratDetail>();
		for(PengajuanSuratModel i : listPengajuan) {
			if(i.getJenisSurat().getNama().equals("Surat Pengajuan Ruangan")) {
				PengajuanSuratDetail addPengajuan = new PengajuanSuratDetail();
				addPengajuan.setIdUser(i.getUser().getIdUser());
				addPengajuan.setJenisSurat(i.getJenisSurat().getNama());
				addPengajuan.setKeterangan(i.getKeterangan());
				addPengajuan.setStatus(i.getStatus());
				listPengajuanSuratRuangan.add(addPengajuan);
			}
		}
 		return listPengajuanSuratRuangan;
	}
}
