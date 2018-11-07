package e.ricardo.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
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

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registro extends AppCompatActivity {


    boolean ban=false;
    Spinner opciones;
    Button login;
    String nom,ape,sexo,pass;
    EditText mail,password,nombre,apellido;
    BDUsuario bd;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        opciones= (Spinner) findViewById(R.id.lista);
        mail=(EditText)findViewById(R.id.correo);
        password=(EditText)findViewById(R.id.password);
        pass = password.getText().toString().trim();
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

                if (camponombre.getText().toString().isEmpty() || campoapellido.getText().toString().isEmpty()){
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



                password.setError("Enter password ");
                sexo=opciones.getSelectedItem().toString();
               nom=camponombre.getText().toString();
                ape=campoapellido.getText().toString();
                bd = new BDUsuario(getApplicationContext());
                SQLiteDatabase bdinsert = bd.getWritableDatabase();


                if(ban==true) {
                    guardarpref();

                    ContentValues valores = new ContentValues();
                    valores.put(EsquemaDB.Esquema.COLUMN_NOMBE_USUARIO,nom);
                    valores.put(EsquemaDB.Esquema.COLUMN_APE_USUARIO,ape);
                    valores.put(EsquemaDB.Esquema.COLUMN_EMAIL_USUARIO,email);
                    valores.put(EsquemaDB.Esquema.COLUMN_SEX_USUARIO,sexo);
                    valores.put(EsquemaDB.Esquema.COLUMN_PASSWORD_USUARIO,pass);
                    Long id = bdinsert.insert(EsquemaDB.Esquema.TABLE_USUARIO,null,valores);
                    Log.i("INSERT","ID INSERTADO-> "+id);
                    Intent i = new Intent(registro.this,MainActivity.class);
                    startActivity(i);
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

}
