package com.eaj.ufrn.mobilemilk.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.eaj.ufrn.mobilemilk.Enum.EnumTipoPerfilUsuario;
import com.eaj.ufrn.mobilemilk.Modelo.Cliente;
import com.eaj.ufrn.mobilemilk.Modelo.Fazenda;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastrarFazendaActivity extends AppCompatActivity {

    private EditText nomeFazenda;
    private EditText cnpjFazenda;
    private EditText cepFazenda;
    private EditText estadoFazenda;
    private EditText cidadeFazenda;
    private EditText bairroFazenda;
    private EditText numeroFazenda;

    private ImageButton adicionar;
    private Button confirmar;
    private Button cancelar;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle saveInstanteState){
        super.onCreate(saveInstanteState);
        setContentView(R.layout.activity_cadastrar_fazendas);

        getSupportActionBar().setTitle(R.string.CadastrarFazenda); // Seta um titulo ao ActionBar

        this.nomeFazenda = findViewById(R.id.nomeFazenda);
        this.cnpjFazenda = findViewById(R.id.cnpjFazenda);
        this.cepFazenda = findViewById(R.id.cepFazenda);
        this.estadoFazenda = findViewById(R.id.estadoFazenda);
        this.cidadeFazenda = findViewById(R.id.cidadeFazenda);
        this.bairroFazenda = findViewById(R.id.bairroFazenda);
        this.numeroFazenda = findViewById(R.id.numeroFazenda);

        this.adicionar = findViewById(R.id.adicionarImagemfazenda);
        this.confirmar = findViewById(R.id.confirmarCadastro);
        this.cancelar = findViewById(R.id.cancelarCadastro);
    }

    public void cadastrarFazenda(View v){

        boolean teste = this.verifiedInputs();

        if(teste == false)
            return;

        SharedPreferences prefs = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);


        Cliente cliente = new Cliente(prefs.getString("telefone1", "default")
                                        , prefs.getString("telefone2", "default")
                                        , prefs.getString("nome", "default")
                                        , prefs.getString("email", "default")
                                        , prefs.getString("cpf", "default")
                                        , EnumTipoPerfilUsuario.ROLE_CLIENTE
                                        , prefs.getString("accessId", "default"));

        Fazenda fazenda = new Fazenda(this.nomeFazenda.getText().toString()
            , this.cnpjFazenda.getText().toString()
            , this.cepFazenda.getText().toString()
            , "default"
            , this.estadoFazenda.getText().toString()
            , this.cidadeFazenda.getText().toString()
            , this.bairroFazenda.getText().toString()
            , this.numeroFazenda.getText().toString()
            , cliente, "default");

        Call<Fazenda> call = new RetrofitConfig()
                .getFazendaService()
                .cadastrarFazenda(fazenda, prefs.getString("accessToken", "default")
                                         , prefs.getString("accessId", "default"));
        call.enqueue(new Callback<Fazenda>() {
            @Override
            public void onResponse(Call<Fazenda> call, Response<Fazenda> response) {
                if(response.isSuccessful())
                    Toast.makeText(getApplicationContext(), "Fazenda salva com sucesso", Toast.LENGTH_SHORT).show();

                finish();
                Intent i = new Intent(getApplicationContext(), ListarFazendaActivity.class);
            }

            @Override
            public void onFailure(Call<Fazenda> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Erro ao cadastrar", Toast.LENGTH_SHORT).show();
                Log.i("error", "causa: " + t.getCause());
                Log.i("error", "message: " + t.getMessage());
            }
        });

    }

    public void cancelar(View v){
        finish();
    }

    public boolean verifiedInputs(){

        String message = "Campo obrigatório";
        String cnpLenght = "Deve conter 14 números";

        if(this.nomeFazenda.getText().toString().isEmpty()){
            this.nomeFazenda.setError(message);
            return false;
        }
        else if(this.cnpjFazenda.getText().toString().isEmpty()){
            this.cnpjFazenda.setError(message);
            return false;
        }
        else if(this.cnpjFazenda.getText().toString().length() < 14 || this.cnpjFazenda.getText().toString().length() > 14){
            this.cnpjFazenda.setError(cnpLenght);
            return false;
        }
        else if(this.cepFazenda.getText().toString().isEmpty()){
            this.cepFazenda.setError(message);
            return false;
        }
        else if(this.estadoFazenda.getText().toString().isEmpty()){
            this.estadoFazenda.setError(message);
            return false;
        }
        else if(this.cidadeFazenda.getText().toString().isEmpty()){
            this.cidadeFazenda.setError(message);
            return false;
        }
        else if(this.bairroFazenda.getText().toString().isEmpty()){
            this.bairroFazenda.setError(message);
            return false;
        }
        else if(this.numeroFazenda.getText().toString().isEmpty()){
            this.numeroFazenda.setError(message);
            return false;
        }

        return true;
    }

}
