package br.com.brunocaldas.coelhorapido;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.brunocaldas.coelhorapido.models.Produto;
import br.com.brunocaldas.coelhorapido.services.ProdutoService;

public class NovaEntregaActivity extends AppCompatActivity {

    ProdutoService produtoService;

    ListView lstProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_entrega);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        produtoService = new ProdutoService();
        binding();
        preencherListView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void binding() {
        lstProdutos = (ListView) findViewById(R.id.lstProdutos);
    }

    private void preencherListView() {

        List<Produto> produtos = produtoService.buscarTodos();

        if (produtos != null && !produtos.isEmpty()) {

            List<Map<String, String>> data = new ArrayList<Map<String, String>>();
            for (Produto p : produtos) {

                Map<String, String> datum = new HashMap<String, String>(2);
                datum.put("produto", p.getDescricao());
                datum.put("peso", "Peso: " + p.getPeso().toString() + " Kg");
                data.add(datum);
            }

            SimpleAdapter adapter = new SimpleAdapter(this, data,
                    android.R.layout.simple_list_item_2,
                    new String[]{"produto", "peso"},
                    new int[]{android.R.id.text1,
                            android.R.id.text2});

            lstProdutos.setAdapter(adapter);
        }
    }

}
