package br.com.brunocaldas.coelhorapido.activities.cliente;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.brunocaldas.coelhorapido.R;
import br.com.brunocaldas.coelhorapido.models.Entrega;
import br.com.brunocaldas.coelhorapido.models.Usuario;
import br.com.brunocaldas.coelhorapido.services.EntregaService;

public class ConsultaActivity extends AppCompatActivity {

    ListView lstConsulta;
    EntregaService entregaService;
    List<Entrega> entregas;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        binding();

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        preencherListView();

        lstConsulta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), DetalhesConsultaActivity.class);
                intent.putExtra("entrega",entregas.get(i));
                startActivity(intent);
            }
        });
    }

    private void binding() {
        lstConsulta = (ListView) findViewById(R.id.lstConsulta);
        entregaService = new EntregaService();
    }

    private void preencherListView() {
        entregas = entregaService.buscarPorUsuario(usuario);

        if (entregas != null && !entregas.isEmpty()) {

            List<Map<String, String>> data = new ArrayList<Map<String, String>>();
            for (Entrega e : entregas) {

                Map<String, String> datum = new HashMap<String, String>(2);
                datum.put("produto", e.getProduto().getDescricao());
                datum.put("locais", "ORIGEM: " + e.getOrigem().getDescricao()
                        + " | " + "DESTINO: " + e.getDestino().getDescricao());
                data.add(datum);
            }

            SimpleAdapter adapter = new SimpleAdapter(this, data,
                    android.R.layout.simple_list_item_2,
                    new String[]{"produto", "locais"},
                    new int[]{android.R.id.text1,
                            android.R.id.text2});

            lstConsulta.setAdapter(adapter);
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
