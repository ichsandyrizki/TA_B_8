package tugaskelompokb8.apap.situ.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="jenis_lowongan")
public class JenisLowonganModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idJenisLowongan;
	
	@NotNull
	@Size(max = 200)
	@Column(name="nama", nullable = false)
	private String nama;
	
	@NotNull
	@Size(max = 200)
	@Column(name="keterangan", nullable = false)
	private String keterangan;
	
	@OneToMany(mappedBy = "jenisLowongan", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<LowonganModel> listLowongan;

	public Long getIdJenisLowongan() {
		return idJenisLowongan;
	}

	public void setIdJenisLowongan(Long idJenisLowongan) {
		this.idJenisLowongan = idJenisLowongan;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public List<LowonganModel> getListLowongan() {
		return listLowongan;
	}

	public void setListLowongan(List<LowonganModel> listLowongan) {
		this.listLowongan = listLowongan;
	}
}
