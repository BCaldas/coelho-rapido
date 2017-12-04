package br.com.brunocaldas.coelhorapido.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import br.com.brunocaldas.coelhorapido.NovaEntregaActivity;
import br.com.brunocaldas.coelhorapido.R;
import br.com.brunocaldas.coelhorapido.models.Entrega;

public class ConfirmacaoFragment extends Fragment {

    Entrega entrega;
    TextView txtProduto, txtUsuario, txtOrigem, txtDestino, txtDistancia, txtValor;
    Button btnConfirmar;
    NovaEntregaActivity novaEntrega;

    public ConfirmacaoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_confirmacao, container, false);

        binding(view);

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                novaEntrega.salvarEntrega();
            }
        });

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            entrega = novaEntrega.getEntrega();
            if (entrega != null) {
                if (entrega.getProduto() != null) {
                    txtProduto.setText(entrega.getProduto().getDescricao() + " - "
                            + entrega.getProduto().getPeso() + " Kg");
                }
                if (entrega.getOrigem() != null) {
                    txtValor.setText("R$ " + entrega.getValor().toString());
                    txtDistancia.setText(entrega.getOrigem().getKmFaltante().toString());
                    txtOrigem.setText(entrega.getOrigem().getDescricao());

                }
                if(entrega.getDestino() != null) {
                    txtDestino.setText(entrega.getDestino().getDescricao());
                }

                if (entrega.getCliente() != null) {
                    txtUsuario.setText(entrega.getCliente().getNome());
                }

                if (entrega.getDestino() != null &&
                        entrega.getOrigem() != null &&
                        entrega.getProduto() != null &&
                        entrega.getCliente() != null) {
                    btnConfirmar.setEnabled(true);
                } else {
                    btnConfirmar.setEnabled(false);
                }

            } else {
                btnConfirmar.setEnabled(false);
            }

        }
    }

    private void binding(View view) {
        txtProduto = (TextView) view.findViewById(R.id.txtProduto);
        txtUsuario = (TextView) view.findViewById(R.id.txtUsuario);
        txtOrigem = (TextView) view.findViewById(R.id.txtOrigem);
        txtDestino = (TextView) view.findViewById(R.id.txtDestino);
        txtDistancia = (TextView) view.findViewById(R.id.txtDistancia);
        txtValor = (TextView) view.findViewById(R.id.txtValor);
        btnConfirmar = (Button) view.findViewById(R.id.btnConfirmar);
        novaEntrega = (NovaEntregaActivity) getActivity();
    }
}