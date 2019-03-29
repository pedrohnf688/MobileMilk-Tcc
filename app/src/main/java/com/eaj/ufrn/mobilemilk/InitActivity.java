package com.eaj.ufrn.mobilemilk;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


public class InitActivity extends AppCompatActivity {

    private Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        /*myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);*/


    }
}
