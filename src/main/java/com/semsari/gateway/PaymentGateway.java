package com.semsari.gateway;

import com.semsari.domain.Transaction;

import java.io.IOException;

public interface PaymentGateway {



    String send() throws IOException;
    String verify(String id_get, String trans_id);
    void setTrx(Transaction txn);

}
