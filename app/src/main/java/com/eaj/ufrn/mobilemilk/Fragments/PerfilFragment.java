package com.eaj.ufrn.mobilemilk.Fragments;

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
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
import com.bumptech.glide.request.RequestOptions;
import com.eaj.ufrn.mobilemilk.Activity.AtualizarClienteActivity;
import com.eaj.ufrn.mobilemilk.Activity.ListarFazendaActivity;
import com.eaj.ufrn.mobilemilk.Modelo.Arquivo;
import com.eaj.ufrn.mobilemilk.R;
import com.eaj.ufrn.mobilemilk.Retrofit.RetrofitConfig;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.app.Activity.RESULT_OK;

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

    //private Usuario usuario;

    private FrameLayout frameLayout;

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
        SharedPreferences prefs = getActivity().getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
        this.nomeClientePerfil.setText(prefs.getString("nome", "null"));
        this.emailClientePerfil.setText(prefs.getString("email", "null"));
        this.cpfClientePerfil.setText(prefs.getString("cpf", "null"));
        this.telefone1ClientePerfil.setText(prefs.getString("telefone1", "DEFAULT"));
        this.telefone2ClientePerfil.setText(prefs.getString("telefone2", "DEFAULT"));
        //loadProfileIcon("",circleImageView);
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
                        .placeholder(R.drawable.userlogin)
                        .dontAnimate()
                        .fitCenter())
                .into(imageView);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == 1){

            Uri selectImage = data.getData();
            File file = new File(getPath(selectImage));

            RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("upload", file.getName(), fileReqBody);

            SharedPreferences prefs = getActivity().getSharedPreferences("PREFS_NAME", Context.MODE_PRIVATE);
            Call<Arquivo> call = new RetrofitConfig().getArquivoService().uploadFileCliente(part, prefs.getString("accessId", "default"),
                    prefs.getString("accessToken", "default"));

            call.enqueue(new Callback<Arquivo>() {
                @Override
                public void onResponse(Call<Arquivo> call, Response<Arquivo> response) {
                    Arquivo arquivo = response.body();
                   if(response.isSuccessful()) {

                       // loadProfileIcon(arquivo.getFileDownloadUri(),circleImageView);
                       Toast.makeText(getContext(), "Só sucesso :)", Toast.LENGTH_SHORT).show();
                       Log.i("Arquivo 1", response.body().toString());
                       //Toast.makeText(getContext(), call.toString(), Toast.LENGTH_SHORT).show();
                       Toast.makeText(getContext(), arquivo.toString(), Toast.LENGTH_SHORT).show();
                   }else {

                       Log.i("Arquivo 2",""+response.toString());
                       Toast.makeText(getContext(), arquivo.toString(), Toast.LENGTH_SHORT).show();
                       Toast.makeText(getContext(), ""+response.toString(), Toast.LENGTH_SHORT).show();
                   }

                }

                @Override
                public void onFailure(Call<Arquivo> call, Throwable t) {
                    Toast.makeText(getContext(), "Falha :(", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.i("Falha Arquivo 3",t.getMessage());
                    Log.i("Falha Arquivo 4",call.toString());
                    Log.i("Falha Arquivo 5", String.valueOf(t.getCause()));
                }
            });

        }
    }

    public String getPath(Uri uri) {
        String[] filePath = {MediaStore.Images.Media.DATA};
        Cursor c = getContext().getContentResolver().query(uri, filePath, null, null, null);
        c.moveToFirst();
        int columnIndex = c.getColumnIndex(filePath[0]);
        String picturePath = c.getString(columnIndex);
        c.close();
        return picturePath;
    }
}
