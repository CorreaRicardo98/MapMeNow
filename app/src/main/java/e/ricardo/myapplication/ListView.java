package e.ricardo.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
public class ListView extends AppCompatActivity {

    AdapterImages adapterImages;
    android.widget.ListView lista;
    String[] titulo = new String[]{"Centro Historico"};

    int[] imagenes = new int[]{R.drawable.centrohistorico};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        lista = (android.widget.ListView) findViewById(R.id.listView1);
        adapterImages = new AdapterImages(this,titulo,imagenes);
        lista.setAdapter(adapterImages);


    }
}
