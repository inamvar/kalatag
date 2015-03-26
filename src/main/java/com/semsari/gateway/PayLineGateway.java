package com.semsari.gateway;

import com.semsari.domain.Transaction;
import com.semsari.util.Util;

import java.io.IOException;

/**
 * Created by Iman on 3/22/2015.
 */
public class PayLineGateway implements PaymentGateway {

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public Transaction getTxn() {
        return txn;
    }

    public void setTxn(Transaction txn) {
        this.txn = txn;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getVerifyUrl() {
        return verifyUrl;
    }

    public void setVerifyUrl(String verifyUrl) {
        this.verifyUrl = verifyUrl;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    private String requestUrl;
    private String paymentUrl;
    private String verifyUrl;
    private String redirect;
    private Transaction txn;
    private String api;

    @Override
    public String send() throws IOException {

        String params = "api=" + this.api + "&amount=" + this.txn.getAmount() + "&redirect=" + redirect ;
        return Util.excutePost(this.requestUrl,params);
    }

    @Override
    public String verify(String id_get, String trans_id) {
        this.txn = txn;
        String params = "api=" + this.api + "&id_get=" +id_get + "&trans_id=" + trans_id ;
        String response = Util.excutePost(this.verifyUrl,params);
        return  response;
    }

    @Override
    public void setTrx(Transaction txn) {
        this.txn = txn;
    }


}
