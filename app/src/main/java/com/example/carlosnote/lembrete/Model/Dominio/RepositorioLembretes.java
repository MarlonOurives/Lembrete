package com.example.carlosnote.lembrete.Model.Dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.carlosnote.lembrete.Model.SingletonLembretes;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by marlonourives on 13/11/2017.
 */

public class RepositorioLembretes {
    private SQLiteDatabase conexao;
    public RepositorioLembretes(Context context){
        conexao = SingletonLembretes.getInstance(context);
    }
    public boolean insertLembrete(Lembrete lembrete){
        try {
            ContentValues value = new ContentValues();
            value.put("MENSAGEM",lembrete.getMensagem());
            value.put("DATA",lembrete.getData().toString());
            value.put("URGENTE",lembrete.getUrgente());
            conexao.insertOrThrow("LEMBRETES", null, value);
            return true;

        }catch (SQLException ex){
            Log.e("Erro ao inserir", ex.getMessage());
            return false;
        }
    }
    public void deleteLembrete(Lembrete lembrete){
        String[] ids = new String[]{String.valueOf(lembrete.getId())};
        conexao.delete("LEMBRETES","_id = ?",ids);
    }
    public ArrayList<Lembrete> getListLembrete(){
        ArrayList<Lembrete> lembretes = new ArrayList<>();

        Cursor cursor = conexao.query("LEMBRETES", null, null,null,null,null,"NOME COLLATE NOCASE");
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                int id = cursor.getInt(Lembrete.ID);
                String mensagem = cursor.getString(Lembrete.MENSAGEM);
                String data = cursor.getString(Lembrete.DATA);
                String urgente = cursor.getString(Lembrete.URGENTE);

                Lembrete tempLembrete = new Lembrete();
                lembretes.add(tempLembrete);
            }while (cursor.moveToNext());
        }
        return lembretes;
    }
}
