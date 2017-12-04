package br.com.brunocaldas.coelhorapido.activities.motorista;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.com.brunocaldas.coelhorapido.R;
import br.com.brunocaldas.coelhorapido.activities.cliente.NovaEntregaActivity;
import br.com.brunocaldas.coelhorapido.models.Entrega;
import br.com.brunocaldas.coelhorapido.models.PontoReferencia;
import br.com.brunocaldas.coelhorapido.models.Usuario;
import br.com.brunocaldas.coelhorapido.services.EntregaService;
import br.com.brunocaldas.coelhorapido.services.PontoReferenciaService;

public class PontosReferenciaActivity extends AppCompatActivity {

    PontoReferenciaService pontoReferenciaService;
    EntregaService entregaService;

    Button btnSalvar, btnFinalizar;
    EditText nome, descricao, kmPercorridos;

    Usuario usuario;
    Entrega entrega;
    List<PontoReferencia> pontos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontos_referencia);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        binding();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nome.getText().toString().isEmpty()
                        || descricao.getText().toString().isEmpty()
                        || kmPercorridos.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Por favor, preencha todos os campos",Toast.LENGTH_SHORT).show();
                } else {
                    PontoReferencia p = new PontoReferencia();
                    p.setDescricao(nome.getText().toString());
                    p.setDetalhe(descricao.getText().toString());
                    p.setKmPercorrido(Double.parseDouble(kmPercorridos.getText().toString()));
                    p.setKmFaltante(entrega.getOrigem().getKmFaltante() - p.getKmPercorrido());

                    p = pontoReferenciaService.salvar(p);
                    pontos.add(p);

                    nome.setText("");
                    descricao.setText("");
                    kmPercorridos.setText("");

                    Toast.makeText(getApplicationContext(),"Ponto de referência cadastrado com sucesso!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrega.setPontos(pontos);
                entregaService.salvar(entrega);
                Toast.makeText(getApplicationContext(),"Entrega atualizada e transporte iniciado. Boa viagem!",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void binding() {
        pontoReferenciaService = new PontoReferenciaService();
        entregaService = new EntregaService();

        nome = (EditText) findViewById(R.id.txtNome);
        descricao = (EditText) findViewById(R.id.txtDescricao);
        kmPercorridos = (EditText) findViewById(R.id.txtKm);

        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnFinalizar = (Button) findViewById(R.id.btnFinalizar);

        entrega = (Entrega) getIntent().getSerializableExtra("entrega");
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
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
