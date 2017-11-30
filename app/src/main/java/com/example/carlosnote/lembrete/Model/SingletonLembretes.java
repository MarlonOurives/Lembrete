package com.example.carlosnote.lembrete.Model;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by marlonourives on 13/11/2017.
 */

public class SingletonLembretes {
    private static SQLiteDatabase conex = null;
    private static SingletonLembretes instance = null;
    private SingletonLembretes(Context context){
        try {
            DataBase dataBase = new DataBase(context);
            instance.conex = dataBase.getWritableDatabase();
            Log.i("#Banco","O banco foi criado com sucesso");
        }catch (SQLException ex){
            ex.printStackTrace();
            Log.i("#Banco", "Erro ao criar o banco");
        }
    }
    public static SQLiteDatabase getInstance(Context context){
        if (instance == null){
            instance = new SingletonLembretes(context);
        }
        return instance.getConex();
    }
    private SQLiteDatabase getConex(){
        return conex;
    }
}
