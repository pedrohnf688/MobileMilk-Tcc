package com.eaj.ufrn.mobilemilk.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eaj.ufrn.mobilemilk.Enum.EnumTipoPerfilUsuario;
import com.eaj.ufrn.mobilemilk.Modelo.Cliente;
import com.eaj.ufrn.mobilemilk.ModeloDTO.ClienteDto;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AtualizarClienteActivity extends AppCompatActivity {

    private EditText nomeCliente;
    private EditText emailCliente;
    private EditText telefone1Cliente;
    private EditText telefone2Cliente;
    private EditText cpfCliente;
    private EditText senhaAtualCLiente;
    private EditText senhaNovaCLiente;

    private Button alterarInfomacoes;
    private Button cancelarAlteracao;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_alterar_informacoes_cliente);

        // Inicia componentes da view do android.
        this.nomeCliente = findViewById(R.id.nomeClienteAtl);
        this.emailCliente = findViewById(R.id.emailClienteAtl);
        this.cpfCliente = findViewById(R.id.cpfClienteAtl);
        this.telefone1Cliente = findViewById(R.id.telefone1ClienteAtl);
        this.telefone2Cliente = findViewById(R.id.telefone2ClienteAtl);
        this.senhaAtualCLiente = findViewById(R.id.senhaAtualClienteAtl);
        this.senhaNovaCLiente = findViewById(R.id.senhaNovaClienteAtl);

        this.alterarInfomacoes = findViewById(R.id.bAtualizarInformacoes);
        this.cancelarAlteracao = findViewById(R.id.bCancelarInformacoes);

        this.carregarPerfil(); // carrega informações para a tela de atualizar_informações_cliente
    }

    public void carregarPerfil(){
        SharedPreferences prefs = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
        this.nomeCliente.setText(prefs.getString("nome", "default"));
        this.emailCliente.setText(prefs.getString("email", "default"));
        this.cpfCliente.setText(prefs.getString("cpf", "default"));
        this.telefone1Cliente.setText(prefs.getString("telefone1", "default"));
        this.telefone2Cliente.setText(prefs.getString("telefone2", "default"));
    }

    /*
    *    implementação para o button de cacelar alterações
    * */
    public void cancelarAlteracao(View v){
        Toast.makeText(getApplicationContext(), "Operação Cancelada", Toast.LENGTH_SHORT).show();
        finish();
    }

    /*
    *
    * */
    public boolean atualizarInformacoes(View v){

        SharedPreferences prefs = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);

        if(verificaInputs() == false)
            return false;

        Cliente cliente = new Cliente(
                this.telefone1Cliente.getText().toString(),
                this.telefone2Cliente.getText().toString(),
                this.nomeCliente.getText().toString(),
                this.emailCliente.getText().toString(),
                this.cpfCliente.getText().toString(),
                EnumTipoPerfilUsuario.ROLE_CLIENTE,
                null
        );

        Call<ClienteDto> call = new RetrofitConfig().getClienteService().atualizarCliente(
                prefs.getString("accessId", "default"),
                cliente,
                prefs.getString("accessToken", "default")
        );
        call.enqueue(new Callback<ClienteDto>() {
            @Override
            public void onResponse(Call<ClienteDto> call, Response<ClienteDto> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Informações atualizadas", Toast.LENGTH_SHORT).show();
                    Log.i("sucesso", "Http Code: " + response.code());
                    Log.i("sucesso", "Message: " + response.message());
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Erro ao Atualizar", Toast.LENGTH_SHORT).show();
                    Log.i("falha", "Http Code: " + response.code());
                    Log.i("falha", "Message: " + response.message());
                    finish();
                }

            }

            @Override
            public void onFailure(Call<ClienteDto> call, Throwable t) {
                Log.i("Error", "message: " + t.getMessage());
                Log.i("Error", "Causa: " + t.getCause());
            }
        });

        return  true;

    }

    public boolean verificaInputs(){

        if(cpfCliente.length() == 0){
            this.cpfCliente.setError("Cpf é obrigatório");
            return false;
        }
        else if(cpfCliente.length() < 11){
            this.cpfCliente.setError("Cpf inválido");
            return  false;
        }
        else if(cpfCliente.getText().toString().contains(" ")){
            this.cpfCliente.setError("Cpf não pode conter espaços");
            return false;
        }
        else if(telefone1Cliente.equals("")){
            this.telefone1Cliente.setError("Telefone obrigatório");
            return  false;
        }
        else if(telefone1Cliente.length() < 11 || telefone1Cliente.length() > 11){
            this.telefone1Cliente.setError("Deve conter 11 digitos incluindo o DDD");
            return false;
        }
        else if(telefone1Cliente.getText().toString().contains(" ")){
            this.telefone1Cliente.setError("Não use espaços ao preencher o número");
            return false;
        }
        else if(telefone2Cliente.length() < 11 || telefone2Cliente.length() > 11){
            this.telefone2Cliente.setError("Deve conter 11 digitos incluindo o DDD");
            return false;
        }
        else if(telefone2Cliente.getText().toString().contains(" ")){
            this.telefone2Cliente.setError("Não use espaços ao preencher o número");
            return false;
        }
        else if(nomeCliente.length() == 0) {
            nomeCliente.setError("Nome é obrigatório");
            return false;
        }
        else if(emailCliente.length() == 0){
            emailCliente.setError("Email é obrigatório");
            return false;
        }
        else if(emailCliente.getText().toString().contains(" ")){
            emailCliente.setError("Email não pode haver espaços");
            return false;
        }
        else if(!emailCliente.getText().toString().contains("@") || !emailCliente.getText().toString().contains(".")){
            emailCliente.setError("Email não é válido");
            return false;
        }

        return  true;

    }
}
