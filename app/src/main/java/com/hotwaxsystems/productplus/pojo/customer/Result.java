
package com.hotwaxsystems.productplus.pojo.customer;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Result {

    @SerializedName("customerDetailsList")
    @Expose
    private List<CustomerDetailsList> customerDetailsList = new ArrayList<CustomerDetailsList>();
    @SerializedName("listSize")
    @Expose
    private Integer listSize;
    @SerializedName("viewIndex")
    @Expose
    private Integer viewIndex;
    @SerializedName("viewSize")
    @Expose
    private Integer viewSize;

    /**
     * 
     * @return
     *     The customerDetailsList
     */
    public List<CustomerDetailsList> getCustomerDetailsList() {
        return customerDetailsList;
    }

    /**
     * 
     * @param customerDetailsList
     *     The customerDetailsList
     */
    public void setCustomerDetailsList(List<CustomerDetailsList> customerDetailsList) {
        this.customerDetailsList = customerDetailsList;
    }

    /**
     * 
     * @return
     *     The listSize
     */
    public Integer getListSize() {
        return listSize;
    }

    /**
     * 
     * @param listSize
     *     The listSize
     */
    public void setListSize(Integer listSize) {
        this.listSize = listSize;
    }

    /**
     * 
     * @return
     *     The viewIndex
     */
    public Integer getViewIndex() {
        return viewIndex;
    }

    /**
     * 
     * @param viewIndex
     *     The viewIndex
     */
    public void setViewIndex(Integer viewIndex) {
        this.viewIndex = viewIndex;
    }

    /**
     * 
     * @return
     *     The viewSize
     */
    public Integer getViewSize() {
        return viewSize;
    }

    /**
     * 
     * @param viewSize
     *     The viewSize
     */
    public void setViewSize(Integer viewSize) {
        this.viewSize = viewSize;
    }

}
