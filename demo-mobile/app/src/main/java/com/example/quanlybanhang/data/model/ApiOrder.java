package com.example.quanlybanhang.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ApiOrder implements Serializable {
    @SerializedName("user_id")
    private Integer userID;
    @SerializedName("address")
    private String AddressOrder;
    @SerializedName("accept")
    private Integer Accept;
    @SerializedName("IDSanPham")
    private Integer idOrder;
    @SerializedName("SLSanPham")
    private Integer amountOrder;

    public ApiOrder(Integer userID, String addressOrder, Integer accept, Integer idOrder, Integer amountOrder) {
        this.userID = userID;
        AddressOrder = addressOrder;
        Accept = accept;
        this.idOrder = idOrder;
        this.amountOrder = amountOrder;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getAddressOrder() {
        return AddressOrder;
    }

    public void setAddressOrder(String addressOrder) {
        AddressOrder = addressOrder;
    }

    public Integer getAccept() {
        return Accept;
    }

    public void setAccept(Integer accept) {
        Accept = accept;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getAmountOrder() {
        return amountOrder;
    }

    public void setAmountOrder(Integer amountOrder) {
        this.amountOrder = amountOrder;
    }
}
