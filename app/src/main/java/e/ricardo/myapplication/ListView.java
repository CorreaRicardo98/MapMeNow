package e.ricardo.myapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
public class ListView extends AppCompatActivity {

    AdapterImages adapterImages;
    android.widget.ListView lista;
    BDUsuario bdUsuario;

    String[] titulo = new String[]{"Centro Historico",};

    int[] imagenes = new int[]{R.drawable.centrohistorico};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        Cursor res = bdUsuario.getseciones();
        String img="";
        while (res.moveToNext()){
            if(titulo[titulo.length] != null){
                titulo[titulo.length] = res.getString(0);
                img = res.getString(1).to;
                imagenes[imagenes.length] = img:
            }
        }

        lista = (android.widget.ListView) findViewById(R.id.listView1);
        adapterImages = new AdapterImages(this,titulo,imagenes);
        lista.setAdapter(adapterImages);


    }
}
