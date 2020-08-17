package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "categories")
@NamedQueries({
	@NamedQuery(
			name = "getAllCategories",
			query = "SELECT c FROM Category AS c ORDER BY c.id DESC"
			),
	@NamedQuery(
			name = "getCategoriesCount",
			query = "SELECT COUNT(c) FROM Category AS c"
			)
})
@Entity
public class Category {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

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
}
