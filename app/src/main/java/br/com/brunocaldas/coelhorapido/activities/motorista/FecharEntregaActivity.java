package br.com.brunocaldas.coelhorapido.activities.motorista;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.brunocaldas.coelhorapido.R;
import br.com.brunocaldas.coelhorapido.models.Entrega;
import br.com.brunocaldas.coelhorapido.models.Usuario;
import br.com.brunocaldas.coelhorapido.services.EntregaService;

public class FecharEntregaActivity extends AppCompatActivity {

    ListView lstEntregas;
    EntregaService entregaService;
    List<Entrega> entregas;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fechar_entrega);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        binding();
        preencherListView();

        lstEntregas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                entregas.get(i).setEntregaAberta(false);
                Entrega entrega = entregaService.salvar(entregas.get(i));

                if (entrega != null) {
                    Toast.makeText(getApplicationContext(),"Entrega encerrada com sucesso!",Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),"Falha ao encerrar entrega! Tente novamente.",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void binding() {
        lstEntregas = (ListView) findViewById(R.id.lstEntregas);
        entregaService = new EntregaService();
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
    }

    private void preencherListView() {
        entregas = new ArrayList<>();
        entregas = entregaService.buscarAbertasPorMotorista(usuario);

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

            lstEntregas.setAdapter(adapter);
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
