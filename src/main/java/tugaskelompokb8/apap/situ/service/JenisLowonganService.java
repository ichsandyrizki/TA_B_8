package tugaskelompokb8.apap.situ.service;

import tugaskelompokb8.apap.situ.model.JenisLowonganModel;

import java.util.List;

public interface JenisLowonganService {
    //Method untuk mendapatkan data jenis berdasarkan id
    JenisLowonganModel getJenisById(long id);

    //Method untuk menambah jenis
    boolean addJenisLowongan (JenisLowonganModel jenisLowongan);

    //Method untuk mendapatkan data semua jenis yang tersimpan
    List<JenisLowonganModel> getJenisList();

    //Method untuk mengubah data jenis
    JenisLowonganModel changeJenis(JenisLowonganModel jenisModel);

    //Method untuk menghapus data jenis
    void deleteJenisLowongan(JenisLowonganModel jenisModel);

    JenisLowonganModel getJenisByNama(String nama);
}
