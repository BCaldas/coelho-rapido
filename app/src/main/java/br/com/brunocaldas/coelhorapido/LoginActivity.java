package br.com.brunocaldas.coelhorapido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.brunocaldas.coelhorapido.models.Usuario;
import br.com.brunocaldas.coelhorapido.services.UsuarioService;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    AutoCompleteTextView login;
    EditText senha;
    UsuarioService usuarioService;

    private static int MAIN_CLIENTE = 1;
    private static int MAIN_CAMINHONEIRO = 2;
    private static int MAIN_ADM = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding();

        usuarioService = new UsuarioService();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Usuario usuario = usuarioService.fazerLogin(login.getText().toString(),
                        senha.getText().toString());

                if (usuario != null) {
                    Intent i = null;
                    switch (usuario.getTipo()) {
                        case CLIENTE:
                            i = new Intent(getApplicationContext(), ClienteMainActivity.class);
                            break;
                        default:
                            break;
                    }

                    if (i != null) {
                        finish();
                        i.putExtra("usuario",usuario);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(),"O Tipo de usuário não pôde ser definido.",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Usuário ou Senha incorretos!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void binding() {
        btnLogin = (Button) findViewById(R.id.btnLogin);
        login = (AutoCompleteTextView) findViewById(R.id.txtLogin);
        senha = (EditText) findViewById(R.id.txtSenha);
    }
}
