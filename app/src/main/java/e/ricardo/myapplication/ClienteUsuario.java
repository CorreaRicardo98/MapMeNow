package e.ricardo.myapplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClienteUsuario {

    private static final String BASE_URL = "http://taptecm.com/equipo5/ws/";
    private Retrofit retrofit;
    private APIRetrofit REST_CLIENT;

    public ClienteUsuario(){

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(20,TimeUnit.SECONDS).readTimeout(20,TimeUnit.SECONDS).build();
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient).build();
        REST_CLIENT = retrofit.create(APIRetrofit.class);
    }
    public APIRetrofit getrestclient(){return REST_CLIENT;}
}
