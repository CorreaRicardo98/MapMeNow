package e.ricardo.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
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

        if(getFirstTimeRun() == 0) {
           insertar_patin();
        }

    }
    public void insertar_patin(){
        BDUsuario bd;
        bd = new BDUsuario(getApplicationContext());
        SQLiteDatabase bdinsert = bd.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(EsquemaDB.Esquema.COLUMN_NOMBRE_SECCION,"Centro");
        bdinsert.insert(EsquemaDB.Esquema.TABLE_SECCION, null, v);

        v.clear();
        v.put(EsquemaDB.Esquema.COLUMN_NOMBRE_LUGAR,"Tarascas");
        v.put(EsquemaDB.Esquema.COLUMN_ID_SECCION_FK, 1);
        v.put(EsquemaDB.Esquema.COLUMN_IMG,"centrotarascas");
        bdinsert.insert(EsquemaDB.Esquema.TABLE_LUGARES, null, v);
        v.clear();
        v.put(EsquemaDB.Esquema.COLUMN_NOMBRE_LUGAR,"Catedral");
        v.put(EsquemaDB.Esquema.COLUMN_ID_SECCION_FK, 1);
        v.put(EsquemaDB.Esquema.COLUMN_IMG,"centrocatedral");
        bdinsert.insert(EsquemaDB.Esquema.TABLE_LUGARES, null, v);
        v.clear();
        v.put(EsquemaDB.Esquema.COLUMN_NOMBRE_LUGAR,"Cllejon del Romance");
        v.put(EsquemaDB.Esquema.COLUMN_ID_SECCION_FK, 1);
        v.put(EsquemaDB.Esquema.COLUMN_IMG,"centrocallejondelromance");
        bdinsert.insert(EsquemaDB.Esquema.TABLE_LUGARES, null, v);
        v.clear();

        v.put(EsquemaDB.Esquema.COLUMN_NOMBRE_SECCION,"La Huerta");
        bdinsert.insert(EsquemaDB.Esquema.TABLE_SECCION, null, v);
        v.clear();
        v.put(EsquemaDB.Esquema.COLUMN_NOMBRE_LUGAR,"Sirloin");
        v.put(EsquemaDB.Esquema.COLUMN_ID_SECCION_FK, 2);
        v.put(EsquemaDB.Esquema.COLUMN_IMG,"huertasirlon");
        bdinsert.insert(EsquemaDB.Esquema.TABLE_LUGARES, null, v);
        v.clear();
        v.put(EsquemaDB.Esquema.COLUMN_NOMBRE_LUGAR,"Cinepilos");
        v.put(EsquemaDB.Esquema.COLUMN_ID_SECCION_FK, 2);
        v.put(EsquemaDB.Esquema.COLUMN_IMG,"huertacinepolis");
        bdinsert.insert(EsquemaDB.Esquema.TABLE_LUGARES, null, v);
        v.clear();

        v.put(EsquemaDB.Esquema.COLUMN_NOMBRE_SECCION,"Camelinas");
        bdinsert.insert(EsquemaDB.Esquema.TABLE_SECCION, null, v);
        v.clear();
        v.put(EsquemaDB.Esquema.COLUMN_NOMBRE_LUGAR,"Zoológico");
        v.put(EsquemaDB.Esquema.COLUMN_ID_SECCION_FK, 3);
        v.put(EsquemaDB.Esquema.COLUMN_IMG,"camelinaszoologico");
        bdinsert.insert(EsquemaDB.Esquema.TABLE_LUGARES, null, v);
        v.clear();
        v.put(EsquemaDB.Esquema.COLUMN_NOMBRE_LUGAR,"Plaza las Americas");
        v.put(EsquemaDB.Esquema.COLUMN_ID_SECCION_FK, 3);
        v.put(EsquemaDB.Esquema.COLUMN_IMG,"camelinasplazaamericas");
        bdinsert.insert(EsquemaDB.Esquema.TABLE_LUGARES, null, v);
        v.clear();
        v.put(EsquemaDB.Esquema.COLUMN_NOMBRE_LUGAR,"Cinepilos");
        v.put(EsquemaDB.Esquema.COLUMN_ID_SECCION_FK, 3);
        v.put(EsquemaDB.Esquema.COLUMN_IMG,"camelinascinepolis");
        bdinsert.insert(EsquemaDB.Esquema.TABLE_LUGARES, null, v);
        v.clear();

    }

    //---------------------------------------------------------------------------------------------
    private int getFirstTimeRun() {
        SharedPreferences sp = getSharedPreferences("MYAPP", 0);
        int result, currentVersionCode = BuildConfig.VERSION_CODE;
        int lastVersionCode = sp.getInt("FIRSTTIMERUN", -1);
        if (lastVersionCode == -1) result = 0; else
            result = (lastVersionCode == currentVersionCode) ? 1 : 2;
        sp.edit().putInt("FIRSTTIMERUN", currentVersionCode).apply();
        return result;
    }
}
