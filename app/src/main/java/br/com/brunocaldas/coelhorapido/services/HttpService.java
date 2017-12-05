package br.com.brunocaldas.coelhorapido.services;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class HttpService extends AsyncTask<Void, Void, String> {

//    private static final String baseUrl = "http://192.168.25.10:8080/api/";
    private static final String baseUrl = "https://service.davesmartins.com.br/api/";


    private String method;
    private String path;
    private String params;
    private String postParams;

    public HttpService(String path) {
        this.path = path;
    }

    public String doGet(String params) throws ExecutionException, InterruptedException {
        this.method = "GET";
        this.params = params;
        return this.execute().get();
    }

    public String doPost(String postParams, String params) throws ExecutionException, InterruptedException {
        this.method = "POST";
        this.params = params;
        this.postParams = postParams;
        return this.execute().get();
    }

    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();
        try {
            URL url = new URL(baseUrl + path + "/" + params);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setConnectTimeout(5000);

            if (method.equals("POST")) {
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Length", String.valueOf(postParams.getBytes().length));
                connection.setFixedLengthStreamingMode(postParams.getBytes().length);

                OutputStream output = new BufferedOutputStream(connection.getOutputStream());
                output.write(postParams.getBytes());
                output.flush();
                output.close();
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String text = "";
            JSONObject json = new JSONObject();
            while ((text = br.readLine()) != null) {
                resposta.append(text);

            }
            connection.connect();
            connection.disconnect();
            br.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resposta.toString();
    }
}
