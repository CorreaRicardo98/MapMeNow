package e.ricardo.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnINgresar;
    private int contador =0;
    private TextInputLayout usuario1,contraseña1;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(),"onPause",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {


        if (contador==0){
            Toast.makeText(getApplicationContext(),"Precione de nuevo para salir",Toast.LENGTH_SHORT).show();
            contador++;
            new CountDownTimer(3000,1000){

                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {
                    contador=0;
                }
            };
        }else {
            Intent intent= new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            System.exit(0);

        }


}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnINgresar = (Button) findViewById(R.id.boton_1);
        final TextView mensajevt = (TextView) findViewById(R.id.mensaje);
        btnINgresar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                Toast message;
                String usuario,contraseña;
                usuario1 = (TextInputLayout) findViewById(R.id.usuario);
                contraseña1 = (TextInputLayout) findViewById(R.id.password);
                EditText usu = findViewById(R.id.editText);
                usuario = usu.getText().toString().trim();
                EditText pass = findViewById(R.id.editText2);
                contraseña = pass.getText().toString().trim();


                if (usuario.equals("") || contraseña.equals("")){
                    if (usuario.equals("")) {
                        usuario1.setError("no puede estar vacio");

                    }else{
                        usuario1.setError(null);
                    }
                    if (contraseña.equals("")) {
                        contraseña1.setError("no puede estar vacio");
                    }else{
                        contraseña1.setError(null);
                    }
                    if (usuario.equals("") && contraseña.equals("")){
                        usuario1.setError("usuario vacio");
                        contraseña1.setError("contraseña vacia");
                    }
                    message = Toast.makeText(getApplicationContext(),"Complete los campos",Toast.LENGTH_LONG);
                    message.show();

                }else if(contraseña.length()>10){
                    message = Toast.makeText(getApplicationContext(),"La contraseña no puede se mayor a 10 caracteres",Toast.LENGTH_LONG);
                    message.show();
                    mensajevt.setText("contraseña demaciado larga");

                }else if (usuario.equals("Ricardo") && contraseña.equals("123456")){
                    Intent nuevo = new Intent(MainActivity.this,Hola.class);
                    startActivity(nuevo);
                    message = Toast.makeText(getApplicationContext(),"Ingresado",Toast.LENGTH_LONG);
                    message.show();


                }
                else{
                    message = Toast.makeText(getApplicationContext(),"Usuario o Contraseña incorrectos",Toast.LENGTH_LONG);
                    message.show();
                    mensajevt.setText("Usuario o Contraseña incorrectos");
                    mensajevt.setTextColor(R.color.colorPrimary);

                }

            }
        });





    }

}
