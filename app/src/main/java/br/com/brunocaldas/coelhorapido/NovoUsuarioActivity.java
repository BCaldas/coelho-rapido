package br.com.brunocaldas.coelhorapido;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.brunocaldas.coelhorapido.enums.ETipoUsuario;
import br.com.brunocaldas.coelhorapido.models.Usuario;
import br.com.brunocaldas.coelhorapido.services.UsuarioService;

public class NovoUsuarioActivity extends AppCompatActivity {

    EditText nome, email, login, senha;
    Spinner tipoUsuario;
    Button btnSalvar;
    UsuarioService usuarioService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        binding();
        preencherSpinnerTipoUsuario();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (salvarUsuario().getId() != null) {
                    Toast.makeText(getApplicationContext(),"Usuário Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),"Falha ao cadastrar usuário! Tente novamente.", Toast.LENGTH_LONG).show();
                    finish();
                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                }
            }
        });

    }

    private void binding() {
        nome = (EditText) findViewById(R.id.txtNome);
        email = (EditText) findViewById(R.id.txtEmail);
        login = (EditText) findViewById(R.id.txtLogin);
        senha = (EditText) findViewById(R.id.txtSenha);
        tipoUsuario = (Spinner) findViewById(R.id.spnTipoUsuario);
        btnSalvar = (Button) findViewById(R.id.btnCadastrar);
        usuarioService = new UsuarioService();
    }

    private void preencherSpinnerTipoUsuario() {
        List<String> list = new ArrayList<>();

        list.add(ETipoUsuario.CLIENTE.toString());
        list.add(ETipoUsuario.MOTORISTA.toString());
        list.add(ETipoUsuario.ADMIN.toString());

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoUsuario.setAdapter(dataAdapter);
    }

    private Usuario salvarUsuario() {
        Usuario u = new Usuario();
        u.setNome(nome.getText().toString());
        u.setEmail(email.getText().toString());
        u.setLogin(login.getText().toString());
        u.setSenha(senha.getText().toString());
        u.setAtivo(true);
        u.setTipo(ETipoUsuario.valueOf(tipoUsuario.getSelectedItem().toString()));

        u = usuarioService.salvar(u);

        return u;
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
