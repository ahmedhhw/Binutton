package com.doublea.binyutton.backgroundTools;

public class Request {
    private String id;
    private String message;

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    private int stage; // Stores where in request process the user is
    public Request(String id, String message){
        this.id = id;
        this.message = message;
        stage = 0;
    }
    public Request(String id, String message, int stage){
        this.id = id;
        this.message = message;
        stage = stage;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
