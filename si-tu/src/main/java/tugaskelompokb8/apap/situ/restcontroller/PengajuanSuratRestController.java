package tugaskelompokb8.apap.situ.restcontroller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import reactor.core.publisher.Mono;
import tugaskelompokb8.apap.situ.model.PengajuanSuratModel;
import tugaskelompokb8.apap.situ.repository.JenisSuratDb;
import tugaskelompokb8.apap.situ.repository.UserDb;
import tugaskelompokb8.apap.situ.rest.BaseResponse;
import tugaskelompokb8.apap.situ.rest.PengajuanSuratDetail;
import tugaskelompokb8.apap.situ.rest.PengajuanSuratPostDetail;
import tugaskelompokb8.apap.situ.service.PengajuanSuratRestService;
import tugaskelompokb8.apap.situ.service.PengajuanSuratService;

@RestController
@RequestMapping(value="/api/v1/situ")
public class PengajuanSuratRestController {
	@Autowired
	PengajuanSuratRestService pengajuanSuratRestService;
	
	@Autowired
	JenisSuratDb jenisSuratDb;
	
	@Autowired
	UserDb userDb;
	

	
	@GetMapping(value="pengajuanSurat/{idPengajuan}")
	public BaseResponse<PengajuanSuratDetail> retrievePengajuanSurat(@PathVariable (value="idPengajuan") Long idPengajuan) {
		try {
			BaseResponse<PengajuanSuratDetail> response = new BaseResponse<PengajuanSuratDetail>();
			response.setMessage("success");
			response.setStatus(200);
			response.setResult(pengajuanSuratRestService.getPengajuanSuratById(idPengajuan));
			
			return response;
		}
		catch(NoSuchElementException e){
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, " Not Found");
		}
	}
	
	
	@PostMapping(value="pengajuanSurat/add")
	public PengajuanSuratModel createPengjuanSurat(@Valid @RequestBody PengajuanSuratPostDetail pengajuan, 
			BindingResult bindingResult) {
	
		if(bindingResult.hasFieldErrors()) {
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST,"Request body has invalid type or missing field");
				
		}else {
			PengajuanSuratModel pengajuanBaru = new PengajuanSuratModel();
			pengajuanBaru.setKeterangan(pengajuan.getKeterangan());
			pengajuanBaru.setJenisSurat(jenisSuratDb.findByIdJenisSurat(pengajuan.getJenisSurat()));
			pengajuanBaru.setTanggalDisetujui(null);
	        pengajuanBaru.setStatus(0);
	        pengajuanBaru.setNomorSurat("0");
	        pengajuanBaru.setUser(userDb.findByIdUser("1"));  
			java.util.Date nows = new java.util.Date();
			Date curr = new Date(nows.getTime());
				        
	        pengajuanBaru.setTanggalPengajuan(curr);
			return pengajuanSuratRestService.createPengajuan(pengajuanBaru);
		}
	}
	
	//NYOBA DI POSTMAN
	@GetMapping(value="pengajuanSurat/get/ruangan")
	public Object getPengajuan(){
		Object testPengajuan = pengajuanSuratRestService.getPengajuan();
		return pengajuanSuratRestService.getPengajuan();
	}
}
