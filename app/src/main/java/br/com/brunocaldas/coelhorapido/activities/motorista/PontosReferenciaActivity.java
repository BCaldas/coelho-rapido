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
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
    TextView kmFaltantes;

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

        DecimalFormat df = new DecimalFormat("0.##");
        String km = df.format(entrega.getOrigem().getKmFaltante());
        kmFaltantes.setText("Km's restantes para o fim da viagem: " + km);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nome.getText().toString().isEmpty()
                        || descricao.getText().toString().isEmpty()
                        || kmPercorridos.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Por favor, preencha todos os campos",Toast.LENGTH_SHORT).show();
                } else {
                    PontoReferencia p = new PontoReferencia();

                    Double kmFaltante = entrega.getOrigem().getKmFaltante() - Double.parseDouble(kmPercorridos.getText().toString());

                    if (kmFaltante < 0) {
                        Toast.makeText(getApplicationContext(),"Os kilômetros faltantes não podem ser negativos",Toast.LENGTH_LONG).show();
                    } else {
                        p.setDescricao(nome.getText().toString());
                        p.setDetalhe(descricao.getText().toString());
                        p.setKmPercorrido(Double.parseDouble(kmPercorridos.getText().toString()));
                        p.setKmFaltante(entrega.getOrigem().getKmFaltante() - p.getKmPercorrido());
                        p = pontoReferenciaService.salvar(p);

                        pontos.add(p);
                        Toast.makeText(getApplicationContext(),"Ponto de referência cadastrado com sucesso!",Toast.LENGTH_SHORT).show();

                        DecimalFormat df = new DecimalFormat("0.##");
                        String km = df.format(p.getKmFaltante());
                        kmFaltantes.setText("Km's restantes para o fim da viagem: " + km);

                        nome.setText("");
                        descricao.setText("");
                        kmPercorridos.setText("");
                    }
                }
            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrega.setPontos(pontos);
                entrega.setMotorista(usuario);
                entregaService.salvar(entrega);
                Toast.makeText(getApplicationContext(),"Entrega atualizada e transporte iniciado. Boa viagem!",Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

    private void binding() {
        pontoReferenciaService = new PontoReferenciaService();
        entregaService = new EntregaService();
        pontos = new ArrayList<>();

        nome = (EditText) findViewById(R.id.txtNome);
        descricao = (EditText) findViewById(R.id.txtDescricao);
        kmPercorridos = (EditText) findViewById(R.id.txtKm);
        kmFaltantes = (TextView) findViewById(R.id.txtKmFaltantes);

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
