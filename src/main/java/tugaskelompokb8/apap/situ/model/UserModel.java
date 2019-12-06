package tugaskelompokb8.apap.situ.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user")
public class UserModel implements Serializable{
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String idUser;

	@NotNull
	@Size(max=50)
	@Column(name="username", nullable = false)
	private String username;

	@NotNull
	@Lob
	@Column(name="password", nullable = false)
	private String password;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_role", referencedColumnName = "idRole", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private RoleModel role;

	@OneToMany(mappedBy = "user", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<PengajuanSuratModel> listPengajuanSurat;

	@OneToMany(mappedBy = "user", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<LowonganModel> listLowongan;

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleModel getRole() {
		return role;
	}

	public void setRole(RoleModel role) {
		this.role = role;
	}

	public List<PengajuanSuratModel> getListPengajuanSurat() {
		return listPengajuanSurat;
	}

	public void setListPengajuanSurat(List<PengajuanSuratModel> listPengajuanSurat) {
		this.listPengajuanSurat = listPengajuanSurat;
	}

	public List<LowonganModel> getListLowongan() {
		return listLowongan;
	}

	public void setListLowongan(List<LowonganModel> listLowongan) {
		this.listLowongan = listLowongan;
	}
}

