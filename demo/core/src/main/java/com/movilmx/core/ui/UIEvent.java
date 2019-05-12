package com.movilmx.core.ui;

public interface UIEvent {
    /**
     * Invocación del algún evento de UI interno que se requiera
     * @param eventType tipo de evento, click en holder, swipe, borrado, etc.
     * @param uiObject data que se quiere transmitir al contendor principal
     */
    void event(UIEventType eventType, UIObject uiObject);
}
