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

@Entity
@Table(name="lowongan")
public class LowonganModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLowongan;
	
	@NotNull
	@Size(max = 200)
	@Column(name="judul", nullable = false)
	private String judul;
	
	@NotNull
	@Column(name = "tanggal_dibuka", columnDefinition = "DATE", nullable = false)
	private Date tanggalDibuka;
	
	@NotNull
	@Column(name = "tanggal_ditutup", columnDefinition = "DATE", nullable = false)
	private Date tanggalDitutup;
	
	@NotNull
	@Size(max = 200)
	@Column(name="keterangan", nullable = false)
	private String keterangan;
	
	@NotNull
	@Column(name="jumlah", nullable = false)
	private Integer jumlah;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="id_jenis_lowongan", referencedColumnName = "idJenisLowongan", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private JenisLowonganModel jenisLowongan;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="uuid_user", referencedColumnName = "idUser", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private UserModel user;
	
	public Long getIdLowongan() {
		return idLowongan;
	}

	public void setIdLowongan(Long idLowongan) {
		this.idLowongan = idLowongan;
	}

	public String getJudul() {
		return judul;
	}

	public void setJudul(String judul) {
		this.judul = judul;
	}

	public Date getTanggalDibuka() {
		return tanggalDibuka;
	}

	public void setTanggalDibuka(Date tanggalDibuka) {
		this.tanggalDibuka = tanggalDibuka;
	}

	public Date getTanggalDitutup() {
		return tanggalDitutup;
	}

	public void setTanggalDitutup(Date tanggalDitutup) {
		this.tanggalDitutup = tanggalDitutup;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public Integer getJumlah() {
		return jumlah;
	}

	public void setJumlah(Integer jumlah) {
		this.jumlah = jumlah;
	}

	public JenisLowonganModel getJenisLowongan() {
		return jenisLowongan;
	}

	public void setJenisLowongan(JenisLowonganModel jenisLowongan) {
		this.jenisLowongan = jenisLowongan;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}
}
