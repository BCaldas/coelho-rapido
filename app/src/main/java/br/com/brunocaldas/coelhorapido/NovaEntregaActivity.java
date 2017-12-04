package br.com.brunocaldas.coelhorapido;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import br.com.brunocaldas.coelhorapido.fragments.ListaProdutosFragment;
import br.com.brunocaldas.coelhorapido.fragments.ConfirmacaoFragment;
import br.com.brunocaldas.coelhorapido.fragments.OrigemDestinoFragment;
import br.com.brunocaldas.coelhorapido.models.Entrega;
import br.com.brunocaldas.coelhorapido.models.PontoReferencia;
import br.com.brunocaldas.coelhorapido.models.Produto;
import br.com.brunocaldas.coelhorapido.models.Usuario;
import br.com.brunocaldas.coelhorapido.services.EntregaService;

public class NovaEntregaActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    Entrega entrega;
    Usuario usuario;

    EntregaService entregaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_entrega);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new ListaProdutosFragment(), "Produto");
        adapter.addFragment(new OrigemDestinoFragment(), "Destino x Origem");
        adapter.addFragment(new ConfirmacaoFragment(), "Confirmação");
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(this);

        entregaService = new EntregaService();
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        entrega = new Entrega();
        entrega.setCliente(usuario);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        private static int count = 3;

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return count;
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return mFragmentTitleList.get(position);
        }
    }

    public void setProduto(Produto produto) {
        this.entrega.setProduto(produto);
    }

    public void setOrigem(PontoReferencia origem) {
        this.entrega.setOrigem(origem);
    }

    public void setDestino(PontoReferencia destino) {
        this.entrega.setDestino(destino);
    }

    public Entrega getEntrega() {

        if (entrega != null) {
            gerarResumo();
            return entrega;
        } else {
            return null;
        }
    }

    private void gerarResumo() {
        calcularValor();
        entrega.setEntregaAberta(true);
    }

    private void calcularValor() {
        if (entrega.getOrigem() != null) {
            entrega.setValor(entrega.getProduto().getPeso() * entrega.getOrigem().getKmFaltante());
        }
    }

    public void salvarEntrega() {
        if (entrega != null) {
            entrega = entregaService.salvar(entrega);

            if (entrega.getId() != null && entrega.getId() > 0) {
                Toast.makeText(this,"Pedido de entrega feito com sucsso!",Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this,"Erro ao efetuar pedido de entrega. Por favor, tente novamente.",Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this,"Nenhum dado fornecido!",Toast.LENGTH_SHORT).show();
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
}
