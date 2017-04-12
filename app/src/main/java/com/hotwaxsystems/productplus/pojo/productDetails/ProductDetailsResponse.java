
package com.hotwaxsystems.productplus.pojo.productDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetailsResponse {

    @SerializedName("productDetails")
    @Expose
    private ProductDetails productDetails;

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

}
