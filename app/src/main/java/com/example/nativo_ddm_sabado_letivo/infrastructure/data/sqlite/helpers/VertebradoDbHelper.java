package com.example.nativo_ddm_sabado_letivo.infrastructure.data.sqlite.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VertebradoDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "vertebrado";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_IDADE = "idade";
    public static final String COLUMN_ESPECIE = "especie";
    public static final String COLUMN_TIPO_COLUNA = "tipoColunaVertebral";
    public static final String COLUMN_GRUPO = "grupo";

    public VertebradoDbHelper(Context context) {
        super(context, "animal_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOME + " TEXT, " +
                COLUMN_IDADE + " INTEGER, " +
                COLUMN_ESPECIE + " TEXT, " +
                COLUMN_TIPO_COLUNA + " TEXT, " +
                COLUMN_GRUPO + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
