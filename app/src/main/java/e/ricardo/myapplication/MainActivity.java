package e.ricardo.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
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



                    if (usuario.equals("")) {
                        usuario1.setError("no puede estar vacio");
                        mensajevt.setText("usuario o contraseña vacio ");
                        mensajevt.setVisibility(mensajevt.VISIBLE);

                    }else{
                        //usuario1.setError(null);

                        usuario1.setErrorEnabled(false);

                    }

                    if (contraseña.equals("")) {
                        contraseña1.setError("no puede estar vacio");
                        mensajevt.setText("usuario o contraseña vacio ");
                        mensajevt.setVisibility(mensajevt.VISIBLE);
                    }else{

                        //contraseña1.setError(null);
                        mensajevt.setVisibility(mensajevt.INVISIBLE);
                    }




                    if (contraseña.length()<10 && contraseña.length()>0){
                        contraseña1.setError("La contraseña muy corta");
                        mensajevt.setText("Contraseña muy corta ");
                        mensajevt.setVisibility(mensajevt.VISIBLE);
                    }else{
                       // contraseña1.setError(null);
                        mensajevt.setVisibility(mensajevt.INVISIBLE);
                    }
                    if (usuario.length()<10 && usuario.length()>0){
                        usuario1.setError("Usuario  muy corto");
                    }else{
                        //usuario1.setError(null);
                    }
                    if (usuario.length()>10 && usuario.length()<20){
                        usuario1.setError(null);
                    }
                    if (contraseña.length()>10 && contraseña.length()<20){
                        contraseña1.setError(null);
                    }



                    if (contraseña.length()>20){
                        contraseña1.setError("La contraseña demaciado larga");
                    }else{
                        //contraseña1.setError(null);
                    }
                    if (usuario.length()>20){
                        usuario1.setError("Usuario demaciado grande");
                    }else{
                       // usuario1.setError(null);

                        contraseña1.setErrorEnabled(false);
                    }
                    if (usuario.equals("") || contraseña.equals("")){
                        usuario1.setError("usuario vacio");
                        contraseña1.setError("contraseña vacia");
                    }



                if (usuario.equals("qwerty") && contraseña.equals("toor")){
                    Intent nuevo = new Intent(MainActivity.this,Hola.class);
                    startActivity(nuevo);
                    message = Toast.makeText(getApplicationContext(),"Ingresado",Toast.LENGTH_LONG);
                    message.show();
                }else if (usuario.equals("root") && contraseña.equals("toor")){
                    Intent nuevo = new Intent(MainActivity.this,Inicio.class);
                    startActivity(nuevo);
                    message = Toast.makeText(getApplicationContext(),"Ingresado",Toast.LENGTH_LONG);
                    message.show();


                }


            }
        });





    }

}
