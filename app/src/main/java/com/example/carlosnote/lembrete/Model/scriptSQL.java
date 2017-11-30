package com.example.carlosnote.lembrete.Model;

/**
 * Created by marlonourives on 13/11/2017.
 */

public class scriptSQL {
    public static String getSqlLembrete(){
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("CREATE TABLE IF NOT EXISTS LEMBRETES(");
        strBuilder.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,");
        strBuilder.append("MENSAGEM VARCHAR(100),");
        strBuilder.append("DATA VARCHAR(12),");
        strBuilder.append("URGENTE BOOLEAN");
        strBuilder.append(");");
        return strBuilder.toString();
    }
}
