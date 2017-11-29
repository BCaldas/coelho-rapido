package br.com.brunocaldas.coelhorapido;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

import br.com.brunocaldas.coelhorapido.models.Usuario;
import br.com.brunocaldas.coelhorapido.services.UsuarioService;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    AutoCompleteTextView login;
    EditText senha;
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
                        Usuario usuario = usuarioService.fazerLogin(login.getText().toString(),
                                senha.getText().toString());

                    Toast.makeText(getApplicationContext(),usuario.getNome(),Toast.LENGTH_LONG).show();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();

                } catch (ExecutionException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                } catch (NullPointerException e) {
                    Toast.makeText(getApplicationContext(),"Usuário não encontrado",Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
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
