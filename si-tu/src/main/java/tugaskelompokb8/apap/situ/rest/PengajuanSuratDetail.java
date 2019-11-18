package tugaskelompokb8.apap.situ.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PengajuanSuratDetail {
	@JsonProperty("jenisSurat")
	private String jenisSurat;
	
	@JsonProperty("keterangan")
	private String keterangan;
	
	@JsonProperty("status")
	private Integer status;
	
	@JsonProperty("idUser")
	private String idUser;

	public String getJenisSurat() {
		return jenisSurat;
	}

	public void setJenisSurat(String jenisSurat) {
		this.jenisSurat = jenisSurat;
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

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
}
