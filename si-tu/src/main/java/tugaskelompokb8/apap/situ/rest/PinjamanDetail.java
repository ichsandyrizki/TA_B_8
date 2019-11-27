package tugaskelompokb8.apap.situ.rest;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PinjamanDetail {

    @JsonProperty("tanggalPengajuan")
    private Date tanggalPinjaman;

    @JsonProperty("jumlahPeminjaman")
    private Integer jumlahPinjaman;

    public Date getTanggalPinjaman() {
        return tanggalPinjaman;
    }

    public void setTanggalPinjaman(Date tanggalPinjaman) {
        this.tanggalPinjaman = tanggalPinjaman;
    }

    public Integer getJumlahPinjaman() {
        return jumlahPinjaman;
    }

    public void setJumlahPinjaman(Integer jumlahPinjaman) {
        this.jumlahPinjaman = jumlahPinjaman;
    }
}
