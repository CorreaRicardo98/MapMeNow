package e.ricardo.myapplication;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
public interface APIRetrofit {

    @FormUrlEncoded
    @POST("pre_login")
    Call<Comentario> preLogin(@Field("id") String id,
                              @Field("mensaje") String mensaje);

    @FormUrlEncoded
    @POST("usuariopost")
    Call<ArrayList<Usuario>> obtenerDatos(@Field("nombre") String nombre,
                                          @Field("usuario") String usuario,
                                          @Field("pasword") String password);

    @FormUrlEncoded
    @POST("ingresar_usuario")
    Call<Comentario> ingresarUsuario(@Field("nombre") String nombre,
                                          @Field("ape_p") String apellido_p,
                                          @Field("ape_m") String apellido_m,
                                            @Field("email") String email,
                                            @Field("sex") String sex,
                                            @Field("pass") String pass);

    @FormUrlEncoded
    @POST("validar_correo")
    Call<Comentario> validar_coreo(@Field("email") String email);



    @FormUrlEncoded
    @POST("update_perfil")
    Call<Comentario> update_perfil(@Field("nombre") String nombre,
                                   @Field("apellido") String apellido,
                                   @Field("sexo") String sexo,
                                   @Field("email") String email,
                                   @Field("id") String id);

    @FormUrlEncoded
    @POST("cambia_pass")
    Call<Comentario> cambia_pass(@Field("pass") String pass);

    @FormUrlEncoded
    @POST("consulta_pass")
    Call<Comentario> consulta_pass(@Field("pass") String pass);



}
