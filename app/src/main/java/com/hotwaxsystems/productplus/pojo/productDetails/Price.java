
package com.hotwaxsystems.productplus.pojo.productDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Price {

    @SerializedName("defaultPrice")
    @Expose
    private String defaultPrice;
    @SerializedName("margin")
    @Expose
    private String margin;
    @SerializedName("currencyUsed")
    @Expose
    private String currencyUsed;
    @SerializedName("specialPromoPrice")
    @Expose
    private Object specialPromoPrice;
    @SerializedName("validPriceFound")
    @Expose
    private Boolean validPriceFound;
    @SerializedName("competitivePrice")
    @Expose
    private Object competitivePrice;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("orderItemPriceInfos")
    @Expose
    private List<OrderItemPriceInfo> orderItemPriceInfos = null;
    @SerializedName("isSale")
    @Expose
    private Boolean isSale;
    @SerializedName("averageCost")
    @Expose
    private String averageCost;
    @SerializedName("wholesalePrice")
    @Expose
    private String wholesalePrice;
    @SerializedName("listPrice")
    @Expose
    private String listPrice;
    @SerializedName("basePrice")
    @Expose
    private String basePrice;

    public String getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(String defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public String getCurrencyUsed() {
        return currencyUsed;
    }

    public void setCurrencyUsed(String currencyUsed) {
        this.currencyUsed = currencyUsed;
    }

    public Object getSpecialPromoPrice() {
        return specialPromoPrice;
    }

    public void setSpecialPromoPrice(Object specialPromoPrice) {
        this.specialPromoPrice = specialPromoPrice;
    }

    public Boolean getValidPriceFound() {
        return validPriceFound;
    }

    public void setValidPriceFound(Boolean validPriceFound) {
        this.validPriceFound = validPriceFound;
    }

    public Object getCompetitivePrice() {
        return competitivePrice;
    }

    public void setCompetitivePrice(Object competitivePrice) {
        this.competitivePrice = competitivePrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<OrderItemPriceInfo> getOrderItemPriceInfos() {
        return orderItemPriceInfos;
    }

    public void setOrderItemPriceInfos(List<OrderItemPriceInfo> orderItemPriceInfos) {
        this.orderItemPriceInfos = orderItemPriceInfos;
    }

    public Boolean getIsSale() {
        return isSale;
    }

    public void setIsSale(Boolean isSale) {
        this.isSale = isSale;
    }

    public String getAverageCost() {
        return averageCost;
    }

    public void setAverageCost(String averageCost) {
        this.averageCost = averageCost;
    }

    public String getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(String wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public String getListPrice() {
        return listPrice;
    }

    public void setListPrice(String listPrice) {
        this.listPrice = listPrice;
    }

    public String getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }

}
