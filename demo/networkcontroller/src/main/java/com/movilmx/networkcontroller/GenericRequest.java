package com.movilmx.networkcontroller;

import com.google.gson.Gson;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.json.JSONObject;

public class GenericRequest {
    public JSONObject toJson(){
        JSONObject jsonObject  = null;
        String jsonString  = "";
        try {
            Gson gson = new Gson();
            jsonString  = gson.toJson(this);
            jsonObject  = new JSONObject(jsonString);
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public String TAG(){
        return this.getClass().getSimpleName();
    }
}
