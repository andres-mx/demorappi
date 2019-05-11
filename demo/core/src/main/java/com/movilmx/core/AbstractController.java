package com.movilmx.core;

import com.movilmx.networkcontroller.client.NetworkController;

public class AbstractController {

    private NetworkController client;

    public AbstractController(NetworkController client){
        this.client = client;
    }

    public <T extends NetworkController>T getClient(){
        return (T) client;
    }
}
