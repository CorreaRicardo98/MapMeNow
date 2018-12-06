package e.ricardo.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class CambiarPass extends AppCompatActivity {

    public EditText pass,pass2,pass3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_pass);

        pass = (EditText) findViewById(R.id.ComprobarPass);
        pass2 = (EditText) findViewById(R.id.NewPass);
        pass3 = (EditText) findViewById(R.id.ComprobarPass);


    }
}
