package com.eaj.ufrn.mobilemilk.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eaj.ufrn.mobilemilk.R;

public class PerfilFragment extends Fragment {

    public PerfilFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveIsntanceState){

        View view = inflater.inflate(R.layout.fragment_perfil_usuario, container, false);
        return view;
    }
}
