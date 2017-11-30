package com.example.carlosnote.lembrete;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.carlosnote.lembrete.Model.Dominio.Lembrete;
import com.example.carlosnote.lembrete.Model.Dominio.RepositorioLembretes;

import java.util.ArrayList;

public class SalvamentoActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener{
    private Button btnFechar;
    private ListView lsvLembretes;
    private ArrayAdapter<Lembrete> arrayLembretes;
    private RepositorioLembretes repositorioLembretes;
    private Lembrete delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salvamento);

        btnFechar = (Button) findViewById(R.id.btnFechar);
        lsvLembretes = (ListView) findViewById(R.id.lsvLembretes);

        //setando os clicks
        btnFechar.setOnClickListener(this);

        //criar o array adpater
        arrayLembretes = new ArrayAdapter<Lembrete>(this,android.R.layout.simple_list_item_1);
        lsvLembretes.setAdapter(arrayLembretes);

        repositorioLembretes = new RepositorioLembretes(this);

        lsvLembretes.setOnItemLongClickListener((AdapterView.OnItemLongClickListener) this);





        //TODO Ler as informações do BANCO

        //TODO alimetar a arrayLembretes com o contéudo da lista

    }
    @Override
    protected void onResume(){
        super.onResume();
        reload();
    }
    public void reload(){
        ArrayList<Lembrete> listLembretes = new ArrayList<>();
        listLembretes = repositorioLembretes.getListLembrete();
        arrayLembretes.clear();
        arrayLembretes.addAll(listLembretes);
    }
    @Override
    public void onClick(View view) {
        if(view == btnFechar){
            //fechar tela
            finish();
        }
    }
    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l){
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setTitle("Deletar");
        build.setMessage("Deseja deletar?");
        build.setPositiveButton("Deletar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteLembrete();
            }
        });
        build.setNeutralButton("Cancelar", null);
        build.show();
        return true;
    }
    public void deleteLembrete(){
        repositorioLembretes.deleteLembrete(delete);
        reload();
    }
}
