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
                    if (bd.getRepetidos(email).getCount()>0 && bd.getRepetidos(email)!=null){
                        mail.setError("Este emai ya fue utilizado");
                    }else{
                    guardarpref();
                    Usuarios(nom,ape,ape,email,sexo,pass);
                    Intent i = new Intent(registro.this,MainActivity.class);
                    startActivity(i);

               }}
            }

        });

    }

    public void Usuarios(String nombre,String ape_p,String ape_m, String email,String sex,String pass) {
        RetrofitCliente rcConsumirWS = new RetrofitCliente();

        retrofit2.Call<ArrayList<Usuario>> wsUsuario = rcConsumirWS.getrestclient().ingresarUsuario(nombre,ape_p,ape_m,email,sex,pass);
        if (wsUsuario != null) {
            wsUsuario.enqueue(new Callback<ArrayList<Usuario>>() {
                @Override
                public void onResponse(@NonNull retrofit2.Call<ArrayList<Usuario>> call, Response<ArrayList<Usuario>> response) {
                    if (response.isSuccessful()) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                "isSuccessful.", Toast.LENGTH_SHORT);
                        toast.show();
                        if (response.body() != null) {

                            alUsuario = new ArrayList<Usuario>(response.body());

                            for (int i = 0; i < alUsuario.size(); i++) {
                                Usuario usuario = alUsuario.get(i);

                                Log.i("usuarioxd", "id: "
                                        + usuario.getUsuario() + " nombre: "
                                        + usuario.getNombre() + " apellido: "
                                        + usuario.getApe_p() + " pass: "
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

}
