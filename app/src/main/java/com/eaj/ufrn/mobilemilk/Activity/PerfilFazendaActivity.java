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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.eaj.ufrn.mobilemilk.Modelo.Arquivo;
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

public class PerfilFazendaActivity extends AppCompatActivity {

    private TextView nome;
    private TextView cnpj;
    private TextView cep;
    private TextView estado;
    private TextView cidade;
    private TextView bairro;
    private TextView numero;
    private CircleImageView circleImageViewFazenda;
    private ImageButton imageButtonFazenda;
    private final int PERMISSION_REQUEST = 2;

    private Button editarFazenda;
    private Button excluirFazenda;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_perfil_fazenda);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Perfil da fazenda");

        this.bundle = getIntent().getExtras(); // Recebe as informaçães de fazenda de ListaFazendaActivity

        this.nome = findViewById(R.id.nomeFazenda);
        this.cnpj = findViewById(R.id.cnpjFazenda);
        this.cep = findViewById(R.id.cepFazenda);
        this.cidade = findViewById(R.id.cidadeFazenda);
        this.estado = findViewById(R.id.estadoFazenda);
        this.bairro = findViewById(R.id.bairroFazenda);
        this.numero = findViewById(R.id.numeroFazenda);
        circleImageViewFazenda =  findViewById(R.id.circleImageViewFazenda);
        imageButtonFazenda = findViewById(R.id.adicionarImagemfazenda);


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
                        Log.i("AAA",""+response.toString());
                        Log.i("AAA 3",""+bundle.getString("idFazenda"));
                    }
                }

                @Override
                public void onFailure(Call<Arquivo> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Falha :(", Toast.LENGTH_SHORT).show();
                    Log.i("AAA 2",""+t.getMessage());
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





    // Seta as informações da fazenda selecionada;
    public void setData(Bundle bundle){
        this.nome.setText(bundle.getString("nomeFazenda"));
        this.cnpj.setText(bundle.getString("cnpjFazenda"));
        this.cep.setText(bundle.getString("cepFazenda"));
        this.cidade.setText(bundle.getString("cidadeFazenda"));
        this.estado.setText(bundle.getString("estadoFazenda"));
        this.bairro.setText(bundle.getString("bairroFazenda"));
        this.numero.setText(bundle.getString("numeroFazenda"));
    }

    // Editar informações
    public void editar(View v){
        Intent i = new Intent(getApplicationContext(), AtualizarFazendaActivity.class);
        i.putExtras(bundle);
        startActivity(i);
    }

    // Excruir Fazenda
    public void excluir(View v){

        SharedPreferences prefs = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);

        Call<FazendaDto> call = new RetrofitConfig().getFazendaService().deletarFazenda(
                bundle.getString("idFazenda")
                , prefs.getString("accessToken", "default"));
        call.enqueue(new Callback<FazendaDto>() {
            @Override
            public void onResponse(Call<FazendaDto> call, Response<FazendaDto> response) {
                Intent i = new Intent();
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Fazenda Excluida", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.i("Error", "mensagem: " + response.errorBody());
                    Log.i("Error", "corpo: " + response.toString());
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }
                finish();
            }

            @Override
            public void onFailure(Call<FazendaDto> call, Throwable t) {
                Log.i("Error 2 message:", t.getMessage());
                Log.i("Error 2 stackTrace:",t.getStackTrace().toString());
                Log.i("Error 2 localize:",t.getLocalizedMessage());

                Log.i("Error 2 call:",call.toString());
            }
        });

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
