package com.semsari.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Set;

@Entity
public class Customer extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "contact_id", nullable = true )
	@Valid
	private Contact contact;

    @Lob
    private String description;


    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @OneToMany(targetEntity = Item.class, mappedBy = "customer")
    @JsonIgnore
    private Set<Item> items;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
