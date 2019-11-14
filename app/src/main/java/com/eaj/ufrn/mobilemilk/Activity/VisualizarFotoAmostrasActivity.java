package com.eaj.ufrn.mobilemilk.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.eaj.ufrn.mobilemilk.Adapters.AnalisesSolicitadasAdapter;
import com.eaj.ufrn.mobilemilk.Adapters.FotoSolicitacaoAdapter;
import com.eaj.ufrn.mobilemilk.Modelo.Arquivo;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisualizarFotoAmostrasActivity extends AppCompatActivity {

    ImageView imageViewFotoSolicitacao;
    //ImageButton imageButtonFotoSolicitacao;
    private final int PERMISSION_REQUEST = 2;
    private Bundle bundle;
    List<Arquivo> arquivoList = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_foto_amostras);

        //imageButtonFotoSolicitacao = findViewById(R.id.imageButtonFotoAmostras);
        //imageViewFotoSolicitacao = findViewById(R.id.imageViewFotoAmostras);
        recyclerView = findViewById(R.id.recyclerViewAmostrasFotos);


        getSupportActionBar().setTitle("Foto da Solicitação");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.bundle = getIntent().getExtras();




//        imageButtonFotoSolicitacao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent,1);
//            }
//        });


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
                        .placeholder(R.drawable.ic_insert_drive_file_black_24dp)
                        .dontAnimate()
                        .fitCenter())
                .into(imageView);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == 1){

            Uri selectImage = data.getData();

            final File file = new File(getPath(selectImage));

            SharedPreferences prefs = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);

            final RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            final MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

            Call<Arquivo> call = new RetrofitConfig().getArquivoService().uploadFileSolicitacao(body, bundle.getString("SolicitacaoID"),
                    prefs.getString("accessToken", "default"));

            call.enqueue(new Callback<Arquivo>() {
                @Override
                public void onResponse(Call<Arquivo> call, Response<Arquivo> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Só sucesso :)", Toast.LENGTH_SHORT).show();
                        Log.i("Sucesso Arquivo: ",""+response.body());
                    }else {
                        Toast.makeText(getApplicationContext(), "Falha :(", Toast.LENGTH_SHORT).show();
                        Log.i("Falha 1",response.toString());
                    }
                }

                @Override
                public void onFailure(Call<Arquivo> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Falha :(", Toast.LENGTH_SHORT).show();

                    Log.i("Id da Solicitacao:",""+bundle.getString("SolicitacaoID"));
                    Log.i("falha 1 message:", t.getMessage());
                    Log.i("falha 1 stackTrace:",t.getStackTrace().toString());
                    Log.i("falha 1 localize:",t.getLocalizedMessage());


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


    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences prefs2 = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);

        Call<List<Arquivo>> call3 = new RetrofitConfig().getArquivoService()
                .fileUrlSolicitacao(bundle.getString("SolicitacaoID"),
                        prefs2.getString("accessToken", "default"));


        call3.enqueue(new Callback<List<Arquivo>>() {
            @Override
            public void onResponse(Call<List<Arquivo>> call, Response<List<Arquivo>> response) {

                if (response.isSuccessful()) {

                    Log.i("aaaaaa",""+response.body());
                    arquivoList = response.body();

                    FotoSolicitacaoAdapter adapter = new FotoSolicitacaoAdapter(arquivoList, getApplicationContext());
                    recyclerView.setAdapter(adapter);

                    RecyclerView.LayoutManager layout = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(layout);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());

                    //Toast.makeText(getApplicationContext(),"Só Sucesso :)", Toast.LENGTH_SHORT).show();
                }else {

                    Log.i("URL ERRADA:", "" + response.body());
                    Log.i("URL ToString:", "" + response.toString());
                    // Toast.makeText(getApplicationContext(), "Falha :(", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Arquivo>> call, Throwable t) {

                //Toast.makeText(getApplicationContext(), "Falha :(", Toast.LENGTH_SHORT).show();
                Log.i("falha 2 message:", t.getMessage());
                Log.i("falha 2 stackTrace:",t.getStackTrace().toString());
                Log.i("falha 2 localize:",t.getLocalizedMessage());

            }
        });
    }

}
