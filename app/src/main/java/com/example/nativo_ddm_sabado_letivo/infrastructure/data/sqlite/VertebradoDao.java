package com.example.nativo_ddm_sabado_letivo.infrastructure.data.sqlite;


import static com.example.nativo_ddm_sabado_letivo.infrastructure.data.sqlite.helpers.VertebradoDbHelper.*;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nativo_ddm_sabado_letivo.domain.entities.Vertebrado;
import com.example.nativo_ddm_sabado_letivo.infrastructure.data.interfaces.IVertebadoDao;
import com.example.nativo_ddm_sabado_letivo.infrastructure.data.sqlite.helpers.VertebradoDbHelper;

import java.util.ArrayList;
import java.util.List;


public class VertebradoDao implements IVertebadoDao {

    private VertebradoDbHelper dbHelper;

    public VertebradoDao(Context context) {
        dbHelper = new VertebradoDbHelper(context);
    }
    public void adicionarVertebrado(Vertebrado vertebrado) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, vertebrado.getNome());
        values.put(COLUMN_IDADE, vertebrado.getIdade());
        values.put(COLUMN_ESPECIE, vertebrado.getEspecie());
        values.put(COLUMN_TIPO_COLUNA, vertebrado.getTipoColunaVertebral());
        values.put(COLUMN_GRUPO, vertebrado.getGrupo());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Vertebrado> listarVertebrados() {
        List<Vertebrado> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Vertebrado v = new Vertebrado(
                        cursor.getString(cursor.getColumnIndex(COLUMN_NOME)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_IDADE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_ESPECIE)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_TIPO_COLUNA)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_GRUPO))
                );
                lista.add(v);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

    public void atualizarVertebrado(Vertebrado vertebrado) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOME, vertebrado.getNome());
        values.put(COLUMN_IDADE, vertebrado.getIdade());
        values.put(COLUMN_ESPECIE, vertebrado.getEspecie());
        values.put(COLUMN_TIPO_COLUNA, vertebrado.getTipoColunaVertebral());
        db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(vertebrado.getId())});
        db.close();
    }

    public void deletarVertebrado(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }


}
