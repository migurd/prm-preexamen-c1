package com.example.preexamen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class ReciboNominaActivity extends AppCompatActivity {
    private TextView outFolio;
    private TextView outNombre;
    private EditText inHorasTrabajadas;
    private EditText inHorasExtras;
    private RadioButton rbAux, rbAlb, rbIng;
    private TextView outSubtotal, outImpuesto, outTotal;
    private Button btnCalcular, btnLimpiar, btnRegresar;
    private ReciboNomina reb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recibo_nomina);

        // init
        init();

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    reb.setHorasTrabajoNormal(Double.parseDouble(inHorasTrabajadas.getText().toString()));
                    reb.setHorasTrabajoExtra(Double.parseDouble(inHorasExtras.getText().toString()));

                    if (reb.getHorasTrabajoNormal() < 0 || reb.getHorasTrabajoExtra() < 0)
                        throw new IllegalArgumentException("Inserte valores positivos, por favor.");

                    int rbSelected = rbAux.isSelected() ? 1 : rbAlb.isSelected() ? 2 : rbIng.isSelected() ? 3 : 0;

                    if (rbSelected == 0 )
                        throw new IllegalArgumentException("Elija un puesto, por favor.");

                    reb.setPuesto(rbSelected);

                    outSubtotal.setText(doubleToNominated(reb.calcularSubtotal()));
                    outImpuesto.setText(doubleToNominated(reb.calcularImpuesto()));
                    outTotal.setText(doubleToNominated(reb.calcularTotal()));
                } catch (IllegalArgumentException error) {
                    Toast.makeText(ReciboNominaActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outSubtotal.setText("");
                outImpuesto.setText("");
                outTotal.setText("");
                rbAux.setActivated(false);
                rbAlb.setActivated(false);
                rbIng.setActivated(false);
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void init() {
        outFolio = findViewById(R.id.outFolio);
        outNombre = findViewById(R.id.outNombre);
        inHorasTrabajadas = findViewById(R.id.inHorasTrabajadas);
        inHorasExtras = findViewById(R.id.inHorasExtras);
        rbAlb = findViewById(R.id.rbAlb);
        rbAux = findViewById(R.id.rbAuxiliar);
        rbIng = findViewById(R.id.rbIng);
        outSubtotal = findViewById(R.id.outSubtotal);
        outImpuesto = findViewById(R.id.outImpuesto);
        outTotal = findViewById(R.id.outTotalPagar);

        reb = new ReciboNomina();

        //        set values for folio and name
        String name = getIntent().getStringExtra("nombre");
        int folio = getIntent().getIntExtra("folio", 1);

        reb.setNombre(name);
        reb.setNumRecibo(reb.generateId());
        outNombre.setText(reb.getNombre());
        outFolio.setText(Integer.toString(reb.getNumRecibo()));
    }

    public String doubleToNominated(double n) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(n);
    }
}