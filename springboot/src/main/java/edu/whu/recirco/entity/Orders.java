package edu.whu.recirco.entity;

import java.io.Serializable;

/**
 * 订单信息表
 */
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer orderId;
    private Integer goodsId;
    private Integer businessId;
    private Integer num;
    private Integer userId;
    private Double price;
    private Integer address_id;
    private String status;

    public Integer getOrderId() {
        return orderId;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getAddress_id() {
        return address_id;
    }

    public String getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }


    private String businessName;
    private String goodsImg;
    private String goodsName;
    private String goodsUnit;
    private Double goodsprice;
    private String username;



    private String useraddress;
    private String phone;

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public Double getGoodsprice() {
        return goodsprice;
    }

    public void setGoodsprice(Double goodsprice) {
        this.goodsprice = goodsprice;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}