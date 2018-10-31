package e.ricardo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class principal extends AppCompatActivity {
    Button blogin,bsign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        blogin=(Button)findViewById(R.id.blogin);
        bsign=(Button)findViewById(R.id.bsign);
        bsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nav2=new Intent(principal.this,registro.class);
                startActivity(nav2);
            }
        });
        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nav=new Intent(principal.this,MainActivity.class);
                startActivity(nav);
            }
        });
    }
}
