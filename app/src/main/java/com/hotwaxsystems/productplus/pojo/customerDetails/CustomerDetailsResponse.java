package com.hotwaxsystems.productplus.pojo.customerDetails;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerDetailsResponse implements Serializable
{

    @SerializedName("customerDetail")
    @Expose
    private CustomerDetail customerDetail;
    @SerializedName("customerId")
    @Expose
    private String customerId;
    @SerializedName("sessionId")
    @Expose
    private String sessionId;
    @SerializedName("productStoreId")
    @Expose
    private String productStoreId;
    @SerializedName("_LOGIN_PASSED_")
    @Expose
    private String lOGINPASSED;
    private final static long serialVersionUID = 2550347928107613205L;

    public CustomerDetail getCustomerDetail() {
        return customerDetail;
    }

    public void setCustomerDetail(CustomerDetail customerDetail) {
        this.customerDetail = customerDetail;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getProductStoreId() {
        return productStoreId;
    }

    public void setProductStoreId(String productStoreId) {
        this.productStoreId = productStoreId;
    }

    public String getLOGINPASSED() {
        return lOGINPASSED;
    }

    public void setLOGINPASSED(String lOGINPASSED) {
        this.lOGINPASSED = lOGINPASSED;
    }

}
