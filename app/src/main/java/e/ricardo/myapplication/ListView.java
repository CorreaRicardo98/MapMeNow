package e.ricardo.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

public class ListView extends AppCompatActivity {

    AdapterImages adapterImages;
    android.widget.ListView lista;
    Button regresar;
    private int contador=0;
    BDUsuario bdUsuario;

    String[] titulo = new String[]{"Centro Historico","Av. Camelinas","La Huerta"};

    int[] imagenes = new int[]{R.drawable.centrohistorico,R.drawable.avcamelinas,R.drawable.lahuerta};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        regresar = (Button) findViewById(R.id.bregresar);


        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListView.this,Inicio.class);
                startActivity(intent);
            }
        });

        /*
        Cursor res = bdUsuario.getseciones();
        String img="";
        while (res.moveToNext()){
            if(titulo[titulo.length] != null){
                titulo[titulo.length] = res.getString(0);
                img = res.getString(res.getColumnIndex(EsquemaDB.Esquema.COLUMN_IMG));
                String ruta ="R.drawable.";
                imagenes[imagenes.length+1] = R.drawable.centrocatedral;
            }

        }
        */



        lista = (android.widget.ListView) findViewById(R.id.listView1);
        adapterImages = new AdapterImages(this,titulo,imagenes);
        lista.setAdapter(adapterImages);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0 :
                        setContentView(R.layout.centrohistorico);
                        break;
                    case 1 :
                        setContentView(R.layout.avcamelinas);
                        break;
                    case 2:
                        setContentView(R.layout.lahuerta);
                        break;
                }
            }
        });
    }

    public void onBackPressed() {

            Intent intent = new Intent(this,ListView.class);
            startActivity(intent);

    }
}
