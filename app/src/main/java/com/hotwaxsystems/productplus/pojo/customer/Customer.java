package com.hotwaxsystems.productplus.pojo.customer;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Customer {

    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("partyId")
    @Expose
    private String partyId;

    /**
     *
     * @return
     * The customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     *
     * @param customerName
     * The customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     *
     * @return
     * The partyId
     */
    public String getPartyId() {
        return partyId;
    }

    /**
     *
     * @param partyId
     * The partyId
     */
    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

}
