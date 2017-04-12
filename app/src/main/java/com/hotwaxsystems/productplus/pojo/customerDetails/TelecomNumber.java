package com.hotwaxsystems.productplus.pojo.customerDetails;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TelecomNumber implements Serializable
{

    @SerializedName("shippingPhone")
    @Expose
    private ShippingPhone shippingPhone;
    @SerializedName("billingPhone")
    @Expose
    private BillingPhone billingPhone;
    @SerializedName("primaryPhone")
    @Expose
    private PrimaryPhone primaryPhone;
    private final static long serialVersionUID = 5754976255519075377L;

    public ShippingPhone getShippingPhone() {
        return shippingPhone;
    }

    public void setShippingPhone(ShippingPhone shippingPhone) {
        this.shippingPhone = shippingPhone;
    }

    public BillingPhone getBillingPhone() {
        return billingPhone;
    }

    public void setBillingPhone(BillingPhone billingPhone) {
        this.billingPhone = billingPhone;
    }

    public PrimaryPhone getPrimaryPhone() {
        return primaryPhone;
    }

    public void setPrimaryPhone(PrimaryPhone primaryPhone) {
        this.primaryPhone = primaryPhone;
    }

}
