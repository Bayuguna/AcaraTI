package com.example.bayuguna.progmob.entities;

import com.squareup.moshi.Json;

public class AccessToken {
    @Json(name = "token_type")
    String tokenType;
    @Json(name = "expires_in")
    int expiresIn;
    @Json(name = "acsess_token")
    String accessToken;
    @Json(name = "refresh_token")
    String refreshToken;
}
