package com.example.bayuguna.progmob.network;

import android.content.SharedPreferences;

import com.example.bayuguna.progmob.entities.AccessToken;

public class TokenManager {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private static TokenManager INSTANCE =null;

    private TokenManager(SharedPreferences preferences){
        this.preferences = preferences;
        this.editor = preferences.edit();
    }

    static synchronized TokenManager getInstance(SharedPreferences preferences){
        if (INSTANCE == null){
            INSTANCE = new TokenManager(preferences);
        }
        return INSTANCE;
    }

    void saveToken(AccessToken token){
        editor.putString("ACCESS TOKEN", token.getAccessToken()).commit();
        editor.putString("REFRESH TOKEN", token.getRefreshToken()).commit();
    }

    public void deleteToken(){
        editor.remove("ACCESS TOKEN").commit();
        editor.remove("REFRESH TOKEN").commit();
    }

    public AccessToken getToken(){
        AccessToken token = new AccessToken();
        token.setAccessToken(preferences.getString("ACCESS TOKEN", null));
        token.setRefreshToken(preferences.getString("REFRESH TOKEN", null));

        return token;
    }
}
