package tugaskelompokb8.apap.situ.service;

import java.util.List;

import reactor.core.publisher.Mono;
import tugaskelompokb8.apap.situ.model.PengajuanSuratModel;
import tugaskelompokb8.apap.situ.rest.PengajuanSuratDetail;

public interface PengajuanSuratRestService {
	PengajuanSuratDetail getPengajuanSuratById(Long id);
	List<PengajuanSuratDetail> getAllPengajuanSuratRuangan();
	PengajuanSuratModel createPengajuan(PengajuanSuratModel pengajuan);
	Object getPengajuan();
}
