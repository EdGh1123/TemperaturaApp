package com.ed.TemperaturaApp;

public class ModeloBD {

    public static final String NOMBRE_DB = "serviciosPublicos";
    public static final String NOMBRE_TABLA = "registros";
    public static final String COL_CODIGO = "codigo";
    public static final String NOMBREB = "direccion";
    public static final String FECHA = "fecha";
    public static final String TEMPERATURA = "medida";
    public static final String TIPO_TEMPERATURA = "tipoDServicio";

    public static final String CREAR_TABLA_REGISTROS = "CREATE TABLE " +
            "" + NOMBRE_TABLA + " ( " + COL_CODIGO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " " + NOMBREB + " TEXT, " + FECHA + " INTEGER, " +
            " " + TEMPERATURA + " INTEGER, " + TIPO_TEMPERATURA + " INTEGER)";

}
