package tugaskelompokb8.apap.situ.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;



@Entity
@Table(name="pengajuan_surat")
public class PengajuanSuratModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPengajuanSurat;
	
	@NotNull
	@Size(max = 200)
	@Column(name="nomor_surat", nullable = false)
	private String nomorSurat;
	
	@NotNull
	@Column(name = "tanggal_pengajuan", columnDefinition = "DATE", nullable = false)
	private Date tanggalPengajuan;

	@Column(name = "tanggal_disetujui", columnDefinition = "DATE")
	private Date tanggalDisetujui;
	
	@NotNull
	@Size(max = 200)
	@Column(name="keterangan", nullable = false)
	private String keterangan;
	
	@NotNull
	@Column(name="status", nullable = false)
	private Integer status;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="id_jenis_surat", referencedColumnName = "idJenisSurat", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private JenisSuratModel jenisSurat;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="uuid_user", referencedColumnName = "idUser", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private UserModel user;

	public Long getIdPengajuanSurat() {
		return idPengajuanSurat;
	}

	public void setIdPengajuanSurat(Long idPengajuanSurat) {
		this.idPengajuanSurat = idPengajuanSurat;
	}

	public String getNomorSurat() {
		return nomorSurat;
	}

	public void setNomorSurat(String nomorSurat) {
		this.nomorSurat = nomorSurat;
	}

	public Date getTanggalPengajuan() {
		return tanggalPengajuan;
	}

	public void setTanggalPengajuan(Date tanggalPengajuan) {
		this.tanggalPengajuan = tanggalPengajuan;
	}

	public Date getTanggalDisetujui() {
		return tanggalDisetujui;
	}

	public void setTanggalDisetujui(Date tanggalDisetujui) {
		this.tanggalDisetujui = tanggalDisetujui;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public JenisSuratModel getJenisSurat() {
		return jenisSurat;
	}

	public void setJenisSurat(JenisSuratModel jenisSurat) {
		this.jenisSurat = jenisSurat;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}
}
