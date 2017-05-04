package com.hotwaxsystems.productplus.pojo.customerDetails;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmailAddressInfo implements Serializable
{

    @SerializedName("emailAddress")
    @Expose
    private String emailAddress;
    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("contactMechId")
    @Expose
    private String contactMechId;
    private final static long serialVersionUID = -3885100917068865197L;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
