package e.ricardo.myapplication;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import e.ricardo.myapplication.Comentario;
import e.ricardo.myapplication.RetrofitCliente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Servicios {



    public void ingresarUsuario(String nombre, String ape_p, String ape_m, String email, String sex, String pass, final Context context) {
        RetrofitCliente rcConsumirWS = new RetrofitCliente();

        Call<Comentario> wsRegistro = rcConsumirWS.getrestclient().ingresarUsuario(nombre,ape_p,ape_m,email,sex,pass);

        if(wsRegistro != null ){

            wsRegistro.enqueue(new Callback<Comentario>() {
                @Override
                public void onResponse(Call<Comentario> call, Response<Comentario> response) {
                    if(response.isSuccessful()){
                        if (response.body() != null){
                            Comentario objetoRegistro = response.body();

                            Log.i("mensaje","id-> "+objetoRegistro.getId());
                            Log.i("mensaje","mensaje-> "+objetoRegistro.getMensaje());
                        }
                    }else{
                        Toast.makeText(context,"algo a salido mal prro",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Comentario> call, Throwable t) {

                }
            });


        }
    }
}
