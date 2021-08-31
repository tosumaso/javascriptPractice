package practice.example.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;

	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "categories") // mappedBy: 双方向の関係性を表し、2つのentityの関係(entity1 to entity2、entity2 to entity1)を１つにまとめる
	private List<Item> items = new ArrayList<>(); //初期化されていない状態でゲッターが呼び出させてもぬるぽにならないように空のListで初期化しておく

	public Category() {
	
	}
	
	public Category(String name) {
		this.name = name;
	}

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

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	
}
