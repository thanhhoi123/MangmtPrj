package com.example.quanlybanhang.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ApiDetail implements Serializable {
    @SerializedName("IDDH")
    private Integer IDDH;
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    @SerializedName("amount")
    private Integer amount;
    @SerializedName("Price")
    private String Price;
    @SerializedName("description")
    private String description;
    @SerializedName("img_link")
    private String img_link;


    public ApiDetail(Integer IDDH, Integer id, String name, String created_at, String updated_at, Integer amount, String price, String description, String img_link) {
        this.IDDH = IDDH;
        this.id = id;
        this.name = name;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.amount = amount;
        Price = price;
        this.description = description;
        this.img_link = img_link;

    }

    public Integer getIDDH() {
        return IDDH;
    }

    public void setIDDH(Integer IDDH) {
        this.IDDH = IDDH;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getImg_link() {
        return img_link;
    }

    public void setImg_link(String img_link) {
        this.img_link = img_link;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
