package tugaskelompokb8.apap.situ.model;

import java.util.List;
import java.io.Serializable;

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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="role")
public class RoleModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idRole;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "nama", nullable = false)
	private String nama;
	
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private List<UserModel> userRole;

	

	public List<UserModel> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<UserModel> userRole) {
		this.userRole = userRole;
	}

	public long getIdRole() {
		return idRole;
	}

	public void setIdRole(long idRole) {
		this.idRole = idRole;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}
}
