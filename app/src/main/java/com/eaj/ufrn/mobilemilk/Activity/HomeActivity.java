package com.eaj.ufrn.mobilemilk.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toolbar;

import com.eaj.ufrn.mobilemilk.Fragments.FazendasFragment;
import com.eaj.ufrn.mobilemilk.Fragments.PerfilFragment;
import com.eaj.ufrn.mobilemilk.Fragments.SolicitacoesFragment;
import com.eaj.ufrn.mobilemilk.Modelo.Cliente;
import com.eaj.ufrn.mobilemilk.Modelo.Login;
import com.eaj.ufrn.mobilemilk.ModeloDTO.ClienteDto;
import com.eaj.ufrn.mobilemilk.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottonNavigation;
    private Toolbar tool;
    private Activity getActivity;
    private FrameLayout layout;

    private FloatingActionButton actionButton;

    private Button bListarFazendas;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setTitle(R.string.Solicitacoes); // Adiciona o título ao ActionBar

        this.bottonNavigation = findViewById(R.id.buttonNavigationHome);
        // Seta o listener de ButtonNavigation
        this.bottonNavigation.setOnNavigationItemSelectedListener(navListener);

        this.actionButton = findViewById(R.id.floatingActionHome);
        //this.bListarFazendas = findViewById(R.id.bListarfazendas);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutHome, new SolicitacoesFragment()).commit();

    }

    //Implementação OnNavigationItemSelectedListener navListener
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @SuppressLint("RestrictedApi")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

            Fragment fragment = null;

            switch(menuItem.getItemId()){
                case R.id.navigation_solicitacao:
                    getSupportActionBar().setTitle(R.string.Solicitacoes);
                    actionButton.setVisibility(View.VISIBLE);
                    fragment = new SolicitacoesFragment();
                    break;
                case R.id.navigation_fazenda:
                    getSupportActionBar().setTitle(R.string.Fazendas);
                    actionButton.setVisibility(View.VISIBLE);
                    fragment = new FazendasFragment();
                    break;
                case R.id.navigation_perfil:
                    getSupportActionBar().setTitle(R.string.Perfil);
                    actionButton.setVisibility(View.GONE);
                    fragment = new PerfilFragment();
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
            Login.destroyToken(getApplicationContext()); // Limpa do sharedPrefs da aplicação.
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    // Método do ciclo de cida da aplicação, responsável por carregar as informações de cliente as outras activities ...
    @Override
    protected void onStart(){
        super.onStart();

        SharedPreferences prefs = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
        String idCliente = prefs.getString("accessId", null);
        String[] token = prefs.getString("accessToken", null).split(" ");
        String token1 = token[1];

        Log.i("credenciais", "id: " + idCliente);
        Log.i("credenciais", "token: " + token);

        Call<ClienteDto> call = Cliente.buscarCliente(idCliente, token1);
        call.enqueue(new Callback<ClienteDto>() {
            @Override
            public void onResponse(Call<ClienteDto> call, Response<ClienteDto> response) {
                SharedPreferences prefs = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                if(response.isSuccessful()) {

                    ClienteDto c = response.body();

                    editor.putString("nome", response.body().getCliente().getNome());
                    editor.putString("email", response.body().getCliente().getEmail());
                    editor.putString("username", response.body().getCliente().getUsername());
                    editor.putString("cpf", response.body().getCliente().getCpf());

                    List<String> telefones = new ArrayList<String>();

                    for (int i = 0; i < telefones.size(); i++) {
                        editor.putString("telefone" + i, telefones.get(i));
                    }

                    editor.commit();
                    Log.i("certo", "Tudo certo cambada");
                    Log.i("certo", "nome: "+response.body().getCliente().getNome());
                    Log.i("certo", "email: "+response.body().getCliente().getEmail());
                    Log.i("certo", "username: "+response.body().getCliente().getUsername());
                }
                else
                    Log.i("Error", "Error: " + response.errorBody());
            }

            @Override
            public void onFailure(Call<ClienteDto> call, Throwable t) {
                Log.i("Error1", "Error: " + t.toString());
                Log.i("Error1", "Error: " + t.getMessage());
                Log.i("Error1", "Error: " + t.getCause());
            }
        });
    }

    public void cadastrarNovaSolicitacao(View v){
        Intent t = new Intent(getApplicationContext(), CadastrarSolicitacaoActivity.class);
        startActivity(t);
    }

}
