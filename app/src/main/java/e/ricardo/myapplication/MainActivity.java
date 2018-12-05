package e.ricardo.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;

//Login
public class MainActivity extends AppCompatActivity {

    private Button btnINgresar;
    private int contador =0;
    private TextInputLayout usuario1, contrasena1;
    private ClienteUsuario clienteUsuario;
    private ArrayList<Usuario> alUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnINgresar = (Button) findViewById(R.id.boton_1);
        final TextView mensajevt = (TextView) findViewById(R.id.mensaje);


        //--------------------------Esto no es----------------------------------------------

        btnINgresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuario,contraseña;
                EditText usu =  findViewById(R.id.main_editText);
                usuario = usu.getText().toString().trim();
                @SuppressLint("WrongViewCast") EditText pass = findViewById(R.id.editText2);
                contraseña = pass.getText().toString().trim();

                TinyDB tinyDB = new TinyDB(getApplicationContext());
                //---------------------Usuario por defecto------------------------------------------
                tinyDB.putString("UserName",usuario);
                tinyDB.putString("pass",contraseña);

                Intent intent = new Intent(MainActivity.this,Inicio.class);
                startActivity(intent);
            }
        });
        //---------------------------------------------------------------------------------------

       btnINgresar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                Toast message;
                String usuario,contrasena;
                usuario1 = (TextInputLayout) findViewById(R.id.main_usuario);
                contrasena1 = (TextInputLayout) findViewById(R.id.main_password);
                EditText usu =  findViewById(R.id.main_editText);
                usuario = usu.getText().toString().trim();
                EditText pass = findViewById(R.id.main_editText2);
                contrasena = pass.getText().toString().trim();
                BDUsuario bd = new BDUsuario(getApplicationContext());

                TinyDB tinyDB = new TinyDB(getApplicationContext());
                if (usuario.equals("") || contrasena.equals("")){
                    if (usuario.equals("")){
                        usuario1.setError("Campo vacio");
                    }else{
                        usuario1.setError(null);
                    }
                    if (contrasena.equals("")){
                        contrasena1.setError("Campo vacio");
                    }else{
                        contrasena1.setError(null);
                    }
                }else{

                   /* Cursor res = bd.getData(usuario,contrasena);
                   Cursor res1 = bd.getseciones();
                   Cursor res2 = bd.getlugares(1);
                   while (res1.moveToNext()){
                        Log.i("usuarios","usuario->"+res1.getString(0));
                        Log.i("usuarios","contraseña->"+res1.getString(1));
                   }

                   if (res!=null && res.getCount()>0){
                        if (res.getCount()>0){
                            while (res.moveToNext()){
                                tinyDB.putString("Email",res.getString(0));
                                tinyDB.putString("UserName",res.getString(1));
                                tinyDB.putString("sexxx",res.getString(2));
                            }
                            Intent intent = new Intent(MainActivity.this,Inicio.class);
                            startActivity(intent);

                            message = Toast.makeText(getApplicationContext(),"Ingresado",Toast.LENGTH_LONG);
                            message.show();
                        }
                        message = Toast.makeText(getApplicationContext(),"Usuario no encontrado",Toast.LENGTH_LONG);
                        message.show();
                    }else{

                        message = Toast.makeText(getApplicationContext(),"Usuario no encontrado",Toast.LENGTH_LONG);
                        message.show();



                    }*/

                    RetrofitCliente rcConsumirWS = new RetrofitCliente();

                    retrofit2.Call<Comentario> wsLogin = rcConsumirWS.getrestclient().validar_coreo(usuario);

                    if(wsLogin != null ){

                        wsLogin.enqueue(new Callback<Comentario>() {
                            @Override
                            public void onResponse(retrofit2.Call<Comentario> call, Response<Comentario> response) {
                                if(response.isSuccessful()){
                                    if (response.body() != null){
                                        Comentario preLogin = response.body();

                                        if (preLogin.getId()=="0"){


                                        }
                                        else Toast.makeText(getApplicationContext(),preLogin.getMensaje(),Toast.LENGTH_SHORT).show();

                                    }

                                }else{
                                    Toast.makeText(getApplicationContext(),"algo a salido mal",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(retrofit2.Call<Comentario> call, Throwable t) {

                            }
                        });


                    }
                }



            }
        });





    }







    @Override
    protected void onDestroy() {
        super.onDestroy();
        BDUsuario bd = new BDUsuario(getApplicationContext());
        bd.close();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(),"onPause",Toast.LENGTH_SHORT).show();
        Log.d("mensaje", "onPause: llamado ");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d("mensaje", "onResume: llamado ");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }



    @Override
    public void onBackPressed() {


        if (contador==0){
            Toast.makeText(getApplicationContext(),"Precione de nuevo para salir",Toast.LENGTH_SHORT).show();
            contador++;
            CountDownTimer cdt = new CountDownTimer(3000,1000){

                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    contador=0;
                }

            };
            cdt.start();
        }else {
            Intent intent= new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            System.exit(0);

        }


}
}
