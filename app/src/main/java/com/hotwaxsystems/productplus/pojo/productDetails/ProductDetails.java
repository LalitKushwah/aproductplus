package com.hotwaxsystems.productplus.pojo.productDetails;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetails implements Serializable
{

    @SerializedName("productId")
    @Expose
    private String productId;
    @SerializedName("upc")
    @Expose
    private String upc;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("currencyUom")
    @Expose
    private String currencyUom;
    @SerializedName("imageURL")
    @Expose
    private String imageURL;
    @SerializedName("recommondedProducts")
    @Expose
    private List<RecommondedProduct> recommondedProducts = null;
    private final static long serialVersionUID = 8149452460806969910L;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrencyUom() {
        return currencyUom;
    }

    public void setCurrencyUom(String currencyUom) {
        this.currencyUom = currencyUom;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public List<RecommondedProduct> getRecommondedProducts() {
        return recommondedProducts;
    }

    public void setRecommondedProducts(List<RecommondedProduct> recommondedProducts) {
        this.recommondedProducts = recommondedProducts;
    }

}
