package tugaskelompokb8.apap.situ.service;

import tugaskelompokb8.apap.situ.model.JenisLowonganModel;
import tugaskelompokb8.apap.situ.model.JenisSuratModel;

import java.util.List;

public interface JenisSuratService {
    //Method untuk menambah jenis surat
    boolean addJenisSurat(JenisSuratModel jenisSuratModel);

    //Method untuk menghapus jenis surat
    void deleteJenisSurat(Long idJenisSurat);

    //Method untuk mencari jenis surat berdasarkan nama
    JenisSuratModel getJenisSuratByNama(String nama);

    //Method untuk mencari jenis surat berdasarkan id
    JenisSuratModel getJenisSuratById(Long id);

    //Method untuk mendapatkan seua jenis surat yang tersimpan
    List<JenisSuratModel> getJenisSuratList();

}
