package com.jaimito.mideporte;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioButton rdbIndividual, rdbEnPareja, rdbGrupal;
    RadioGroup rdgTipo;
    TextView tvResultado;
    Spinner spDeporte;
    String resultado = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        rdbIndividual = findViewById(R.id.rdbIndividual);
        rdbGrupal = findViewById(R.id.rdbGrupal);
        rdbEnPareja = findViewById(R.id.rdbEnPareja);
        rdgTipo = findViewById(R.id.rdgTipo);
        tvResultado = findViewById(R.id.tvResultado);
        spDeporte = findViewById(R.id.spDeporte);

        rdgTipo.setOnCheckedChangeListener((group, checkedId) -> {
            int num = 0;
            if (checkedId == R.id.rdbGrupal) {
                num = 1;
            } else if (checkedId == R.id.rdbEnPareja) {
                num = 2;
            }
            cambiarSpinner(num);
        });

        spDeporte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                resultado = spDeporte.getSelectedItem().toString();
                mostrarInfo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void cambiarSpinner(int num) {
        ArrayAdapter<CharSequence> adapterDeporte;
        switch (num) {
            case 0:
                adapterDeporte = ArrayAdapter.createFromResource(this, R.array.spIndividual, android.R.layout.simple_spinner_item);
                break;
            case 1:
                adapterDeporte = ArrayAdapter.createFromResource(this, R.array.spGrupal, android.R.layout.simple_spinner_item);
                break;
            case 2:
                adapterDeporte = ArrayAdapter.createFromResource(this, R.array.spEnPareja, android.R.layout.simple_spinner_item);
                break;
            default:
                adapterDeporte = null;
        }

        if (adapterDeporte != null) {
            adapterDeporte.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spDeporte.setAdapter(adapterDeporte);
            spDeporte.setSelection(0);
        }
    }

    private void mostrarInfo() {
        tvResultado.setText(resultado);
    }
}
