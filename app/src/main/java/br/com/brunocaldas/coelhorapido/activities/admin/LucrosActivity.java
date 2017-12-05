package br.com.brunocaldas.coelhorapido.activities.admin;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

import br.com.brunocaldas.coelhorapido.R;
import br.com.brunocaldas.coelhorapido.services.EntregaService;

public class LucrosActivity extends AppCompatActivity {

    EntregaService entregaService;
    TextView txtLucros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucros);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        binding();

        DecimalFormat df = new DecimalFormat("0.##");
        String valor = df.format(entregaService.retornarLucros());

        txtLucros.setText("R$ " + valor);
    }

    private void binding() {
        entregaService = new EntregaService();
        txtLucros = (TextView) findViewById(R.id.txtLucros);
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
