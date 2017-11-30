package com.example.carlosnote.lembrete.Model.Dominio;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by marlonourives on 13/11/2017.
 */

public class Lembrete implements Serializable{
    public static int ID=0;
    public static int MENSAGEM = 1;
    public static int DATA=2;
    public static int URGENTE = 3;

    private int id;
    private String mensagem;
    private String data;
    private Boolean urgente;

    public Lembrete(){
        setId(0);
    }
    public Lembrete(int id, String mensagem, String data, Boolean urgente){
        this.setId(id);
        this.setMensagem(mensagem);
        this.setData(data);
        this.setUrgente(urgente);

    }
    public Lembrete(int id, String mensagem, String data){
        this.setId(id);
        this.setMensagem(mensagem);
        this.setData(data);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getUrgente() {
        return urgente;
    }

    public void setUrgente(Boolean urgente) {
        this.urgente = urgente;
    }
    @Override
    public String toString(){
        return mensagem + "\n " + data;
    }
}
