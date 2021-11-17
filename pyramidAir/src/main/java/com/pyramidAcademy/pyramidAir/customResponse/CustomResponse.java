package com.pyramidAcademy.pyramidAir.customResponse;

public class CustomResponse<T> implements iCustom<T> {
    private boolean status;
    private String message;
    private T data;

    public CustomResponse(){}
    public CustomResponse(T data, String message, boolean status){
        this.data = data;
        this.message = message;
        this.status =status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public String getMessage() {
        return message;
    }

}
