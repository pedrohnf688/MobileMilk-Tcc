package com.eaj.ufrn.mobilemilk.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.eaj.ufrn.mobilemilk.Activity.AtualizarClienteActivity;
import com.eaj.ufrn.mobilemilk.Activity.ListarFazendaActivity;
import com.eaj.ufrn.mobilemilk.Modelo.Arquivo;
import com.eaj.ufrn.mobilemilk.Modelo.Cliente;
import com.eaj.ufrn.mobilemilk.ModeloDTO.ClienteDto;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

public class PerfilFragment extends Fragment {

    private TextView nomeClientePerfil;
    private TextView emailClientePerfil;
    private TextView cpfClientePerfil;
    private TextView telefone1ClientePerfil;
    private TextView telefone2ClientePerfil;

    private Button bAlterarPerfil;
    private Button bListarFazendas;
    CircleImageView circleImageView;
    ImageButton alterarFoto;
    private final int PERMISSION_REQUEST = 2;
    Arquivo arquivo;
    private FrameLayout frameLayout;
    String urlImagem;
    private CircleImageView circular;

    public PerfilFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveIsntanceState){

        View view = inflater.inflate(R.layout.fragment_perfil, container, false); // Infla o layout do fragment

        // Incia componentes da view
        this.nomeClientePerfil = view.findViewById(R.id.nomeCLientePerfil);
        this.emailClientePerfil = view.findViewById(R.id.emailCLientePerfil);
        this.cpfClientePerfil = view.findViewById(R.id.cpfCLientePerfil);
        this.telefone1ClientePerfil = view.findViewById(R.id.telefone1CLientePerfil);
        this.telefone2ClientePerfil = view.findViewById(R.id.telefone2CLientePerfil);

        this.bListarFazendas = view.findViewById(R.id.bListarfazendas);
        this.bAlterarPerfil = view.findViewById(R.id.bAlterarDados);
        this.alterarFoto = view.findViewById(R.id.buttonAlterarFotoPerfil);

        this.circleImageView = view.findViewById(R.id.circleImageViewPerfil);
        // atualiza informações dos textView
        carregarPerfil();

        // implementação de Button para listar fazendas
        this.bListarFazendas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(getContext(), ListarFazendaActivity.class);
                startActivity(t);
            }
        });

        // implementação de Button para tela de atualizar informações
        this.bAlterarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(getActivity().getApplicationContext(), AtualizarClienteActivity.class);
                startActivity(t);
            }
        });

        this.alterarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);
            }
        });


        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSION_REQUEST);
            }
        }

        return view;
    }

    public void carregarPerfil(){
        SharedPreferences prefs = getActivity().getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
        this.nomeClientePerfil.setText(prefs.getString("nome", "null"));
        this.emailClientePerfil.setText(prefs.getString("email", "null"));
        this.cpfClientePerfil.setText(prefs.getString("cpf", "null"));
        this.telefone1ClientePerfil.setText(prefs.getString("telefone1", "DEFAULT"));
        this.telefone2ClientePerfil.setText(prefs.getString("telefone2", "DEFAULT"));
//      loadProfileIcon(prefs.getString("fotoUsuario","null"), circleImageView);
        //loadProfileIcon(urlImagem, circleImageView);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == 1){

            Uri selectImage = data.getData();
            final File file = new File(getPath(selectImage));

            SharedPreferences prefs = getActivity().getSharedPreferences("PREFS_NAME", MODE_PRIVATE);

            final RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            final MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);

            Call<Arquivo> call = new RetrofitConfig().getArquivoService().uploadFileCliente(body, prefs.getString("accessId", "default"),
                    prefs.getString("accessToken", "default"));

            call.enqueue(new Callback<Arquivo>() {
                @Override
                public void onResponse(Call<Arquivo> call, Response<Arquivo> response) {
                    if(response.isSuccessful()) {
                        Toast.makeText(getContext(), "Foto atualizada com sucesso", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Falha ao atualizar foto", Toast.LENGTH_SHORT).show();
                   }
                }

                @Override
                public void onFailure(Call<Arquivo> call, Throwable t) {
                    Toast.makeText(getContext(), "Falha ao atualizar foto", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(getContext(),uri,projection, null, null, null);
        Cursor c = cursorLoader.loadInBackground();
        int column_idx = c.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        c.moveToFirst();
        String picturePath = c.getString(column_idx);
        c.close();
        return picturePath;
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        SharedPreferences prefs2 = getActivity().getSharedPreferences("PREFS_NAME", MODE_PRIVATE);

        Call<Arquivo> call3 = new RetrofitConfig().getArquivoService()
                .fileUrlUser(prefs2.getString("accessId", "default"),
                        prefs2.getString("accessToken", "default"));

        call3.enqueue(new Callback<Arquivo>() {
            @Override
            public void onResponse(Call<Arquivo> call, Response<Arquivo> response) {

                if (response.isSuccessful()) {
                    Log.i("aaaaaa",""+response.body());

                    loadProfileIcon(response.body().getFileDownloadUri(), circleImageView);
                    //Toast.makeText(getContext(),"URL:"+ response.body(), Toast.LENGTH_SHORT).show();
                }else {
                    Log.i("URL ERRADA:", "" + response.body());
                    //Toast.makeText(getContext(), "Erro" + response.body(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Arquivo> call, Throwable t) {
                //Toast.makeText(getContext(), "Falha 2:"+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("falha 2 message:", t.getMessage());
                Log.i("falha 2 stackTrace:",t.getStackTrace().toString());
                Log.i("falha 2 localize:",t.getLocalizedMessage());

                Log.i("falha 2 call:",call.toString());

            }
        });

    }
}
