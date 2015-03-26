package com.semsari.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Iman on 3/20/2015.
 */
@Entity
@Table(name="sub_category")
public class SubCategory  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    private String name;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    private long count;



    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private ItemCategory parent;


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @OneToMany( mappedBy = "category")
    private List<Item> items;

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

    public ItemCategory getParent() {
        return parent;
    }

    public void setParent(ItemCategory parent) {
        this.parent = parent;
    }


}
