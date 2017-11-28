package br.com.brunocaldas.coelhorapido.services;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class BaseService extends AsyncTask<Void, Void, String> {
    private static final String baseUrl = "http://192.168.25.10:8080/api/";

    protected String method;
    protected String path;
    protected String params;

    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();

            try {
                URL url = new URL(baseUrl + path + "/" + params);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(method);
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                connection.setConnectTimeout(5000);
                connection.connect();

                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    resposta.append(scanner.next());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        return resposta.toString();
    }
}
