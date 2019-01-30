package com.bit.sfa.Models;

/**
 * Created by Sathiyaraja on 7/26/2018.
 */

public class Fmissdet {
    public String RefNo;
    public String TxnDate;
    public String TxnType;
    public String SeqNo;
    public String GItemCode;
    public String Qty;
    public String CostPrice;
    public String Amt;

    public String getRefNo() {
        return RefNo;
    }

    public void setRefNo(String refNo) {
        RefNo = refNo;
    }

    public String getTxnDate() {
        return TxnDate;
    }

    public void setTxnDate(String txnDate) {
        TxnDate = txnDate;
    }

    public String getTxnType() {
        return TxnType;
    }

    public void setTxnType(String txnType) {
        TxnType = txnType;
    }

    public String getSeqNo() {
        return SeqNo;
    }

    public void setSeqNo(String seqNo) {
        SeqNo = seqNo;
    }

    public String getGItemCode() {
        return GItemCode;
    }

    public void setGItemCode(String GItemCode) {
        this.GItemCode = GItemCode;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getCostPrice() {
        return CostPrice;
    }

    public void setCostPrice(String costPrice) {
        CostPrice = costPrice;
    }

    public String getAmt() {
        return Amt;
    }

    public void setAmt(String amt) {
        Amt = amt;
    }
}
