package tugaskelompokb8.apap.situ.restcontroller;

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

import tugaskelompokb8.apap.situ.model.PengajuanSuratModel;
import tugaskelompokb8.apap.situ.rest.PengajuanSuratDetail;
import tugaskelompokb8.apap.situ.service.PengajuanSuratRestService;

@RestController
@RequestMapping(value="/api/v1/situ")
public class PengajuanSuratRestController {
	@Autowired
	PengajuanSuratRestService pengajuanSuratRestService;
	
	@GetMapping(value="pengajuanSurat/{idPengajuanSurat}")
	public PengajuanSuratDetail retrievePengajuanSurat(@PathVariable("idPengajuanSurat") Long idPengajuan) {
		try {
			return pengajuanSuratRestService.getPengajuanSuratById(idPengajuan);
		}
		catch(NoSuchElementException e){
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,"ID Pengajuan Surat " + idPengajuan + " Not Found");
		}
	}
	
	
	@PostMapping(value="pengajuanSurat/add")
	public PengajuanSuratModel createPengjuanSurat(@Valid @RequestBody PengajuanSuratModel pengajuan, 
			BindingResult bindingResult) {
		if(bindingResult.hasFieldErrors()) {
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST,"Request body has invalid type or missing field");
				
		}else {
			return pengajuanSuratRestService.createPengajuan(pengajuan);
		}
	}
}
