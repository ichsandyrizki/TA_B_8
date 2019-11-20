package tugaskelompokb8.apap.situ.service;

import tugaskelompokb8.apap.situ.model.LowonganModel;

import java.util.List;

public interface LowonganService {
    List<LowonganModel> getLowonganList();
    void addLowongan (LowonganModel lowonganModel);
}
