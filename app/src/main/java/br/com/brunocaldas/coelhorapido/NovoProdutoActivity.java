package br.com.brunocaldas.coelhorapido;

import android.content.Intent;
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

import br.com.brunocaldas.coelhorapido.models.Produto;
import br.com.brunocaldas.coelhorapido.services.ProdutoService;

public class NovoProdutoActivity extends AppCompatActivity {

    EditText descricao, peso;
    Button btnSalvar;
    ProdutoService produtoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_produto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        binding();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (salvarNovoProduto().getId() != null) {
                    Toast.makeText(getApplicationContext(),"Produto Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),"Falha ao cadastrar produto! Tente novamente.", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

    private void binding() {
        descricao = (EditText) findViewById(R.id.txtDescricao);
        peso = (EditText) findViewById(R.id.txtPeso);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        produtoService = new ProdutoService();
    }

    private Produto salvarNovoProduto() {
        Produto p = new Produto();
        p.setDescricao(descricao.getText().toString());
        p.setPeso(Double.parseDouble(peso.getText().toString()));

        return produtoService.salvar(p);
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
