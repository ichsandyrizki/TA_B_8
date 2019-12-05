//package tugaskelompokb8.apap.situ.rest;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import java.io.Serializable;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Entity
//@Table(name = "pinjaman")
//public class PinjamanModel implements Serializable {
//
//    //    @NotNull
//    @Column(name = "tanggalPengajuan", nullable = false)
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-dd-MM")
//    private Date tanggalPengajuan;
//
//    //    @NotNull
//    @Column(name = "jumlahPeminjaman", nullable = false)
//    private Integer jumlahPeminjaman;
//
//    public Date getTanggalPengajuan() {
//        return tanggalPengajuan;
//    }
//
//    public void setTanggalPengajuan(Date tanggalPengajuan) {
//        this.tanggalPengajuan = tanggalPengajuan;
//    }
//
//    public Integer getJumlahPeminjaman() {
//        return jumlahPeminjaman;
//    }
//
//    public void setJumlahPeminjaman(Integer jumlahPeminjaman) {
//        this.jumlahPeminjaman = jumlahPeminjaman;
//    }
//}