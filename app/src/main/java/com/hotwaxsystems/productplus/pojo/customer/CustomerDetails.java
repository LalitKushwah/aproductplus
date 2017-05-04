package com.hotwaxsystems.productplus.pojo.customer;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class CustomerDetails {

    @SerializedName("org.apache.tomcat.util.net.secure_protocol_version")
    @Expose
    private String orgApacheTomcatUtilNetSecureProtocolVersion;
    @SerializedName("customer")
    @Expose
        private Customer customer;


    /**
     *
     * @return
     * The orgApacheTomcatUtilNetSecureProtocolVersion
     */
    public String getOrgApacheTomcatUtilNetSecureProtocolVersion() {
        return orgApacheTomcatUtilNetSecureProtocolVersion;
    }

    /**
     *
     * @param orgApacheTomcatUtilNetSecureProtocolVersion
     * The org.apache.tomcat.util.net.secure_protocol_version
     */
    public void setOrgApacheTomcatUtilNetSecureProtocolVersion(String orgApacheTomcatUtilNetSecureProtocolVersion) {
        this.orgApacheTomcatUtilNetSecureProtocolVersion = orgApacheTomcatUtilNetSecureProtocolVersion;
    }

    /**
     *
     * @return
     * The customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     *
     * @param customer
     * The customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
