package com.eaj.ufrn.mobilemilk.Activity;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toolbar;

import com.eaj.ufrn.mobilemilk.Fragments.SolicitacoesFragment;
import com.eaj.ufrn.mobilemilk.R;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottonNavigation;
    private Toolbar tool;
    private Activity getActivity;
    private FrameLayout layout;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle(R.string.Solicitacoes); // Adiciona o título ao ActionBar

        this.bottonNavigation = findViewById(R.id.buttonNavigationHome);
        this.bottonNavigation.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutHome, new SolicitacoesFragment()).commit();

    }

    //Implementação OnNavigationItemSelectedListener navListener
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment fragment = null;

            switch(menuItem.getItemId()){
                case R.id.navigation_solicitacao:
                    getSupportActionBar().setTitle(R.string.Solicitacoes);
                    fragment = new SolicitacoesFragment();
                    break;
                case R.id.navigation_fazenda:
                    getSupportActionBar().setTitle(R.string.Fazendas);
                    fragment = new SolicitacoesFragment();
                    break;
                case R.id.navigation_perfil:
                    getSupportActionBar().setTitle(R.string.Perfil);
                    fragment = new SolicitacoesFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutHome, fragment).commit();
            return true;
        }
    };

    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.options_menu, menu);
        // Associate searchable configuration with the SearchView
        /*SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.searchView).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.sair) {
            Intent i = new Intent(getApplicationContext(), InicioActivity.class);
            finish();
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
