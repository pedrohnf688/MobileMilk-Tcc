package com.eaj.ufrn.mobilemilk;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toolbar;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottonNavigation;
    private Toolbar tool;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle("Solicitações");

        this.bottonNavigation = findViewById(R.id.buttonNavigationHome);
        this.bottonNavigation.setOnNavigationItemSelectedListener(navListener);
    }

    //Implementação OnNavigationItemSelectedListener navListener
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            switch(menuItem.getItemId()){
                case R.id.navigation_solicitacao:
                    getSupportActionBar().setTitle("Solicitações");
                    break;
                case R.id.navigation_fazenda:
                    getSupportActionBar().setTitle("Fazendas");
                    break;
                case R.id.navigation_perfil:
                    getSupportActionBar().setTitle("Perfil");
                    break;
            }

            return true;
        }
    };
}
