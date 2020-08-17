package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "members")
@NamedQueries({
	@NamedQuery(
			name = "getAllMembers",
			query = "SELECT m FROM Member AS m ORDER BY m.id DESC"
			),
	@NamedQuery(
			name = "getMembersCount",
			query = "SELECT COUNT(m) FROM Member AS m"
			)
})
@Entity
public class Member {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "phone_number", nullable = false)
	private String phone_number;

	@Column(name = "email_address", nullable = false)
	private String email_address;

	@Column(name = "password", length = 64, nullable = false)
	private String password;

	@Column(name = "admin_flag", nullable = false)
	private Integer admin_flag;

	@Column(name = "created_at", nullable = false)
	private Timestamp created_at;

	@Column(name = "updated_at", nullable = false)
	private Timestamp updated_at;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAdmin_flag() {
		return admin_flag;
	}

	public void setAdmin_flag(Integer admin_flag) {
		this.admin_flag = admin_flag;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

}

