package com.hotwaxsystems.productplus.pojo.customerDetails;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShippingPhone implements Serializable
{

    @SerializedName("areaCode")
    @Expose
    private String areaCode;
    @SerializedName("contactMechPurposeTypeId")
    @Expose
    private String contactMechPurposeTypeId;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("contactNumber")
    @Expose
    private String contactNumber;
    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("contactMechId")
    @Expose
    private String contactMechId;
    private final static long serialVersionUID = 1156240816495366587L;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getContactMechPurposeTypeId() {
        return contactMechPurposeTypeId;
    }

    public void setContactMechPurposeTypeId(String contactMechPurposeTypeId) {
        this.contactMechPurposeTypeId = contactMechPurposeTypeId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getContactMechId() {
        return contactMechId;
    }

    public void setContactMechId(String contactMechId) {
        this.contactMechId = contactMechId;
    }

}
