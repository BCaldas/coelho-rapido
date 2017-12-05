package br.com.brunocaldas.coelhorapido.activities.motorista;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.brunocaldas.coelhorapido.R;
import br.com.brunocaldas.coelhorapido.models.Entrega;
import br.com.brunocaldas.coelhorapido.models.PontoReferencia;

import br.com.brunocaldas.coelhorapido.services.PontoReferenciaService;

public class PontosPassadosActivity extends AppCompatActivity {

    ListView lstPontos;
    Entrega entrega;
    List<PontoReferencia> pontos;
    PontoReferenciaService pontoReferenciaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontos_passados);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        binding();
        preencherListView();

        lstPontos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                PontoReferencia p = pontos.get(i);

                DateTime dtInicial = DateTime.now();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                p.setHorario("="+sdf.format(dtInicial.toDate()));
                pontoReferenciaService.salvar(p);

                Toast.makeText(getApplicationContext(),"Ponto registrado com sucesso!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void binding() {
        lstPontos = (ListView) findViewById(R.id.lstPontos);
        entrega = (Entrega) getIntent().getSerializableExtra("entrega");
        pontoReferenciaService = new PontoReferenciaService();
    }

    private void preencherListView() {
        pontos = pontoReferenciaService.filtrarSemData(entrega.getPontos());

        if (pontos != null && !pontos.isEmpty()) {

            List<Map<String, String>> data = new ArrayList<Map<String, String>>();
            for (PontoReferencia p : pontos) {
                    Map<String, String> datum = new HashMap<String, String>(2);
                    datum.put("nome", p.getDescricao());
                    datum.put("descricao", p.getDetalhe());
                    data.add(datum);

            }

            SimpleAdapter adapter = new SimpleAdapter(this, data,
                    android.R.layout.simple_list_item_2,
                    new String[]{"nome", "descricao"},
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
