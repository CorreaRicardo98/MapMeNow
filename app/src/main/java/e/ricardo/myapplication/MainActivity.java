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


        //--------------------------OBTENIENDO DATOS DEL SERVIDOR----------------------------------
        obtenerDatos();
        //-----------------------------------------------------------------------------------------

        btnINgresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuario,contraseña;
                EditText usu =  findViewById(R.id.main_editText);
                usuario = usu.getText().toString().trim();
                EditText pass = findViewById(R.id.editText2);
                contraseña = pass.getText().toString().trim();

                TinyDB tinyDB = new TinyDB(getApplicationContext());
                //---------------------Usuario por defecto------------------------------------------
                tinyDB.putString("UserName",usuario);
                tinyDB.putString("pass",contraseña);

                Intent intent = new Intent(MainActivity.this,Inicio.class);
                startActivity(intent);
            }
        });


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
                    Cursor res = bd.getData(usuario,contrasena);
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



                    }
                }



            }
        });





    }


    public class void obtenerDatos(){
        clienteUsuario = new ClienteUsuario();

        retrofit2.Call<ArrayList<Usuario>> wsUsuario = clienteUsuario.getrestclient().obtenerDatos();
        if (wsUsuario != null) {
            wsUsuario.enqueue(new Callback<ArrayList<Usuario>>() {
                @Override
                public void onResponse(retrofit2.Call<ArrayList<Usuario>> call, Response<ArrayList<Usuario>> response) {
                    if (response.isSuccessful()) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "isSuccessful.", Toast.LENGTH_SHORT);
                        toast.show();
                        if (response.body() != null) {

                            alUsuario = new ArrayList<Usuario>(response.body());

                            for (int i = 0; i < alUsuario.size(); i++) {
                                Usuario usuario = alUsuario.get(i);

                                Log.i("usuarioxd", "user: "
                                        + usuario.getUsuario() + " ID: "
                                        + usuario.getId() + " Nombre: "
                                        + usuario.getNombre() + " Pass: "
                                        + usuario.getPass());
                            }
                        } else {
                            Toast toast1 = Toast.makeText(getApplicationContext(),
                                    "No existen comentarios registrados.", Toast.LENGTH_SHORT);
                            toast1.show();
                        }
                    } else {
                        Log.i("log", response.message());
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Ocurrió un problema al cargar los datos. Intente más tarde1.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<ArrayList<Usuario>> call, Throwable t) {
                    Log.i("log", t.getMessage());
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Ocurrió un problema al cargar los datos. Intente más tarde.",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }
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
