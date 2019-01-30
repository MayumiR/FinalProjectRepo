package com.bit.sfa.Models;

public class ItemSearchList {

	private String FITEM_ITEM_CODE;
	private String FITEM_ITEM_NAME;
	private String FITEM_NOU_CASE;
	private String FITEM_PACK;
	private String QOH;

	public ItemSearchList(String fITEM_ITEM_NAME){
		setFITEM_ITEM_NAME(FITEM_ITEM_NAME);
	}
	
	public String getFITEM_ITEM_CODE() {
		return FITEM_ITEM_CODE;
	}
	public void setFITEM_ITEM_CODE(String fITEM_ITEM_CODE) {
		FITEM_ITEM_CODE = fITEM_ITEM_CODE;
	}
	public String getFITEM_ITEM_NAME() {
		return FITEM_ITEM_NAME;
	}
	public void setFITEM_ITEM_NAME(String fITEM_ITEM_NAME) {
		FITEM_ITEM_NAME = fITEM_ITEM_NAME;
	}
	public String getFITEM_NOU_CASE() {
		return FITEM_NOU_CASE;
	}
	public void setFITEM_NOU_CASE(String fITEM_NOU_CASE) {
		FITEM_NOU_CASE = fITEM_NOU_CASE;
	}
	public String getQOH() {
		return QOH;
	}
	public void setQOH(String qOH) {
		QOH = qOH;
	}
	public String getFITEM_PACK() {
		return FITEM_PACK;
	}
	public void setFITEM_PACK(String fITEM_PACK) {
		FITEM_PACK = fITEM_PACK;
	}
	
	
}
