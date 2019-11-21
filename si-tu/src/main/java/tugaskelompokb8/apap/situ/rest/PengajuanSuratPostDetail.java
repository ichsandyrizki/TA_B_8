package tugaskelompokb8.apap.situ.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PengajuanSuratPostDetail {
	
//	@JsonProperty("jenisSurat")
	private Long jenisSurat;
	
//	@JsonProperty("keterangan")
	private String keterangan;
	
//	@JsonProperty("idUser")
//	private String idUser;

	public Long getJenisSurat() {
		return jenisSurat;
	}

	public void setJenisSurat(Long jenisSurat) {
		this.jenisSurat = jenisSurat;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

//	public String getIdUser() {
//		return idUser;
//	}
//
//	public void setIdUser(String idUser) {
//		this.idUser = idUser;
//	}
}
