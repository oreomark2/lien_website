package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "histories")
@NamedQueries({
	@NamedQuery(
			name = "getAllHistories",
			query = "SELECT h FROM History AS h WHERE h.member = :member ORDER BY h.id DESC"
			),
	@NamedQuery(
			name = "getHistoriesCount",
			query = "SELECT COUNT(h) FROM History AS h WHERE h.member = :member "
			)
})
@Entity
public class History {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Column(name = "purchased_at", nullable = false)
	private Timestamp purchased_at;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Timestamp getPurchased_at() {
		return purchased_at;
	}

	public void setPurchased_at(Timestamp purchased_at) {
		this.purchased_at = purchased_at;
	}
}
