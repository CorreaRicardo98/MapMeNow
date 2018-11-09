package e.ricardo.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BDUsuario extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION =4;
    public static final String DATABASE_NAME = "usuario.db";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "+EsquemaDB.Esquema.TABLE_USUARIO+" ("+
            EsquemaDB.Esquema.COLUMN_ID_USUARIO+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            EsquemaDB.Esquema.COLUMN_NOMBE_USUARIO+" TEXT,"+
            EsquemaDB.Esquema.COLUMN_APE_USUARIO+" TEXT,"+
            EsquemaDB.Esquema.COLUMN_EMAIL_USUARIO+" TEXT,"+
            EsquemaDB.Esquema.COLUMN_SEX_USUARIO+" TEXT,"+
            EsquemaDB.Esquema.COLUMN_PASSWORD_USUARIO+" TEXT)";

    private static final String SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS "+ EsquemaDB.Esquema.TABLE_USUARIO;



    public BDUsuario(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void getalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] colums = {EsquemaDB.Esquema.COLUMN_EMAIL_USUARIO,EsquemaDB.Esquema.COLUMN_NOMBE_USUARIO};
        Cursor res= db.rawQuery("SELECT * FROM "+EsquemaDB.Esquema.TABLE_USUARIO,null);
        int i = 1;
        Log.i("parametro1","tamaño "+res.getCount());
        while (res.moveToNext()){
            Log.i("usuarios","usuario "+i+"->"+res.getString(3));
            Log.i("usuarios","contraseña "+i+"->"+res.getString(5));
        }
    }

    public Cursor getData(String email, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.i("parametro","-> "+email);
        Log.i("parametro","-> "+pass);
        String[] selectArgs ={email,pass};
        String[] colums = {EsquemaDB.Esquema.COLUMN_EMAIL_USUARIO,EsquemaDB.Esquema.COLUMN_NOMBE_USUARIO};
        Cursor res = db.query(EsquemaDB.Esquema.TABLE_USUARIO,
                colums,
                EsquemaDB.Esquema.COLUMN_EMAIL_USUARIO+" = ? AND "+EsquemaDB.Esquema.COLUMN_PASSWORD_USUARIO+" = ?",
                selectArgs,
                null,
                null,
                null);

        return res;
    }
}
