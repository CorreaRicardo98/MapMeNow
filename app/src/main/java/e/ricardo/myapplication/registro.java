package e.ricardo.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

public class registro extends AppCompatActivity {
    Spinner opciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        opciones= (Spinner) findViewById(R.id.lista);
    }
}
