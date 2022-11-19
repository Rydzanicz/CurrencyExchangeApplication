package com.example.CurrencyExchangeApplication.NbpClient.Table_AllCurrency;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.processing.Generated;
import java.util.List;

@Generated("jsonschema2pojo") public class RateAllCurrency {

    @SerializedName("table") @Expose private String table;
    @SerializedName("no") @Expose private String no;
    @SerializedName("effectiveDate") @Expose private String effectiveDate;
    @SerializedName("rates") @Expose private List<RateEmbAllCurrency> rates = null;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public List<RateEmbAllCurrency> getRates() {
        return rates;
    }

    public void setRates(List<RateEmbAllCurrency> rates) {
        this.rates = rates;
    }

    @Override public String toString() {
        return "Rate{" + "table='" + table + '\'' + ", no='" + no + '\'' + ", effectiveDate='" + effectiveDate + '\'' + ", rates=" + rates + '}';
    }
}