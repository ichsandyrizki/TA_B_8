package tugaskelompokb8.apap.situ.rest;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PinjamanDetail {

    @JsonProperty("jumlahPinjaman")
    private Integer jumlahPinjaman;

    @JsonProperty("tanggalPinjaman")
    private Date tanggalPinjaman;

    public Integer getJumlahPinjaman() {
        return jumlahPinjaman;
    }

    public Date getTanggalPinjaman() {
        return tanggalPinjaman;
    }

    public void setTanggalPinjaman(Date tanggalPinjaman) {
        this.tanggalPinjaman = tanggalPinjaman;
    }

    public void setJumlahPinjaman(Integer jumlahPinjaman) {
        this.jumlahPinjaman = jumlahPinjaman;
    }
}
