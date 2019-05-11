package com.movilmx.core.builder;

import com.movilmx.core.communication.MovieControllerObject;
import com.movilmx.core.exceptions.MovieControllerException;

public class MovieControllerObjectBuilder {
    private Integer code = -99;//Por deault ajustamos un número que sirve de referencia
    private String msg;
    private Object data;

    public MovieControllerObjectBuilder setCode(Object code){
        if(null != code){
            if (code instanceof String && ((String) code).length() > 0){
                this.code = Integer.parseInt((String) code);
            }else if (code instanceof Integer) {
                this.code = (Integer) code;
            }else{
                this.code = (Integer) code;
            }
        }
        return this;
    }

    public MovieControllerObjectBuilder setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public MovieControllerObjectBuilder setData(Object data) {
        this.data = data;
        return this;
    }

    public MovieControllerObject build() throws MovieControllerException {
        if (code == -99){
            throw new MovieControllerException("Código de respuesta no seteado");
        }

        return new MovieControllerObject(code, msg, data);
    }
}
