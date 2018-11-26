package e.ricardo.myapplication;

import android.provider.BaseColumns;

public final class EsquemaDB {

    private EsquemaDB(){

    }
    public static class Esquema implements BaseColumns{
        public static final String TABLE_USUARIO = "usuario";
        public static final String COLUMN_ID_USUARIO = "id_usuario";
        public static final String COLUMN_NOMBE_USUARIO ="nombre";
        public static final String COLUMN_APE_USUARIO ="apelldio";
        public static final String COLUMN_EMAIL_USUARIO = "email";
        public static final String COLUMN_SEX_USUARIO = "genero";
        public static final String COLUMN_PASSWORD_USUARIO = "password";

        public static final  String TABLE_UBICATION = "ubicacion";
        public static final  String COLUMN_ID_UBICATION = "id";
        public static final  String COLUMN_LAT ="latitud";
        public static final  String COLUMN_LONG = "longitud";
        public static final  String COLUMN_UBI ="ubicacion";

        public static final  String TABLE_LUGARES ="lugares";
        public static final  String COLUMN_ID_LUGAR="id_lugar";
        public static final  String COLUMN_NOMBRE_LUGAR ="nombre_lugar";
        public static final  String COLUMN_ID_UBICATION_FK ="id_ubicacion";
        public static final  String COLUMN_ID_SECCION_FK = "id_seccion";
        public static final  String COLUMN_IMG="imagen_lugar";

        public static final  String TABLE_SECCION ="secciones";
        public static final  String COLUMN_ID_SECCION = "id_sec";
        public static final  String COLUMN_NOMBRE_SECCION="nombre_sec";

        public static final  String TABLE_LUGARES_G = "lugares_g";
        public static final  String COLUMN_ID_USUARIO_FK ="id_usuario";
        public static final  String COLUMN_ID_LUGAR_FK = "id_lugar";
        public static final  String COLUMN_PUNTUACION = "puntuacion";
    }


}
