package com.example.carlosnote.lembrete.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by marlonourives on 13/11/2017.
 */

public class DataBase extends SQLiteOpenHelper {
    public DataBase(Context context) {
        super(context,"LISTALEMBRETES",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(scriptSQL.getSqlLembrete());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
