package e.ricardo.myapplication;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class BDUsuario extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME = "usuario.db";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "+EsquemaDB.Esquema.TABLE_USUARIO+" ("+
            EsquemaDB.Esquema.COLUMN_ID_USUARIO+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            EsquemaDB.Esquema.COLUMN_NOMBE_USUARIO+" TEXT,"+
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
}
