package com.semsari.dao;

import com.semsari.domain.Item;
import com.semsari.domain.ItemStatus;
import com.semsari.domain.SubCategory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.semsari.domain.ItemCategory;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemCategoryDaoImp extends GenericDaoImp<ItemCategory> implements ItemCategoryDao{


    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<ItemCategory> findAll(){
        List<ItemCategory> categories;
        categories = super.findAll();
        for(ItemCategory category: categories){
            for(SubCategory sub: category.getCategories()){
             long  result = 0;
                result =(Long) sessionFactory.getCurrentSession().createCriteria(Item.class, "D").add(Restrictions.eq("D.category", sub)).add(Restrictions.eq("D.status", ItemStatus.ON)).setProjection(Projections.rowCount()).uniqueResult();
                sub.setCount(result);
            }
        }
        return categories;
    }

}
