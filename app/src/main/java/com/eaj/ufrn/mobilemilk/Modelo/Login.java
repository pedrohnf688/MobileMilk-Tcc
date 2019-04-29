package com.eaj.ufrn.mobilemilk.Modelo;

import android.content.Context;
import android.content.SharedPreferences;

public class Login {

    public Context context;

    public Login(Context context){
        this.context = context;
    }

    // guarda o acessToken
    public static void saveToken(Context context, String token, String idClient) {
        SharedPreferences prefs = context.getSharedPreferences("PREFS_NAME", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("accessToken", token);
        editor.putString("accessId", idClient);
        editor.putBoolean("logged", true);
        editor.commit();
    }

    // destroy accessToken
    public static void destroyToken(Context context){
        SharedPreferences prefs = context.getSharedPreferences("PREFS_NAME", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putBoolean("logged", false);
        editor.clear().commit();
    }

}
