package com.eaj.ufrn.mobilemilk.Activity;

import android.Manifest;
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
import com.eaj.ufrn.mobilemilk.ModeloDTO.FazendaDto;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AtualizarFazendaActivity extends AppCompatActivity {

    private EditText nomeFazenda;
    private EditText cpfcnpjFazenda;
    private EditText cepFazenda;
    private EditText estadoFazenda;
    private EditText cidadeFazenda;
    private EditText bairroFazenda;
    private EditText numeroFazenda;
    private CircleImageView circleImageViewFazenda;
    private ImageButton imageButtonFazenda;
    private final int PERMISSION_REQUEST = 2;


    private Bundle bundle;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_alterar_informacoes_fazenda);

        this.nomeFazenda = findViewById(R.id.nomeFazendaAtl);
        this.cpfcnpjFazenda = findViewById(R.id.cnpjFazendaAtl);
        this.cepFazenda = findViewById(R.id.cepFazendaAtl);
        this.estadoFazenda = findViewById(R.id.estadoFazendaAtl);
        this.cidadeFazenda = findViewById(R.id.cidadeFazendaAtl);
        this.bairroFazenda = findViewById(R.id.bairroFazendaAtl);
        this.numeroFazenda = findViewById(R.id.numeroFazendaAtl);
        circleImageViewFazenda =  findViewById(R.id.circleImageViewFazenda);
        imageButtonFazenda = findViewById(R.id.adicionarImagemfazenda);

        // Recebe as informações vindas de Perfil Fazendas
        this.bundle = getIntent().getExtras();

        this.setData(bundle);

        imageButtonFazenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);
            }
        });

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
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

    public static void loadProfileIcon(String url, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.fazenda)
                        .dontAnimate()
                        .fitCenter())
                .into(imageView);
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

            Call<Arquivo> call = new RetrofitConfig().getArquivoService().uploadFileFazenda(body, bundle.getString("idFazenda"),
                    prefs.getString("accessToken", "default"));

            call.enqueue(new Callback<Arquivo>() {
                @Override
                public void onResponse(Call<Arquivo> call, Response<Arquivo> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Só sucesso :)", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "Falha :(", Toast.LENGTH_SHORT).show();
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



    // Confirma alterações
    public void confirmarAlteracoes(View v){

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

        Log.i("idFazenda", "idFazenda = " + bundle.getString("idFazenda"));

        Call<FazendaDto> call = new RetrofitConfig().getFazendaService().editarFazenda(
                bundle.getString("idFazenda")
                , prefs.getString("accessToken", "default")
                , fazenda);
        call.enqueue(new Callback<FazendaDto>() {
            @Override
            public void onResponse(Call<FazendaDto> call, Response<FazendaDto> response) {
                if(response.isSuccessful())
                    Toast.makeText(getApplicationContext(), "Fazenda Atualizada", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Erro ao atualizar", Toast.LENGTH_SHORT).show();

                finish();
            }

            @Override
            public void onFailure(Call<FazendaDto> call, Throwable t) {

            }
        });

    }

    // Cancelar alterações
    public void cancelarAlteracoes(View v){
        finish();
    }

    // Seta as informações da fazenda selecionada;
    public void setData(Bundle bundle){
        this.nomeFazenda.setText(bundle.getString("nomeFazenda"));
        this.cpfcnpjFazenda.setText(bundle.getString("cnpjFazenda"));
        this.cepFazenda.setText(bundle.getString("cepFazenda"));
        this.cidadeFazenda.setText(bundle.getString("cidadeFazenda"));
        this.estadoFazenda.setText(bundle.getString("estadoFazenda"));
        this.bairroFazenda.setText(bundle.getString("bairroFazenda"));
        this.numeroFazenda.setText(bundle.getString("numeroFazenda"));
    }

    // Verifica os inputs de entrada
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
    public void onStart() {
        super.onStart();

        SharedPreferences prefs2 = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);

        Call<Arquivo> call3 = new RetrofitConfig().getArquivoService()
                .fileUrlFazenda(bundle.getString("idFazenda"),
                        prefs2.getString("accessToken", "default"));

        call3.enqueue(new Callback<Arquivo>() {
            @Override
            public void onResponse(Call<Arquivo> call, Response<Arquivo> response) {

                if (response.isSuccessful()) {
                    Log.i("aaaaaa",""+response.body());

                    loadProfileIcon(response.body().getFileDownloadUri(), circleImageViewFazenda);
                    //Toast.makeText(getApplicationContext(),"URL:"+ response.body(), Toast.LENGTH_SHORT).show();
                }else {
                    Log.i("URL ERRADA:", "" + response.body());
                   // Toast.makeText(getApplicationContext(), "Erro" + response.body(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Arquivo> call, Throwable t) {
                //Toast.makeText(getApplicationContext(), "Falha 2:"+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("falha 2 message:", t.getMessage());
                Log.i("falha 2 stackTrace:",t.getStackTrace().toString());
                Log.i("falha 2 localize:",t.getLocalizedMessage());

                Log.i("falha 2 call:",call.toString());

            }
        });

    }
}
