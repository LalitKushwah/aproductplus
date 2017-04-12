
package com.hotwaxsystems.productplus.pojo.productDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderItemPriceInfo {

    @SerializedName("productPriceActionSeqId")
    @Expose
    private String productPriceActionSeqId;
    @SerializedName("modifyAmount")
    @Expose
    private Double modifyAmount;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("productPriceRuleId")
    @Expose
    private String productPriceRuleId;
    @SerializedName("rateCode")
    @Expose
    private Object rateCode;

    public String getProductPriceActionSeqId() {
        return productPriceActionSeqId;
    }

    public void setProductPriceActionSeqId(String productPriceActionSeqId) {
        this.productPriceActionSeqId = productPriceActionSeqId;
    }

    public Double getModifyAmount() {
        return modifyAmount;
    }

    public void setModifyAmount(Double modifyAmount) {
        this.modifyAmount = modifyAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductPriceRuleId() {
        return productPriceRuleId;
    }

    public void setProductPriceRuleId(String productPriceRuleId) {
        this.productPriceRuleId = productPriceRuleId;
    }

    public Object getRateCode() {
        return rateCode;
    }

    public void setRateCode(Object rateCode) {
        this.rateCode = rateCode;
    }

}
