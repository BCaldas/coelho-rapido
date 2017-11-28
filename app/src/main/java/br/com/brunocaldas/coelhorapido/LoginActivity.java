package br.com.brunocaldas.coelhorapido;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import br.com.brunocaldas.coelhorapido.services.BaseService;
import br.com.brunocaldas.coelhorapido.services.UsuarioService;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    UsuarioService usuarioService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding();

        usuarioService = new UsuarioService();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Usuario usuario = usuarioService.buscarPorId(1);
                    Toast.makeText(getApplicationContext(),usuario.getCadastro(),Toast.LENGTH_LONG).show();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void binding() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }
}
