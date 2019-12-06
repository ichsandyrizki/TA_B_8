package tugaskelompokb8.apap.situ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import tugaskelompokb8.apap.situ.model.LowonganModel;
import tugaskelompokb8.apap.situ.repository.LowonganDb;
import tugaskelompokb8.apap.situ.rest.BaseResponse;
import tugaskelompokb8.apap.situ.rest.UserPerpusDetail;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class LowonganServiceImpl implements LowonganService {
    @Autowired
    LowonganDb lowonganDb;

    @Autowired
    LowonganRestService lowonganRestService;

    @Autowired
    LowonganService lowonganService;

    @Autowired
    UserService userService;

    @Autowired
    JenisLowonganService jenisLowonganService;

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

    @Override
    public LowonganModel getLowonganByJudul(String judul){
        return lowonganDb.findByJudul(judul);
    }

    @Override
    public void createLowonganPustakawan(){
        ArrayList<UserPerpusDetail> listUsers = new ArrayList<UserPerpusDetail>();
        Mono<BaseResponse> respon = lowonganRestService.getAllUser();
        ArrayList<LinkedHashMap<String, String>> data = (ArrayList<LinkedHashMap<String, String>>) Objects.requireNonNull(respon.block()).getResult();
        for(int i=0; i<data.size(); i++){
            UserPerpusDetail user = new UserPerpusDetail();
            user.setUsername(data.get(i).get("username"));
            user.setRole(data.get(i).get("role"));
            listUsers.add(user);
        }
        int count = 0;
        for(int i=0; i<listUsers.size(); i++){
            if(listUsers.get(i).getRole().equals("PUSTAKAWAN")){
                count += 1;
            }
        }
        System.out.println(count);
        if(count <5){
            if(lowonganService.getLowonganByJudul("Lowongan Pustakawan") == null){
                createLowongan(count);
            } else if(lowonganService.getLowonganByJudul("Lowongan Pustakawan") != null){
                LowonganModel siLowongan = lowonganService.getLowonganByJudul("Lowongan Pustakawan");
                if(!siLowongan.getJumlah().equals(count)){
                    siLowongan.setJumlah(5 - count);
                    lowonganDb.save(siLowongan);
                }
            }
        } else{
            if(lowonganService.getLowonganByJudul("Lowongan Pustakawan") != null){
                LowonganModel siLowongan = lowonganService.getLowonganByJudul("Lowongan Pustakawan");
                lowonganDb.delete(siLowongan);
            }
        }
    }

    @Override
    public void createLowongan(int jumlah){
        LowonganModel lowonganPustakawan = new LowonganModel();
        lowonganPustakawan.setUser(userService.getUserCurrentLoggedIn());
        lowonganPustakawan.setJumlah(5 - jumlah);
        lowonganPustakawan.setKeterangan("Dibutuhkan Pustakawan Cakap");
        lowonganPustakawan.setJudul("Lowongan Pustakawan");
        lowonganPustakawan.setTanggalDibuka(Date.valueOf(LocalDate.now()));
        lowonganPustakawan.setTanggalDitutup(Date.valueOf(LocalDate.now().plusMonths(1)));
        lowonganPustakawan.setJenisLowongan(jenisLowonganService.getJenisByNama(("pustakawan").toLowerCase()));
        lowonganService.addLowongan(lowonganPustakawan);
    }
}
