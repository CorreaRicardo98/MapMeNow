package e.ricardo.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registro extends AppCompatActivity {


    boolean ban=false;
    Spinner opciones;
    Button login;
    String nom,ape,sexo,pass;
    EditText mail,password,nombre,apellido;
    BDUsuario bd;
    String email;
    Servicios servicios = new Servicios();

    private ArrayList<Usuario> alUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        opciones= (Spinner) findViewById(R.id.lista);
        mail=(EditText)findViewById(R.id.correo);
        password=(EditText)findViewById(R.id.reg_password1);

        opciones=(Spinner) findViewById(R.id.lista);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.opciones,android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);
        final Context context=this;
        SharedPreferences datosh=getSharedPreferences("datos",context.MODE_PRIVATE);

        login=(Button)findViewById(R.id.ingresar);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +

                        "\\@" +

                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

                        "(" +

                        "\\." +

                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

                        ")+";
                EditText camponombre=(EditText)findViewById(R.id.usuario);
                EditText campoapellido=(EditText)findViewById(R.id.apellido);
                EditText campopass=(EditText)findViewById(R.id.reg_password1);
                if (camponombre.getText().toString().isEmpty() || campoapellido.getText().toString().isEmpty() || campoapellido.getText().toString().trim().length()==0
                        || camponombre.getText().toString().trim().length()==0 || campopass.getText().toString().trim().length()==0
                        || campopass.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Uno ´mas campos estan vacios ",Toast.LENGTH_LONG).show();
                    ban=false;
                    String email=mail.getText().toString();
                    Matcher matcher= Pattern.compile(validemail).matcher(email);
                    if(matcher.matches()){

                        ban=true;
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Introduce un email correcto ",Toast.LENGTH_LONG).show();
                        ban=false;
                    }

                }else {
                    email=mail.getText().toString().trim();
                    Matcher matcher= Pattern.compile(validemail).matcher(email);
                    if(matcher.matches()){

                        ban=true;
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Introduce un email correcto ",Toast.LENGTH_LONG).show();
                        ban=false;
                    }

                }




                sexo=opciones.getSelectedItem().toString();
                Log.i("sexo",sexo);
                pass = password.getText().toString().trim();
               nom=camponombre.getText().toString();
                ape=campoapellido.getText().toString();
                bd = new BDUsuario(getApplicationContext());
                SQLiteDatabase bdinsert = bd.getWritableDatabase();


                if(ban==true) {
                    validar_correo(nombre,ape,ape,email,sexo,pass);
                    }
            }

        });

    }


    private void guardarpref(){
        EditText camponombre=(EditText)findViewById(R.id.usuario);
        EditText campoapellido=(EditText)findViewById(R.id.apellido);
        SharedPreferences datos=getSharedPreferences("credenciales",Context.MODE_PRIVATE);
        String nombre=camponombre.getText().toString();
        String Apellido=campoapellido.getText().toString();
        SharedPreferences.Editor editor=datos.edit();
        editor.putString("nombre",nombre);
        editor.putString("apellido",Apellido);
        editor.commit();


    }

    public void validar_correo(EditText nombre, String ape_p, String ape_m, final String email, String sex, final String pass) {
        RetrofitCliente rcConsumirWS = new RetrofitCliente();

        Call<Comentario> wsRegistro = rcConsumirWS.getrestclient().validar_coreo(email);

        if(wsRegistro != null ){

            wsRegistro.enqueue(new Callback<Comentario>() {
                @Override
                public void onResponse(Call<Comentario> call, Response<Comentario> response) {
                    if(response.isSuccessful()){
                        if (response.body() != null){
                            Comentario objetoRegistro = response.body();

                            if (objetoRegistro.getMensaje().equals("correo ya registrado")){
                                mail.setError("correo ya registrado");
                            }else{
                                servicios.ingresarUsuario(nom,ape,ape,email,sexo,pass,getApplicationContext());
                                Intent i = new Intent(registro.this,MainActivity.class);
                                startActivity(i);
                            }
                        }

                    }else{
                        Toast.makeText(getApplicationContext(),"algo a salido mal",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Comentario> call, Throwable t) {

                }
            });


        }
    }

}
