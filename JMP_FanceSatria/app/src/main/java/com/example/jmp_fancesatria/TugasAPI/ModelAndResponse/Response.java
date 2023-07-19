package com.example.jmp_fancesatria.TugasAPI.ModelAndResponse;

public class Response {
    public String data;
    public int statusCode;

    public Response(int statusCode, String data) {
        this.data = data;
        this.statusCode = statusCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
