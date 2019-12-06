package tugaskelompokb8.apap.situ.rest;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserDetail {
	
	@JsonProperty("idUser")
	public String idUser;
	
	@JsonProperty("nip")
	public String nip;
	
	@JsonProperty("nama")
	public String nama;
	
	@JsonProperty("tempatLahir")
	public Date tempatLahir;
	
	@JsonProperty("tanggalLahir")
	public Date tanggalLahir;
	
	@JsonProperty("alamat")
	public String alamat;
	
	@JsonProperty("telepon")
	public String telepon;

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Date getTempatLahir() {
		return tempatLahir;
	}

	public void setTempatLahir(Date tempatLahir) {
		this.tempatLahir = tempatLahir;
	}

	public Date getTanggalLahir() {
		return tanggalLahir;
	}

	public void setTanggalLahir(Date tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public String getTelepon() {
		return telepon;
	}

	public void setTelepon(String telepon) {
		this.telepon = telepon;
	}
}
