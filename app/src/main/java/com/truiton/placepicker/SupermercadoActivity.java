package com.truiton.placepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.truiton.placepicker.models.Avaliacao;
import com.truiton.placepicker.models.CatalagoAvaliacao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SupermercadoActivity extends AppCompatActivity {

    private static final String TAG = "teste";
    private TextView  mAcougue;
    private TextView mFrios;
    private  TextView mCaixa;
    private TextView mPadaria;
    private TextView mHortfrut;
    private int caixa = 0;
    private int padaria = 0;
    private int acougue = 0;
    private int frios = 0;
    private int hortifrut = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supermercado);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPadaria = (TextView) findViewById(R.id.notaPad);
        mAcougue = (TextView) findViewById(R.id.notaAco);
        mCaixa = (TextView) findViewById(R.id.notaCaixa);
        mFrios = (TextView) findViewById(R.id.notaFri);
        mHortfrut = (TextView) findViewById(R.id.notaHor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Service.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Service favorito = retrofit.create(Service.class);
        Call<List<Avaliacao>> requestFavorito = favorito.getAvaliacao(1);

        requestFavorito.enqueue(new Callback<List<Avaliacao>>() {
            @Override
            public void onResponse(Call<List<Avaliacao>> call, Response<List<Avaliacao>> response) {
                if(!response.isSuccessful()){
                    Log.i(TAG,"Erro: " + response.code());
                    Log.i(TAG,"Erro: Estou aqui");

                }else{
                    List<Avaliacao> avaliacoes = response.body();

                    for(Avaliacao a : avaliacoes){
                        Log.e(TAG,"Erro: " + a.comentarios);

                        caixa += a.caixa;
                        padaria += a.padaria;
                        acougue += a.acougue;
                        frios += a.frios;
                        hortifrut += a.hortifrut;

                    }
                    mCaixa.setText(Integer.toString(caixa/avaliacoes.size()));
                    mPadaria.setText(Integer.toString(padaria/avaliacoes.size()));
                    mAcougue.setText(Integer.toString(acougue/avaliacoes.size()));
                    mFrios.setText(Integer.toString(frios/avaliacoes.size()));
                    mHortfrut.setText(Integer.toString(hortifrut/avaliacoes.size()));
                }
            }

            @Override
            public void onFailure(Call<List<Avaliacao>> call, Throwable t) {
                Log.e(TAG,"Erro: " + t.getMessage());
                Log.e(TAG,"Erro: OnFailure");
            }
        });


        /*requestFavorito.enqueue(new Callback<List<CatalagoAvaliacao>>() {
            @Override
            public void onResponse(Call<List<CatalagoAvaliacao>> call, Response<List<CatalagoAvaliacao>> response) {

                if(!response.isSuccessful()){
                    Log.i(TAG,"Erro: " + response.code());
                    Log.i(TAG,"Erro: Estou aqui");

                }else{
                    List<CatalagoAvaliacao> avaliacao = response.body();

                    Log.i(TAG,"Erro: Estou aqui");





                }

            }

            @Override
            public void onFailure(Call<List<CatalagoAvaliacao>> call, Throwable t) {

                Log.e(TAG,"Erro: " + t.getMessage());
                Log.e(TAG,"Erro: OnFailure");

            }
        });
        /*requestFavorito.enqueue(new Callback<CatalagoAvaliacao[]>() {
            @Override
            public void onResponse(Call<CatalagoAvaliacao[]> call, Response<CatalagoAvaliacao[]> response) {
                if(!response.isSuccessful()){
                    Log.i(TAG,"Erro: " + response.code());
                    Log.i(TAG,"Erro: Estou aqui");

                }else{
                    CatalagoAvaliacao[] avaliacao = response.body();

                    Log.i(TAG,"Erro: Estou aqui");




                }
            }

            @Override
            public void onFailure(Call<CatalagoAvaliacao[]> call, Throwable t) {
                Log.e(TAG,"Erro: " + t.getMessage());
                Log.e(TAG,"Erro: OnFailure");

            }
        });*/

        /*requestFavorito.enqueue(new Callback<CatalagoAvaliacao>() {
            @Override
            public void onResponse(Call<CatalagoAvaliacao> call, Response<CatalagoAvaliacao> response) {
                if(!response.isSuccessful()){
                    Log.i(TAG,"Erro: " + response.code());
                    Log.i(TAG,"Erro: Estou aqui");
                }else {
                    //Requisição retornou com sucesso

                    CatalagoAvaliacao avaliacoes = response.body();
                    for (Avaliacao a : avaliacoes.avaliacoes) {
                        mTexto.setText(a.comentarios);
                        Log.i(TAG, String.format("%s", a.comentarios));

                    }
                }

            }

            @Override
            public void onFailure(Call<CatalagoAvaliacao> call, Throwable t) {
                Log.e(TAG,"Erro: " + t.getMessage());
                Log.e(TAG,"Erro: OnFailure");
            }
        });

        /*requestFavorito.enqueue(new Callback<CatalagoAvaliacao>() {
            @Override
            public void onResponse(Call<<CatalagoAvaliacao> call, Response<CatalagoAvaliacao> response) {
                if(!response.isSuccessful()){
                    Log.i(TAG,"Erro: " + response.code());
                    Log.i(TAG,"Erro: Estou aqui");
                }else{
                    //Requisição retornou com sucesso

                    CatalagoAvaliacao avaliacoes =  response.body();
                    //Avaliacao a;
                    //String teste = avaliacoes.get(0).avaliacoes.comentarios;


                    for(int i=0; i < avaliacoes.size();i++){
                        Log.e(TAG,"Campo: " + avaliacoes.get(i));
                    }


                    Log.e(TAG,"Erro: OnResponse");

                }
            }

            @Override
            public void onFailure(Call<CatalagoAvaliacao> call, Throwable t) {
                Log.e(TAG,"Erro: " + t.getMessage());
                Log.e(TAG,"Erro: OnFailure");

            }
        });

        */

        final RatingBar minimunRating  = (RatingBar)findViewById(R.id.ratingBarPad);
        minimunRating.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                float touchPositionX = motionEvent.getX();
                float width = minimunRating.getWidth();
                float starsf = (touchPositionX/width) * 200.0f;
                int stars = (int)starsf + 1;
                minimunRating.setRating(stars);
                return true;
            }
        });

    }
}
