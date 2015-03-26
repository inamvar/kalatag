package com.semsari.service;

import com.semsari.dao.DealDao;
import com.semsari.dao.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semsari.domain.Transaction;
import com.semsari.service.CRUDServiceImp;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionServiceImp extends CRUDServiceImp<Transaction> implements TransactionService {


    @Autowired
    TransactionDao tnxDao;

    @Override
    @Transactional
    public Transaction findBy_id_get(String id_get) {
        return tnxDao.findBy_id_get(id_get);
    }
}
