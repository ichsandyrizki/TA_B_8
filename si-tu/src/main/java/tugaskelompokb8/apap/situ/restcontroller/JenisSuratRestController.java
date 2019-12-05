package tugaskelompokb8.apap.situ.restcontroller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import tugaskelompokb8.apap.situ.repository.JenisSuratDb;
import tugaskelompokb8.apap.situ.model.*;
import tugaskelompokb8.apap.situ.rest.*;

@RestController
@RequestMapping(value="/api/v1/situ")
public class JenisSuratRestController {
	@Autowired
	JenisSuratDb jenisSuratDb;
	
	@GetMapping(value="jenisSurat/all")
	public BaseResponse<List<JenisSuratModel>> retrieveAllJenisSurat(){
		try {
			BaseResponse<List<JenisSuratModel>> response = new BaseResponse<List<JenisSuratModel>>();
			response.setMessage("success");
			response.setStatus(200);
			response.setResult(jenisSuratDb.findAll());
	
			return response;
		}
		catch(NoSuchElementException e){
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, " Not Found");
		}
	}
}
