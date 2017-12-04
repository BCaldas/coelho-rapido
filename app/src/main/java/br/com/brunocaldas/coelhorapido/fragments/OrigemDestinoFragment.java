package br.com.brunocaldas.coelhorapido.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.joda.time.DateTime;
import java.text.SimpleDateFormat;
import java.util.Random;
import br.com.brunocaldas.coelhorapido.activities.cliente.NovaEntregaActivity;
import br.com.brunocaldas.coelhorapido.R;
import br.com.brunocaldas.coelhorapido.models.PontoReferencia;
import br.com.brunocaldas.coelhorapido.services.PontoReferenciaService;

public class OrigemDestinoFragment extends Fragment {

    PontoReferenciaService pontoReferenciaService;
    Button btnConfirmar;
    EditText origemNome, origemDescricao, destinoNome, destinoDescricao;
    NovaEntregaActivity novaEntrega;

    public OrigemDestinoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_origem_destino, container, false);
        binding(view);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (origemNome.getText().equals(destinoNome.getText())) {
                    Toast.makeText(getContext(),"A origem e o destino não podem ser iguais", Toast.LENGTH_SHORT).show();
                } else {
                    PontoReferencia origem = new PontoReferencia();
                    origem.setDescricao(origemNome.getText().toString());
                    origem.setDetalhe(origemDescricao.getText().toString());
                    origem.setKmFaltante(gerarQuilometros());
                    origem.setKmPercorrido(0.0);

                    PontoReferencia destino = new PontoReferencia();
                    destino.setDescricao(destinoNome.getText().toString());
                    destino.setDetalhe(destinoDescricao.getText().toString());
                    destino.setKmFaltante(0.0);
                    destino.setKmPercorrido(origem.getKmFaltante());
                    destino.setHorario("="+gerarPrevisaoEntrega());

                    origem = pontoReferenciaService.salvar(origem);
                    destino = pontoReferenciaService.salvar(destino);

                    novaEntrega.setOrigem(origem);
                    novaEntrega.setDestino(destino);
                }

                TabLayout tabhost = (TabLayout) getActivity().findViewById(R.id.tabs);
                tabhost.getTabAt(2).select();

            }
        });

        return view;
    }

    private void binding(View view) {
        pontoReferenciaService = new PontoReferenciaService();
        origemNome = (EditText) view.findViewById(R.id.txtOrigemNome);
        origemDescricao = (EditText) view.findViewById(R.id.txtOrigemDescricao);
        destinoNome = (EditText) view.findViewById(R.id.txtDestinoNome);
        destinoDescricao = (EditText) view.findViewById(R.id.txtDestinoDescricao);
        btnConfirmar = (Button) view.findViewById(R.id.btnConfirmar);
        novaEntrega = (NovaEntregaActivity) getActivity();
    }

    private Double gerarQuilometros() {
        return new Random().nextDouble() * 3000;
    }

    private String gerarPrevisaoEntrega() {
        DateTime dtInicial = DateTime.now();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return sdf.format(dtInicial.plusDays(gerarDiasEntrega()).toDate());
    }

    private Integer gerarDiasEntrega() {
        return new Random().nextInt(15) + 1;
    }
}