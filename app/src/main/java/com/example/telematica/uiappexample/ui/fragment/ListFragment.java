package com.example.telematica.uiappexample.ui.fragment;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.telematica.uiappexample.R;
import com.example.telematica.uiappexample.presenters.HttpServerConnection;
import com.example.telematica.uiappexample.models.Libro;
import com.example.telematica.uiappexample.presenters.PresenterImpl;
import com.example.telematica.uiappexample.presenters.UIAdapter;
import com.example.telematica.uiappexample.ui.views.ViewInter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cpola on 15-01-2016.
 */public class ListFragment extends Fragment implements ViewInter{

    private PresenterImpl mPresenter;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    /**
     * New instance of ListFragment
     *
     * @return new instance of ListFragment
     */
    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View mainView = inflater.inflate(R.layout.fragment_list, null);

        /*
         * Aquí es donde deben hacer el link a los elementos del layout fragment_list,
         * y donde prácticamente se debe hacer el desarrollo
        */
        mRecyclerView = (RecyclerView) mainView.findViewById(R.id.recyclerView);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);






        new JSONTask().execute();
        // specify an adapter (see also next example)


        return mainView;
    }




    // se habia colocado primer parametro como URL
    public class JSONTask extends AsyncTask<String,String, String> {




        @Override
        protected String doInBackground(String... params) {
            String resultado = new HttpServerConnection().connectToServer("http://www.mocky.io/v2/56990dc51200009e47e25b44", 15000);
            return resultado;
        }

        protected void onPostExecute(String result){
            if(result != null){
                System.out.println(result);
                mAdapter = new UIAdapter(getLista(result));
                mRecyclerView.setAdapter(mAdapter);
            }
        }


    }


    private List<Libro> getLista(String result){
        List<Libro> listaLibros = new ArrayList<Libro>();
        try {
            JSONArray lista = new JSONArray(result);

            int size = lista.length();
            for(int i = 0; i < size; i++){
                Libro libro = new Libro();
                JSONObject objeto = lista.getJSONObject(i);

                libro.setId(objeto.getInt("id"));
                libro.setNombre(objeto.getString("nombre"));
                libro.setEditorial(objeto.getString("editorial"));
                libro.setGenero(objeto.getString("genero"));
                libro.setAutor(objeto.getInt("autor"));

                listaLibros.add(libro);
            }
            return listaLibros;
        } catch (JSONException e) {
            e.printStackTrace();
            return listaLibros;
        }
    }
    

    @Override
    public void onResume(){
        super.onResume();
       // mLocationPresenter.startUpdates();
    }

    @Override
    public void onPause() {
        super.onPause();
        //mPresenter.terminarBusqueda();
    }
    public void showConnectionErrorMsg() {
        Toast.makeText(getActivity(),"Fallo al conectar a dirección http", Toast.LENGTH_LONG).show();
    }

}

