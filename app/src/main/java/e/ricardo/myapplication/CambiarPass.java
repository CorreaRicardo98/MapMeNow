package e.ricardo.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Callback;
import retrofit2.Response;

public class CambiarPass extends AppCompatActivity {

    public EditText pass,pass2,pass3;
    public Button cambiar;
    TinyDB dbusuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_pass);

       dbusuario = new TinyDB(getApplicationContext());
       //Toast.makeText(getApplicationContext(),"contraseña: "+dbusuario.getString("pass"),Toast.LENGTH_LONG).show();


        cambiar = (Button) findViewById(R.id.BCambiamesta);
        pass = (EditText) findViewById(R.id.ComprobarPass);
        pass2 = (EditText) findViewById(R.id.NewPass);
        pass3 = (EditText) findViewById(R.id.comprobarContraseña);

        cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(enOnline() == false){
                    AlertDialog.Builder alerta = new AlertDialog.Builder(CambiarPass.this);
                    alerta.setMessage("Coneccion no establecida").setCancelable(false)
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog titulo = alerta.create();
                    titulo.setTitle("Lo siento :c");
                    titulo.show();
                }else {

                    if (pass.getText().equals("") || pass2.getText().equals("") || pass3.getText().equals("")) {
                        Toast.makeText(getApplicationContext(), "Algun Campo Vacio", Toast.LENGTH_LONG).show();
                    } else if (pass.getText().toString().equals(dbusuario.getString("pass"))) {
                        if (pass2.getText().toString().equals(pass3.getText().toString())) {
                            cambiarContra(pass2.getText().toString());
                            Toast.makeText(getApplicationContext(), "pass2: " + pass2.getText().toString(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Las Contraseñas no coinciden", Toast.LENGTH_LONG);
                            pass2.setError("Las Contraseñas no coinciden");
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Contraseña Incorrecta", Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "pass: " + pass.getText(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });



    }

    public void cambiarContra(final String contraseña){

        //Toast.makeText(getApplicationContext(),"estre al cambiarContra",Toast.LENGTH_LONG).show();

        RetrofitCliente rcConsumirWS = new RetrofitCliente();
        dbusuario = new TinyDB(getApplicationContext());

        retrofit2.Call<Comentario> wsCambiar = rcConsumirWS.getrestclient()
                .update_pass(dbusuario.getString("email"),contraseña);

        if(wsCambiar != null ){
          //  Toast.makeText(getApplicationContext(),"sigo adentro",Toast.LENGTH_LONG).show();
            wsCambiar.enqueue(new Callback<Comentario>() {
                @Override
                public void onResponse(retrofit2.Call<Comentario> call, Response<Comentario> response) {

                    if(response.isSuccessful()){
                        if (response.body() != null){
                            Comentario co = response.body();
                            if(co.getMensaje().equals("operacion correcta")){
                                Toast.makeText(getApplicationContext(),"Cambio Correcto",Toast.LENGTH_LONG).show();
                                dbusuario.putString("pass",contraseña);
                                Intent intent = new Intent(CambiarPass.this,Inicio.class);
                                finish();
                            }Toast.makeText(getApplicationContext(),co.getMensaje(),Toast.LENGTH_LONG).show();
                        }else Toast.makeText(getApplicationContext(),"objeto nulo",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"algo a salido mal",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<Comentario> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"algo a salido mal",Toast.LENGTH_SHORT).show();

                }
            });


        }
    }

    public boolean enOnline() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }

        return false;


    }

}
