package io.rougesocket.model;

public class CurrencyModel {
    private String code;
    private double value;

    public CurrencyModel(){}
    public CurrencyModel(String code, double value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
