package br.com.brunocaldas.coelhorapido.services;

import com.google.gson.Gson;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
/**
 * Created by bruno on 28/11/2017.
 */

public abstract class BaseService<T> {

    private Class<T> clazz;
    private Class<T[]> clazzes;

    private String path;

    public BaseService(String path, Class<T> clazz, Class<T[]> clazzes) {
        this.path = path;
        this.clazz = clazz;
        this.clazzes = clazzes;
    }

    public T buscarPorId(Long id){
        try {
            return new Gson().fromJson(new HttpService(path).doGet(id.toString()),clazz);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<T> buscarTodos() {

        try {
            T[] t = new Gson().fromJson(new HttpService(path).doGet(""),clazzes);
            return Arrays.asList(t);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public T salvar(T entidade) {
        String params = new Gson().toJson(entidade);

        try {
            return new Gson().fromJson(new HttpService(path).doPost(params,""),clazz);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
