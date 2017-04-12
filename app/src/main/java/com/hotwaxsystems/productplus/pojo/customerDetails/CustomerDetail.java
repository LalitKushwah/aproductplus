package com.hotwaxsystems.productplus.pojo.customerDetails;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerDetail implements Serializable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("emailAddressInfo")
    @Expose
    private EmailAddressInfo emailAddressInfo;
    @SerializedName("telecomNumber")
    @Expose
    private TelecomNumber telecomNumber;
    @SerializedName("primaryAddress")
    @Expose
    private PrimaryAddress primaryAddress;
    @SerializedName("birthDate")
    @Expose
    private Object birthDate;
    @SerializedName("customerOrders")
    @Expose
    private List<Object> customerOrders = null;
    @SerializedName("favoriteProducts")
    @Expose
    private List<FavoriteProduct> favoriteProducts = null;
    @SerializedName("favoriteCategories")
    @Expose
    private List<String> favoriteCategories = null;
    @SerializedName("shoppingListId")
    @Expose
    private String shoppingListId;
    private final static long serialVersionUID = 8851891327241911349L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmailAddressInfo getEmailAddressInfo() {
        return emailAddressInfo;
    }

    public void setEmailAddressInfo(EmailAddressInfo emailAddressInfo) {
        this.emailAddressInfo = emailAddressInfo;
    }

    public TelecomNumber getTelecomNumber() {
        return telecomNumber;
    }

    public void setTelecomNumber(TelecomNumber telecomNumber) {
        this.telecomNumber = telecomNumber;
    }

    public PrimaryAddress getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(PrimaryAddress primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public Object getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Object birthDate) {
        this.birthDate = birthDate;
    }

    public List<Object> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<Object> customerOrders) {
        this.customerOrders = customerOrders;
    }

    public List<FavoriteProduct> getFavoriteProducts() {
        return favoriteProducts;
    }

    public void setFavoriteProducts(List<FavoriteProduct> favoriteProducts) {
        this.favoriteProducts = favoriteProducts;
    }

    public List<String> getFavoriteCategories() {
        return favoriteCategories;
    }

    public void setFavoriteCategories(List<String> favoriteCategories) {
        this.favoriteCategories = favoriteCategories;
    }

    public String getShoppingListId() {
        return shoppingListId;
    }

    public void setShoppingListId(String shoppingListId) {
        this.shoppingListId = shoppingListId;
    }

}
