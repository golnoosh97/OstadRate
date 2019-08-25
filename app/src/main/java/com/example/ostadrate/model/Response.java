package com.example.ostadrate.model;

import com.squareup.moshi.Json;

public class Response {

    @Json(name = "status")
    public boolean status;

    @Json(name = "message")
    public String message;
}
