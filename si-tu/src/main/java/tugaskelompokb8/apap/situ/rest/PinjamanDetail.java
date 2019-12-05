package tugaskelompokb8.apap.situ.rest;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PinjamanDetail {

    @JsonProperty("tanggalPengajuan")
    private Date tanggalPengajuan;

    @JsonProperty("jumlahPinjaman")
    private Integer jumlahPinjaman;

    public Date getTanggalPengajuan() {
        return this.tanggalPengajuan;
    }

    public void setTanggalPengajuan(Date tanggalPengajuan) {
        this.tanggalPengajuan = tanggalPengajuan;
    }

    public Integer getJumlahPinjaman() {
        return this.jumlahPinjaman;
    }

    public void setJumlahPinjaman(Integer jumlahPinjaman) {
        this.jumlahPinjaman = jumlahPinjaman;
    }
}
