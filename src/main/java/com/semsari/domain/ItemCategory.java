package com.semsari.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "item_category")
public class ItemCategory  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false, name = "cat_name")
	@NotEmpty
	private String name;
	private String description;
	
	private String iconCss;

    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<SubCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<SubCategory> categories) {
        this.categories = categories;
    }

    @OneToMany( mappedBy = "parent",fetch = FetchType.EAGER)
    private List<SubCategory> categories;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    private String className;

	public String getDescription() { 
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public String getIconCss() {
		return iconCss;
	}

	public void setIconCss(String iconCss) {
		this.iconCss = iconCss;
	}

}
