package com.hotwaxsystems.productplus.pojo.utilities;

        import javax.annotation.Generated;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class AuthorizeUserResult {

    @SerializedName("userLoginId")
    @Expose
    private String userLoginId;
    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("partyId")
    @Expose
    private String partyId;
    @SerializedName("token")
    @Expose
    private String token;

    /**
     *
     * @return
     * The userLoginId
     */
    public String getUserLoginId() {
        return userLoginId;
    }

    /**
     *
     * @param userLoginId
     * The userLoginId
     */
    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    /**
     *
     * @return
     * The responseMessage
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     *
     * @param responseMessage
     * The responseMessage
     */
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
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

    /**
     *
     * @return
     * The token
     */
    public String getToken() {
        return token;
    }

    /**
     *
     * @param token
     * The token
     */
    public void setToken(String token) {
        this.token = token;
    }

}
