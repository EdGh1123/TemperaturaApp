package com.ed.TemperaturaApp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DBControlador {
    private DBHelper baseDatos;

    public DBControlador(Context context) {
        this.baseDatos = new DBHelper(context, ModeloBD.NOMBRE_DB, null, 1);
    }

    public long agregarRegistro(Servicio servicio) {
        try {
            SQLiteDatabase database = baseDatos.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(ModeloBD.NOMBREB, servicio.getDireccion());
            values.put(ModeloBD.FECHA, servicio.getFecha().getTimeInMillis());
            values.put(ModeloBD.TEMPERATURA, servicio.getMedida());
            values.put(ModeloBD.TIPO_TEMPERATURA, servicio.getTipoServicio());
            return database.insert(ModeloBD.NOMBRE_TABLA, null, values);
        } catch (Exception e) {
            return -1L;
        }
    }

    public int actualizarRegistro(Servicio servicio) {
        try {
            SQLiteDatabase database = baseDatos.getWritableDatabase();
            ContentValues valoresActualizados = new ContentValues();
            valoresActualizados.put(ModeloBD.NOMBREB, servicio.getDireccion());
            valoresActualizados.put(ModeloBD.FECHA, servicio.getFecha().getTimeInMillis());
            valoresActualizados.put(ModeloBD.TEMPERATURA, servicio.getMedida());
            valoresActualizados.put(ModeloBD.TIPO_TEMPERATURA, servicio.getTipoServicio());

            String campoParaActualizar = ModeloBD.COL_CODIGO + " = ?";
            String[] argumentosParaActualizar = {String.valueOf(servicio.getId())};

            return database.update(ModeloBD.NOMBRE_TABLA, valoresActualizados, campoParaActualizar, argumentosParaActualizar);
        } catch (Exception e) {
            return 0;
        }
    }

    public int borrarRegistro(Servicio servicio) {
        try {
            SQLiteDatabase database = baseDatos.getWritableDatabase();
            String[] argumentos = {String.valueOf(servicio.getId())};
            return database.delete(ModeloBD.NOMBRE_TABLA, ModeloBD.COL_CODIGO + " = ?", argumentos);
        } catch (Exception e) {
            return 0;
        }
    }

    public ArrayList<Servicio> optenerRegistros() {
        ArrayList<Servicio> servicios = new ArrayList<>();

        SQLiteDatabase database = baseDatos.getReadableDatabase();

        String[] columnasConsultar = {ModeloBD.COL_CODIGO, ModeloBD.NOMBREB, ModeloBD.FECHA
                , ModeloBD.TEMPERATURA, ModeloBD.TIPO_TEMPERATURA};

        Cursor cursor = database.query(ModeloBD.NOMBRE_TABLA, columnasConsultar
                , null, null, null, null, null);

        if (cursor == null) {
            return servicios;
        }

        if (!cursor.moveToFirst()) {
            return servicios;
        }

        do {
            Calendar calendar = new GregorianCalendar();
            calendar.setTimeInMillis(cursor.getLong(2));
            Servicio servicio = new Servicio(cursor.getLong(0), cursor.getString(1)
                    , calendar, cursor.getInt(3), cursor.getInt(4));
            servicios.add(servicio);
        } while (cursor.moveToNext());

        cursor.close();
        return servicios;
    }
}
