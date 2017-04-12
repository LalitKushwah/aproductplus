
package com.hotwaxsystems.productplus.pojo.customer;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class CustomerDetailsList {

    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("contactNumber")
    @Expose
    private String contactNumber;
    @SerializedName("partyId")
    @Expose
    private String partyId;
    @SerializedName("emailAddress")
    @Expose
    private String emailAddress;

    /**
     * 
     * @return
     *     The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 
     * @param firstName
     *     The firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 
     * @return
     *     The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 
     * @param lastName
     *     The lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 
     * @return
     *     The contactNumber
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * 
     * @param contactNumber
     *     The contactNumber
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * 
     * @return
     *     The partyId
     */
    public String getPartyId() {
        return partyId;
    }

    /**
     * 
     * @param partyId
     *     The partyId
     */
    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    /**
     *
     * @return
     *     The partyId
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     *
     * @param partyId
     *     The partyId
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
