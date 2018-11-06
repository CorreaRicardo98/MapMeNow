package e.ricardo.myapplication;

import android.provider.BaseColumns;

public final class EsquemaDB {

    private EsquemaDB(){

    }
    public static class Esquema implements BaseColumns{
        public static final String TABLE_USUARIO = "usuario";
        public static final String COLUMN_ID_USUARIO = "id_usuario";
        public static final String COLUMN_NOMBE_USUARIO ="nombre";
        public static final String COLUMN_PASSWORD_USUARIO = "password";
    }


}
