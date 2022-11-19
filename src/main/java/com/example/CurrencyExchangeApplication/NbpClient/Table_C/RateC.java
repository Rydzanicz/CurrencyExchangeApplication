package com.example.CurrencyExchangeApplication.NbpClient.Table_C;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;
import java.util.List;

@Generated("jsonschema2pojo") public class RateC {

    @SerializedName("table") @Expose private String table;
    @SerializedName("currency") @Expose private String currency;
    @SerializedName("code") @Expose private String code;
    @SerializedName("rates") @Expose private List<RateEmbC> rates = null;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<RateEmbC> getRates() {
        return rates;
    }

    public void setRates(List<RateEmbC> rates) {
        this.rates = rates;
    }

    @Override public String toString() {
        return "Rate{" + "table='" + table + '\'' + ", currency='" + currency + '\'' + ", code='" + code + '\'' + ", rates=" + rates + '}';
    }
}