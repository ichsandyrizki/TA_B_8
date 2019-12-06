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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="jenis_surat")
public class JenisSuratModel implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idJenisSurat;
	
	@NotNull
	@Size(max = 200)
	@Column(name="nama", nullable = false)
	private String nama;
	
	@NotNull
	@Size(max = 200)
	@Column(name="keterangan", nullable = false)
	private String keterangan;
	
	@OneToMany(mappedBy = "jenisSurat", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<PengajuanSuratModel> listPengajuanSurat;

	public Long getIdJenisSurat() {
		return idJenisSurat;
	}

	public void setIdJenisSurat(Long idJenisSurat) {
		this.idJenisSurat = idJenisSurat;
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

	public List<PengajuanSuratModel> getListPengajuanSurat() {
		return listPengajuanSurat;
	}

	public void setListPengajuanSurat(List<PengajuanSuratModel> listPengajuanSurat) {
		this.listPengajuanSurat = listPengajuanSurat;
	}
}
