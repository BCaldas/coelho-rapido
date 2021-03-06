package br.com.brunocaldas.coelhorapido.activities.motorista;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.com.brunocaldas.coelhorapido.R;
import br.com.brunocaldas.coelhorapido.models.Entrega;
import br.com.brunocaldas.coelhorapido.models.Usuario;
import br.com.brunocaldas.coelhorapido.services.EntregaService;

public class NovasEntregasActivity extends AppCompatActivity {

    ListView lstEntregas;
    EntregaService entregaService;
    List<Entrega> entregas;
    Usuario usuario;
    TextView msg;

    private static int PONTOS_REFERENCIA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novas_entregas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        binding();
        preencherListView();

        if (entregas == null || entregas.isEmpty()) {
            msg.setVisibility(View.VISIBLE);
        } else {
            msg.setVisibility(View.INVISIBLE);
        }

        lstEntregas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), PontosReferenciaActivity.class);
                intent.putExtra("entrega",entregas.get(i));
                intent.putExtra("usuario",usuario);
                startActivity(intent);
            }
        });
    }

   private void binding() {
        lstEntregas = (ListView) findViewById(R.id.lstEntregas);
        entregaService = new EntregaService();
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        msg = (TextView) findViewById(R.id.txtMsg);
   }

    private void preencherListView() {
        entregas = new ArrayList<>();
        entregas = entregaService.buscarSemMotorista();

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

    @Override
    protected void onResume() {
        lstEntregas.setAdapter(null);
        preencherListView();
        super.onResume();
    }
}
