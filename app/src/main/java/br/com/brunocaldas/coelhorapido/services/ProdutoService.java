package br.com.brunocaldas.coelhorapido.services;

import br.com.brunocaldas.coelhorapido.models.Produto;

/**
 * Created by bruno on 28/11/2017.
 */

public class ProdutoService extends BaseService<Produto> {

    private static final String path = "produtos";

    public ProdutoService() {
        super(path,Produto.class,Produto[].class);
    }
}
