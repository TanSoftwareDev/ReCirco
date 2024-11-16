package edu.whu.recirco.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 订单信息表
 */
public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String orderId;
    private Integer goodsId;
    private Integer businessId;
    private Integer num;
    private Integer userId;
    private Double price;
    private Integer addressId;
    private String status;

    private List<Cart> cartData;





    public Double getPrice() {
        return price;
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





    public void setPrice(Double price) {
        this.price = price;
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

    public List<Cart> getCartData() {
        return cartData;
    }

    public void setCartData(List<Cart> cartData) {
        this.cartData = cartData;
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

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", userId=" + userId +
                ", businessId=" + businessId +
                ", goodsId=" + goodsId +
                ", num=" + num +
                ", orderId='" + orderId + '\'' +
                ", price=" + price +
                ", addressId=" + addressId +
                ", status='" + status + '\'' +
                '}';
    }





    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
}