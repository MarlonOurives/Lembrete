package com.example.carlosnote.lembrete;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.DatePicker;

import com.example.carlosnote.lembrete.Model.Dominio.Lembrete;
import com.example.carlosnote.lembrete.Model.Dominio.RepositorioLembretes;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtLbt;
    private EditText edtData;
    private CheckBox urgente;
    private Button btnSalvar;
    private Button btnListar;
    private Lembrete lembreteUp;
    private RepositorioLembretes lembretes;
    //private ArrayList<String> arrayLembretes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lembretes = new RepositorioLembretes(this);
        edtLbt = (EditText) findViewById(R.id.edtLembrete);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnListar = (Button) findViewById(R.id.btnListar);
        //novos inputs
        edtData  = (EditText) findViewById(R.id.edtData);
        urgente = (CheckBox) findViewById(R.id.checkUrgente);

        //habilitar o click do botão
        btnSalvar.setOnClickListener(this);

//        //iniciar array de lembretes
       // arrayLembretes = new ArrayList<String>();
        DateDialog dateClick = new DateDialog();
        edtData.setOnClickListener(dateClick);
        edtData.setOnFocusChangeListener(dateClick);
        Intent it = getIntent();
        if(it.getExtras() != null && it.getExtras().containsKey("lembrete")){
            lembreteUp = (Lembrete) it.getExtras().getSerializable("lembrete");
            preencherDados();
        }else {
            lembreteUp = new Lembrete();
        }

    }

    public void selecionarData(){
        Calendar cal = Calendar.getInstance();
        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePiker = new DatePickerDialog(this, new DateReturn(),ano,mes, dia);
        datePiker.show();
    }
    public class DateDialog implements View.OnClickListener, View.OnFocusChangeListener{
        @Override
        public void onClick(View view){
            selecionarData();
        }
        @Override
        public void onFocusChange(View view, boolean b){
            if(b){
                selecionarData();
            }
        }
    }
    public class DateReturn implements DatePickerDialog.OnDateSetListener{

        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            Calendar dateCaledar = Calendar.getInstance();
            dateCaledar.set(i,i1,i2);
            Date date = new Date();
            date = dateCaledar.getTime();
            DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
            edtData.setText(format.format(date));
        }
    }

    @Override
    public void onClick(View view) {
        if(view == btnSalvar){
            salvarDados();
        }

        if(view == btnListar){
            Intent it = new Intent(this, SalvamentoActivity.class);
            startActivity(it);
        }
    }
    public void salvarDados(){
        Lembrete lembrete = new Lembrete();
        lembrete.setId(lembreteUp.getId());
        lembrete.setMensagem(lembreteUp.getMensagem());
        lembrete.setData(lembreteUp.getData());
        lembrete.setUrgente(lembreteUp.getUrgente());

        if (lembreteUp.getId() == 0)
            lembretes.insertLembrete(lembrete);
            finish();
    }
    public void preencherDados(){
        edtLbt.setText(lembreteUp.getMensagem());
        edtData.setText(lembreteUp.getData().toString());
    }

}
