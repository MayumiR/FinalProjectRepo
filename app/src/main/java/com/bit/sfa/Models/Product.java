package com.bit.sfa.Models;


public class Product {

    private String FPRODUCT_ID;
    private String FPRODUCT_ITEMCODE;
    private String FPRODUCT_ITEMNAME;
    private String FPRODUCT_PRICE;
    private String FPRODUCT_QOH;
    private String FPRODUCT_QTY;
    private String FPRODUCT_NOUCASE;
    private String FPRODUCT_PACK;
    private String FPRODUCT_FREESCREMA;
    private String SupCode;
    private String FPRODUCT_DEBCODE;
    
    
    
    

    public String getSupCode() {
		return SupCode;
	}

	public void setSupCode(String supCode) {
		SupCode = supCode;
	}

	public String getFPRODUCT_FREESCREMA() {
		return FPRODUCT_FREESCREMA;
	}

	public void setFPRODUCT_FREESCREMA(String fPRODUCT_FREESCREMA) {
		FPRODUCT_FREESCREMA = fPRODUCT_FREESCREMA;
	}

	public String getFPRODUCT_NOUCASE() {
		return FPRODUCT_NOUCASE;
	}

	public void setFPRODUCT_NOUCASE(String fPRODUCT_NOUCASE) {
		FPRODUCT_NOUCASE = fPRODUCT_NOUCASE;
	}

	public String getFPRODUCT_PACK() {
		return FPRODUCT_PACK;
	}

	public void setFPRODUCT_PACK(String fPRODUCT_PACK) {
		FPRODUCT_PACK = fPRODUCT_PACK;
	}

	public String getFPRODUCT_ID() {
        return FPRODUCT_ID;
    }

    public void setFPRODUCT_ID(String FPRODUCT_ID) {
        this.FPRODUCT_ID = FPRODUCT_ID;
    }

    public String getFPRODUCT_ITEMCODE() {
        return FPRODUCT_ITEMCODE;
    }

    public void setFPRODUCT_ITEMCODE(String FPRODUCT_ITEMCODE) {
        this.FPRODUCT_ITEMCODE = FPRODUCT_ITEMCODE;
    }

    public String getFPRODUCT_ITEMNAME() {
        return FPRODUCT_ITEMNAME;
    }

    public void setFPRODUCT_ITEMNAME(String FPRODUCT_ITEMNAME) {
        this.FPRODUCT_ITEMNAME = FPRODUCT_ITEMNAME;
    }

    public String getFPRODUCT_PRICE() {
        return FPRODUCT_PRICE;
    }

    public void setFPRODUCT_PRICE(String FPRODUCT_PRICE) {
        this.FPRODUCT_PRICE = FPRODUCT_PRICE;
    }

    public String getFPRODUCT_QOH() {
        return FPRODUCT_QOH;
    }

    public void setFPRODUCT_QOH(String FPRODUCT_QOH) {
        this.FPRODUCT_QOH = FPRODUCT_QOH;
    }

    public String getFPRODUCT_QTY() {
        return FPRODUCT_QTY;
    }

    public void setFPRODUCT_QTY(String FPRODUCT_QTY) {
        this.FPRODUCT_QTY = FPRODUCT_QTY;
    }

	public String getFPRODUCT_DEBCODE() {
		return FPRODUCT_DEBCODE;
	}

	public void setFPRODUCT_DEBCODE(String fPRODUCT_DEBCODE) {
		FPRODUCT_DEBCODE = fPRODUCT_DEBCODE;
	}
}
