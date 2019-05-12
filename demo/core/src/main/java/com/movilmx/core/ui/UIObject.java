package com.movilmx.core.ui;

public class UIObject {
    private int         holderPosition;
    private Exception   exception;

    /**
     * En eventos como {@link UIEventType#HOLDERCLICK,UIEventType#WARNING}
     * es necesario trasportar el video que requiere dicha acción, asi que lo enviamos
     * en la notificación.
     */
    private Object data;

    public int getHolderPosition() {
        return holderPosition;
    }

    public UIObject setHolderPosition(int holderPosition) {
        this.holderPosition = holderPosition;
        return this;
    }

    public Exception getException() {
        return exception;
    }

    public UIObject setException(Exception exception) {
        this.exception = exception;
        return this;
    }

    public Object getData() {
        return data;
    }

    public UIObject setData(Object data) {
        this.data = data;
        return this;
    }
}
