
package com.hotwaxsystems.productplus.pojo.customerDetails;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrimaryAddress implements Serializable
{

    @SerializedName("countryGeoId")
    @Expose
    private String countryGeoId;
    @SerializedName("contactMechPurposeTypeId")
    @Expose
    private String contactMechPurposeTypeId;
    @SerializedName("address2")
    @Expose
    private String address2;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("address1")
    @Expose
    private String address1;
    @SerializedName("postalCode")
    @Expose
    private String postalCode;
    @SerializedName("stateProvinceGeoId")
    @Expose
    private String stateProvinceGeoId;
    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("contactMechId")
    @Expose
    private String contactMechId;
    private final static long serialVersionUID = 9115627689699373627L;

    public String getCountryGeoId() {
        return countryGeoId;
    }

    public void setCountryGeoId(String countryGeoId) {
        this.countryGeoId = countryGeoId;
    }

    public String getContactMechPurposeTypeId() {
        return contactMechPurposeTypeId;
    }

    public void setContactMechPurposeTypeId(String contactMechPurposeTypeId) {
        this.contactMechPurposeTypeId = contactMechPurposeTypeId;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStateProvinceGeoId() {
        return stateProvinceGeoId;
    }

    public void setStateProvinceGeoId(String stateProvinceGeoId) {
        this.stateProvinceGeoId = stateProvinceGeoId;
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
