package br.com.brunocaldas.coelhorapido;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.brunocaldas.coelhorapido.models.Entrega;
import br.com.brunocaldas.coelhorapido.models.PontoReferencia;

public class DetalhesConsultaActivity extends AppCompatActivity {

    Entrega entrega;
    TextView txtStatus, txtValor, txtMotorista;
    ListView lstPontos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_consulta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        binding();

        entrega = (Entrega) getIntent().getSerializableExtra("entrega");

        txtStatus.setText(entrega.isEntregaAberta() ? "A caminho" : "Entregue");

        DecimalFormat df = new DecimalFormat("0.##");
        String dx = df.format(entrega.getValor());

        txtValor.setText("R$ " + dx);
        if(entrega.getMotorista() != null) {
            txtMotorista.setText(entrega.getMotorista().getNome());
        } else {
            txtMotorista.setText("Sem informações");
        }

        preencherListView();
    }

    private void binding() {
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        txtValor = (TextView) findViewById(R.id.txtValor);
        txtMotorista = (TextView) findViewById(R.id.txtMotorista);
        lstPontos = (ListView) findViewById(R.id.lstPontos);
    }

    private void preencherListView() {
        List<PontoReferencia> pontos = entrega.getPontos();

        if (pontos != null && !pontos.isEmpty()) {

            List<Map<String, String>> data = new ArrayList<Map<String, String>>();
            for (PontoReferencia p : pontos) {

                Map<String, String> datum = new HashMap<String, String>(2);
                datum.put("nome", p.getDescricao());
                datum.put("km", "KM Pecorrido: " + p.getKmPercorrido()
                        + " | " + "KM Faltante: " + p.getKmFaltante());
                data.add(datum);
            }

            SimpleAdapter adapter = new SimpleAdapter(this, data,
                    android.R.layout.simple_list_item_2,
                    new String[]{"nome", "km"},
                    new int[]{android.R.id.text1,
                            android.R.id.text2});

            lstPontos.setAdapter(adapter);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:break;
        }
        return true;
    }

}
