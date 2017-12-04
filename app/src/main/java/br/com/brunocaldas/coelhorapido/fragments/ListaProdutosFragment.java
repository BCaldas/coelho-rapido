package br.com.brunocaldas.coelhorapido.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.com.brunocaldas.coelhorapido.NovaEntregaActivity;
import br.com.brunocaldas.coelhorapido.R;
import br.com.brunocaldas.coelhorapido.models.Produto;
import br.com.brunocaldas.coelhorapido.services.ProdutoService;

public class ListaProdutosFragment extends Fragment {

    ProdutoService produtoService;
    ListView lstProdutos;
    List<Produto> produtos;
    NovaEntregaActivity novaEntrega;

    public ListaProdutosFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_produtos,container,false);

        novaEntrega = (NovaEntregaActivity) getActivity();
        lstProdutos = (ListView) view.findViewById(R.id.lstProdutos);
        produtoService = new ProdutoService();
        preencherListView();

        lstProdutos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                novaEntrega.setProduto(produtos.get(i));

                TabLayout tabhost = (TabLayout) getActivity().findViewById(R.id.tabs);
                tabhost.getTabAt(1).select();
            }
        });

        return view;
    }

    private void preencherListView() {
        produtos = produtoService.buscarTodos();

        if (produtos != null && !produtos.isEmpty()) {

            List<Map<String, String>> data = new ArrayList<Map<String, String>>();
            for (Produto p : produtos) {

                Map<String, String> datum = new HashMap<String, String>(2);
                datum.put("produto", p.getDescricao());
                datum.put("peso", "Peso: " + p.getPeso().toString() + " Kg");
                data.add(datum);
            }

            SimpleAdapter adapter = new SimpleAdapter(getActivity(), data,
                    android.R.layout.simple_list_item_2,
                    new String[]{"produto", "peso"},
                    new int[]{android.R.id.text1,
                            android.R.id.text2});

            lstProdutos.setAdapter(adapter);
        }
    }
}