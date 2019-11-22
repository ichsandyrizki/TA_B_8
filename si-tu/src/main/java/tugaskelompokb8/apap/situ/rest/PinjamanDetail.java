package tugaskelompokb8.apap.situ.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PinjamanDetail {

    @JsonProperty("jumlahPinjaman")
    private Integer jumlahPinjaman;

    public Integer getJumlahPinjaman() {
        return jumlahPinjaman;
    }

    public void setJumlahPinjaman(Integer jumlahPinjaman) {
        this.jumlahPinjaman = jumlahPinjaman;
    }
}
