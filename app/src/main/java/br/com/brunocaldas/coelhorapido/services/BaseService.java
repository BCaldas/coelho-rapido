package br.com.brunocaldas.coelhorapido.services;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;

import br.com.brunocaldas.coelhorapido.models.Usuario;

/**
 * Created by bruno on 28/11/2017.
 */

public abstract class BaseService<T> {

    private Class<T> clazz;

    private String path;

    public BaseService(String path, Class<T> clazz) {
        this.path = path;
        this.clazz = clazz;
    }

    public T buscarPorId(Integer id){
        try {
            return new Gson().fromJson(new HttpService(path).doGet(id.toString()),clazz);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
