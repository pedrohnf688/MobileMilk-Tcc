package com.eaj.ufrn.mobilemilk.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.eaj.ufrn.mobilemilk.Enum.EnumTipoPerfilUsuario;
import com.eaj.ufrn.mobilemilk.Modelo.Arquivo;
import com.eaj.ufrn.mobilemilk.Modelo.Cliente;
import com.eaj.ufrn.mobilemilk.Modelo.Fazenda;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastrarFazendaActivity extends AppCompatActivity {

    private EditText nomeFazenda;
    private EditText cpfcnpjFazenda;
    private EditText cepFazenda;
    private EditText estadoFazenda;
    private EditText cidadeFazenda;
    private EditText bairroFazenda;
    private EditText numeroFazenda;

    private ImageButton adicionar;
    private Button confirmar;
    private Button cancelar;
    private final int PERMISSION_REQUEST = 2;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle saveInstanteState){
        super.onCreate(saveInstanteState);
        setContentView(R.layout.activity_cadastrar_fazendas);

        getSupportActionBar().setTitle(R.string.CadastrarFazenda); // Seta um titulo ao ActionBar

        this.nomeFazenda = findViewById(R.id.nomeFazenda);
        this.cpfcnpjFazenda = findViewById(R.id.cnpjFazenda);
        this.cepFazenda = findViewById(R.id.cepFazenda);
        this.estadoFazenda = findViewById(R.id.estadoFazenda);
        this.cidadeFazenda = findViewById(R.id.cidadeFazenda);
        this.bairroFazenda = findViewById(R.id.bairroFazenda);
        this.numeroFazenda = findViewById(R.id.numeroFazenda);

        this.adicionar = findViewById(R.id.adicionarImagemfazenda);
        this.confirmar = findViewById(R.id.confirmarCadastro);
        this.cancelar = findViewById(R.id.cancelarCadastro);

        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);
            }
        });

        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(CadastrarFazendaActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(CadastrarFazendaActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSION_REQUEST);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //A permissão foi concedida. Pode continuar
            } else {
                // A permissão foi negada. Precisa ver o que deve ser desabilitado
            }
            return;
        }
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
            , this.cpfcnpjFazenda.getText().toString()
            , this.cepFazenda.getText().toString()
            , "default"
            , this.estadoFazenda.getText().toString()
            , this.cidadeFazenda.getText().toString()
            , this.bairroFazenda.getText().toString()
            , this.numeroFazenda.getText().toString()
            , cliente);

        Call<Fazenda> call = new RetrofitConfig()
                .getFazendaService()
                .cadastrarFazenda(fazenda, prefs.getString("accessToken", "default")
                                         , prefs.getString("accessId", "default"));
        call.enqueue(new Callback<Fazenda>() {
            @Override
            public void onResponse(Call<Fazenda> call, Response<Fazenda> response) {
                if(response.isSuccessful())
                    Toast.makeText(getApplication(), "Fazenda salva com sucesso", Toast.LENGTH_SHORT).show();

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
        String lenght = "Cpf deve conter 11 dígitos, já o Cnpj contem 14 dígitos";
        String entraInvalida = "Este campo não corresponde a um cpf ou cnpj válido";

        if(this.nomeFazenda.getText().toString().isEmpty()){
            this.nomeFazenda.setError(message);
            return false;
        }
        else if(this.cpfcnpjFazenda.getText().toString().isEmpty()){
            this.cpfcnpjFazenda.setError(message);
            return false;
        }
        else if(this.cpfcnpjFazenda.getText().toString().length() != 11 && this.cpfcnpjFazenda.getText().toString().length() != 14){
            this.cpfcnpjFazenda.setError(lenght);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == 1){

            Uri selectImage = data.getData();
            final File file = new File(getPath(selectImage));

            SharedPreferences prefs = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);

            final RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            final MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

            Call<Arquivo> call = new RetrofitConfig().getArquivoService().uploadFileFazenda(body, prefs.getString("accessId", "default"),
                    prefs.getString("accessToken", "default"));

            call.enqueue(new Callback<Arquivo>() {
                @Override
                public void onResponse(Call<Arquivo> call, Response<Arquivo> response) {
   //                 arquivo = response.body();
                    if(response.isSuccessful()) {

//                        SharedPreferences prefs2 = getActivity().getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
//                        SharedPreferences.Editor editor = prefs2.edit();
//                        editor.putString("fotoUsuario", arquivo.getFileDownloadUri());
//                        editor.commit();

                        Toast.makeText(getApplicationContext(), "Só sucesso :)", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(getApplicationContext(), ""+response.toString(), Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<Arquivo> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Falha :(", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(getApplicationContext(),uri,projection, null, null, null);
        Cursor c = cursorLoader.loadInBackground();
        int column_idx = c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        c.moveToFirst();
        String picturePath = c.getString(column_idx);
        c.close();
        return picturePath;
    }

}
