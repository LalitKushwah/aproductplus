package com.hotwaxsystems.productplus.pojo.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by akshay on 26/10/16.
 */

public class LoginAndConfigBypass {

    private LoginAndConfigBypass(){

    }

    public static void setUp(String url){

        Retrofit loginAndByPass = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();


    }

    private  interface  LoginAndConfigBypassService{

        @FormUrlEncoded
        @POST("/pos/control/main")
        Call<PosTerminalIdListResponse> postDetails();
    }


    //////////////////////////////////////////////////////////////

     //   POJOS

    ///////////////////////////////////////////////////////////////
    @Generated("org.jsonschema2pojo")
    public class PosTerminalId {

        @SerializedName("posTerminalId")
        @Expose
        private String posTerminalId;

        /**
         *
         * @return
         * The posTerminalId
         */
        public String getPosTerminalId() {
            return posTerminalId;
        }

        /**
         *
         * @param posTerminalId
         * The posTerminalId
         */
        public void setPosTerminalId(String posTerminalId) {
            this.posTerminalId = posTerminalId;
        }

    }




    @Generated("org.jsonschema2pojo")
    public class PosTerminalIdListResponse {

        @SerializedName("posTerminalIds")
        @Expose
        private List<PosTerminalId> posTerminalIds = new ArrayList<PosTerminalId>();
        @SerializedName("sessionId")
        @Expose
        private String sessionId;

        /**
         *
         * @return
         * The posTerminalIds
         */
        public List<PosTerminalId> getPosTerminalIds() {
            return posTerminalIds;
        }

        /**
         *
         * @param posTerminalIds
         * The posTerminalIds
         */
        public void setPosTerminalIds(List<PosTerminalId> posTerminalIds) {
            this.posTerminalIds = posTerminalIds;
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

    }

}

