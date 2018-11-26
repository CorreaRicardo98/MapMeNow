package e.ricardo.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BDUsuario extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION =11;
    public static final String DATABASE_NAME = "usuario.db";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "+EsquemaDB.Esquema.TABLE_USUARIO+" ("+
            EsquemaDB.Esquema.COLUMN_ID_USUARIO+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            EsquemaDB.Esquema.COLUMN_NOMBE_USUARIO+" TEXT,"+
            EsquemaDB.Esquema.COLUMN_APE_USUARIO+" TEXT,"+
            EsquemaDB.Esquema.COLUMN_EMAIL_USUARIO+" TEXT,"+
            EsquemaDB.Esquema.COLUMN_SEX_USUARIO+" TEXT,"+
            EsquemaDB.Esquema.COLUMN_PASSWORD_USUARIO+" TEXT)";

    public static final  String SQL_CREATE_LUGARES="CREATE TABLE "+EsquemaDB.Esquema.TABLE_LUGARES+" ("+
            EsquemaDB.Esquema.COLUMN_ID_LUGAR+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            EsquemaDB.Esquema.COLUMN_NOMBRE_LUGAR+" TEXT,"+
            EsquemaDB.Esquema.COLUMN_ID_SECCION_FK+" INTEGER,"+
            EsquemaDB.Esquema.COLUMN_IMG+" TEXT)";

    public static final  String SQL_CREATE_SECCION="CREATE TABLE "+EsquemaDB.Esquema.TABLE_SECCION+" ("+
            EsquemaDB.Esquema.COLUMN_ID_SECCION+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            EsquemaDB.Esquema.COLUMN_NOMBRE_SECCION+" TEXT)";

    public static final  String SQL_CREATE_LUGARES_G = "CREATE TABLE "+EsquemaDB.Esquema.TABLE_LUGARES_G+"("+
            EsquemaDB.Esquema.COLUMN_ID_USUARIO_FK+" INTEGER,"+
            EsquemaDB.Esquema.COLUMN_ID_LUGAR_FK+" INTEGER,"+
            EsquemaDB.Esquema.COLUMN_PUNTUACION+" REAL)";


    private static final String SQL_DELETE_ENTRIES ="DROP TABLE IF EXISTS "+ EsquemaDB.Esquema.TABLE_USUARIO;



    public BDUsuario(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_LUGARES);
        db.execSQL(SQL_CREATE_SECCION);
        db.execSQL(SQL_CREATE_LUGARES_G);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public Cursor getseciones(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res= db.rawQuery("SELECT * FROM "+EsquemaDB.Esquema.TABLE_SECCION,null);
        int i = 1;
        Log.i("parametro1","tamaño "+res.getCount());

        return res;
    }

    public Cursor getlugares(int fk){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res1234 = db.rawQuery("SELECT * FROM "+EsquemaDB.Esquema.TABLE_LUGARES+" WHERE "+EsquemaDB.Esquema.COLUMN_ID_SECCION_FK +" = "+fk,null);
        String[] colums = {EsquemaDB.Esquema.COLUMN_ID_LUGAR,EsquemaDB.Esquema.COLUMN_NOMBRE_LUGAR,EsquemaDB.Esquema.COLUMN_IMG};
        Cursor res = db.query(EsquemaDB.Esquema.TABLE_LUGARES,
                colums,
                EsquemaDB.Esquema.COLUMN_ID_SECCION_FK+" = "+fk,
                null,
                null,
                null,
                null);
        Log.i("Lugares123","tamaño "+res1234.getCount());
        return res1234;
    }

    public Cursor getData(String email, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Log.i("parametro","-> "+email);
        Log.i("parametro","-> "+pass);
        String[] selectArgs ={email,pass};
        String[] colums = {EsquemaDB.Esquema.COLUMN_EMAIL_USUARIO,EsquemaDB.Esquema.COLUMN_NOMBE_USUARIO,EsquemaDB.Esquema.COLUMN_SEX_USUARIO};
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
