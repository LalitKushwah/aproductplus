package com.hotwaxsystems.productplus.pojo.utilities;


        import javax.annotation.Generated;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Login {

    @SerializedName("org.apache.tomcat.util.net.secure_protocol_version")
    @Expose
    private String orgApacheTomcatUtilNetSecureProtocolVersion;
    @SerializedName("sessionId")
    @Expose
    private String sessionId;
    @SerializedName("organizationPartyId")
    @Expose
    private String organizationPartyId;
    @SerializedName("_LOGIN_PASSED_")
    @Expose
    private String lOGINPASSED;
    @SerializedName("authorizeUserResult")
    @Expose
    private AuthorizeUserResult authorizeUserResult;

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
     * The sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     *
     * @param sessionId
     * The sessionId
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     *
     * @return
     * The organizationPartyId
     */
    public String getOrganizationPartyId() {
        return organizationPartyId;
    }

    /**
     *
     * @param organizationPartyId
     * The organizationPartyId
     */
    public void setOrganizationPartyId(String organizationPartyId) {
        this.organizationPartyId = organizationPartyId;
    }

    /**
     *
     * @return
     * The lOGINPASSED
     */
    public String getLOGINPASSED() {
        return lOGINPASSED;
    }

    /**
     *
     * @param lOGINPASSED
     * The _LOGIN_PASSED_
     */
    public void setLOGINPASSED(String lOGINPASSED) {
        this.lOGINPASSED = lOGINPASSED;
    }

    /**
     *
     * @return
     * The authorizeUserResult
     */
    public AuthorizeUserResult getAuthorizeUserResult() {
        return authorizeUserResult;
    }

    /**
     *
     * @param authorizeUserResult
     * The authorizeUserResult
     */
    public void setAuthorizeUserResult(AuthorizeUserResult authorizeUserResult) {
        this.authorizeUserResult = authorizeUserResult;
    }

}
