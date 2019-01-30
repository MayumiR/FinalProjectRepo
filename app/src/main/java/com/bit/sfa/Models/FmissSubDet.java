package com.bit.sfa.Models;

/**
 * Created by Sathiyaraja on 7/26/2018.
 */

public class FmissSubDet {
    public String Refn;
    public String ItemCode;
    public String GItemCode;
    public String CostPrice;
    public String Qty;
    public String Amt;
    public String SeqNo;
    public String RecordId;

    public String getRefn() {
        return Refn;
    }

    public void setRefn(String refn) {
        Refn = refn;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getGItemCode() {
        return GItemCode;
    }

    public void setGItemCode(String GItemCode) {
        this.GItemCode = GItemCode;
    }

    public String getCostPrice() {
        return CostPrice;
    }

    public void setCostPrice(String costPrice) {
        CostPrice = costPrice;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getAmt() {
        return Amt;
    }

    public void setAmt(String amt) {
        Amt = amt;
    }

    public String getSeqNo() {
        return SeqNo;
    }

    public void setSeqNo(String seqNo) {
        SeqNo = seqNo;
    }

    public String getRecordId() {
        return RecordId;
    }

    public void setRecordId(String recordId) {
        RecordId = recordId;
    }
}
