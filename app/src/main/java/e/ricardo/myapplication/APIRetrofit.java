package e.ricardo.myapplication;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRetrofit {

    @FormUrlEncoded
    @POST("usuariopost")
    Call<ArrayList<Usuario>> obtenerDatos(@Field("nombre") String nombre,
                                          @Field("usuario") String usuario,
                                          @Field("pasword") String password);

    @FormUrlEncoded
    @POST("ingresar_usuario")
    Call<ArrayList<Usuario>> ingresarUsuario(@Field("nombre") String nombre,
                                          @Field("ape_p") String apellido_p,
                                          @Field("ape_m") String apellido_m,
                                            @Field("email") String email,
                                            @Field("sex") String sex,
                                            @Field("pass") String pass);

}
