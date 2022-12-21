package com.example.CurrencyExchangeApplication.NbpClient.Table_AllCurrency;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo") public class RateEmbAllCurrency {

    @SerializedName("currency") @Expose private String currency;
    @SerializedName("code") @Expose private String code;
    @SerializedName("mid") @Expose private Double mid;

    public String getCurrency() {
        return currency;
    }

    public String getCode() {
        return code;
    }

    public Double getMid() {
        return mid;
    }

    @Override public String toString() {
        return "RateEmb {" + "currency='" + currency + '\'' + ", code='" + code + '\'' + ", mid=" + mid + '}';
    }
}