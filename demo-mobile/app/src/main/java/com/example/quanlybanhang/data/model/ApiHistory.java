package com.example.quanlybanhang.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ApiHistory implements Serializable {

    @SerializedName("id")
    private Integer productID;
    @SerializedName("user_id")
    private Integer userID;
    @SerializedName("created_at")
    private String createAt;
    @SerializedName("updated_at")
    private String updateAt;
    @SerializedName("Address")
    private String addressHistory;
    @SerializedName("Accept")
    private String accept;

    public ApiHistory(Integer productID, Integer userID, String createAt, String updateAt, String addressHistory, String accept) {
        this.productID = productID;
        this.userID = userID;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.addressHistory = addressHistory;
        this.accept = accept;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(String addressHistory) {
        this.addressHistory = addressHistory;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }
}
