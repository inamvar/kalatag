package com.semsari.dao;

import com.semsari.domain.Item;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.semsari.domain.Transaction;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionDaoImp extends GenericDaoImp<Transaction> implements TransactionDao{


    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Transaction findBy_id_get(String id_get) {

       Transaction tnx = new Transaction();
        String hql = "from Transaction T WHERE T.id_get = :id_get";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("id_get", id_get);
        tnx =(Transaction) query.uniqueResult();

        return tnx;

    }
}
