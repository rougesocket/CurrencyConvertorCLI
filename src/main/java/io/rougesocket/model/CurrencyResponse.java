package io.rougesocket.model;

import java.util.Map;


public class CurrencyResponse {

    private Object meta;
    private Map<String,CurrencyModel> data;

    public CurrencyResponse() {
    }

    public CurrencyResponse(String meta, Map<String, CurrencyModel> data) {
        this.meta = meta;
        this.data = data;
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public Map<String, CurrencyModel> getData() {
        return data;
    }

    public void setData(Map<String, CurrencyModel> data) {
        this.data = data;
    }
}

