package com.example.android.realestatemaster.utils.GsonModel;


import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorJsonModel implements Serializable
{

    @SerializedName("error_string")
    @Expose
    private String errorString;
    @SerializedName("error_code")
    @Expose
    private String errorCode;
    private final static long serialVersionUID = -2225149340833571633L;

    /**
     * No args constructor for use in serialization
     *
     */
    public ErrorJsonModel() {
    }

    /**
     *
     * @param errorCode
     * @param errorString
     */
    public ErrorJsonModel(String errorString, String errorCode) {
        super();
        this.errorString = errorString;
        this.errorCode = errorCode;
    }

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}