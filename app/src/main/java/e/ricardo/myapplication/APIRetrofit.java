package e.ricardo.myapplication;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRetrofit {

    @GET("usuarioget")
    Call<ArrayList<Usuario>> obtenerDatos();

}
