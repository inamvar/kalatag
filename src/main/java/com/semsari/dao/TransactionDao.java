package com.semsari.dao;
import com.semsari.domain.Transaction;
public interface TransactionDao extends GenericDao<Transaction> {

    Transaction findBy_id_get(String id_get);
}
