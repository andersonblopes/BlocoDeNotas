package com.example.ander.blocodenotas;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etNota;
    Button btnAnotar;
    ListView lvNotas;

    private ArrayList<String> notas = new ArrayList<String>();
    ArrayAdapter<String> adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNota = (EditText) findViewById(R.id.etNota);
        btnAnotar = (Button) findViewById(R.id.btAnotar);
        lvNotas = (ListView) findViewById(R.id.lvNotas);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, notas);
        lvNotas.setAdapter(adapter);

        btnAnotar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = etNota.getText().toString();
                if(!texto.isEmpty()){
                    etNota.setText("");
                    etNota.findFocus();
                    notas.add(texto);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        lvNotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                adb.setTitle("Nota");
                adb.setMessage("Você deseja apagar esta nota?");
                final int positionToRemove = position;
                adb.setNegativeButton("Não", null);
                adb.setPositiveButton("Sim", new AlertDialog.OnClickListener(){
                    public void onClick(DialogInterface dialog, int which){
                        notas.remove(positionToRemove);
                        adapter.notifyDataSetChanged();
                    }
                });
                adb.show();
            }
        });
    }
}
