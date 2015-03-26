package com.semsari.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Item implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotEmpty
	private String name;
	private double price;
	@JsonIgnore
	private ItemStatus status;

    public ItemPeriod getPeriod() {
        return period;
    }

    public void setPeriod(ItemPeriod period) {
        this.period = period;
    }

    @JsonIgnore
    private ItemPeriod period;

	private Rate rate;
	@Lob
	private String features;
	@Lob
	private String termsOfUse;
	@Lob
	private String description;

    private String firstName;
    private String lastName;
    private String phone;
    private String mobile;
    private String email;
    @Lob
    private String address;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    private int visits;


	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date validity;


    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date registerDate;

	@JsonIgnore
	private DealLabel label;

	@ManyToOne
	@JoinColumn(name = "cat_id", nullable = false)
	@JsonIgnore
	private SubCategory category;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	
	@Column(length = 10000000)
	@JsonIgnore
	private byte[] thumbnail;



    @Column(length = 10000000)
    @JsonIgnore
    private byte[] image1;

    @Column(length = 10000000)
    @JsonIgnore
    private byte[] image2;


    @Column(length = 10000000)
    @JsonIgnore
    private byte[] image3;

    @Column(length = 10000000)
    @JsonIgnore
    private byte[] image4;

	private String[] tags;



	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public SubCategory getCategory() {
		return category;
	}

	public void setCategory(SubCategory category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ItemStatus getStatus() {
		return status;
	}

	public void setStatus(ItemStatus status) {
		this.status = status;
	}

	public byte[] getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}



	public Rate getRate() {
		return rate;
	}

	public void setRate(Rate rate) {
		this.rate = rate;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getTermsOfUse() {
		return termsOfUse;
	}

	public void setTermsOfUse(String termsOfUse) {
		this.termsOfUse = termsOfUse;
	}



	public Date getValidity() {
		return validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}


	public DealLabel getLabel() {
		return label;
	}

	public void setLabel(DealLabel label) {
		this.label = label;
	}

    public byte[] getImage1() {
        return image1;
    }

    public void setImage1(byte[] image1) {
        this.image1 = image1;
    }

    public byte[] getImage2() {
        return image2;
    }

    public void setImage2(byte[] image2) {
        this.image2 = image2;
    }

    public byte[] getImage3() {
        return image3;
    }

    public void setImage3(byte[] image3) {
        this.image3 = image3;
    }

    public byte[] getImage4() {
        return image4;
    }

    public void setImage4(byte[] image4) {
        this.image4 = image4;
    }


    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    private String merchant;


}
