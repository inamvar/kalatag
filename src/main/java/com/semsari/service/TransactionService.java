package com.semsari.service;

import com.semsari.domain.Transaction;
import com.semsari.service.CRUDService;

public interface TransactionService extends CRUDService<Transaction>{
    Transaction findBy_id_get(String id_get);
}
