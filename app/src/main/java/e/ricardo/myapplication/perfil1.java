package e.ricardo.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class perfil1 extends AppCompatActivity {
    TextView tvdatos= (TextView)findViewById(R.id.tvdatos);
    String nombre,apellidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil1);
        SharedPreferences datosh=getSharedPreferences("datos",Context.MODE_PRIVATE);
        String nombre = datosh.getString(getString(R.string.nombre), "");
        tvdatos.setText(nombre);
        //cargarpreferencias();
        //recibirdatos();

    }
    public void recibirdatos(){
            Bundle extras=getIntent().getExtras();
            String nombre= extras.getString("nombre");
            String apellido= extras.getString("apellido");
            String sexo= extras.getString("sexo");

        cargarpreferencias();


    }
    private void cargarpreferencias(){
        SharedPreferences datosh=getSharedPreferences("datos",Context.MODE_PRIVATE);
        String user= datosh.getString("nombre","no existe dato ");
        String apellido= datosh.getString("apellido","no existe dato ");
        tvdatos.setText(user);


    }
}
