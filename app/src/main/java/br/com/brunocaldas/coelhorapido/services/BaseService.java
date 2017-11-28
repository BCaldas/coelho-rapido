package br.com.brunocaldas.coelhorapido.services;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public abstract class BaseService<T> extends AsyncTask<Void, Void, String> {

    private static final String baseUrl = "http://192.168.25.10:8080/api/";

    private String method;
    private String path;
    private String params;
    private JSONObject postParams;

    public BaseService(String path) {
        this.path = path;
    }

    protected String doGet(String params) throws ExecutionException, InterruptedException {
        this.method = "GET";
        this.params = params;
        return this.execute().get();
    }

    protected String doPost(JSONObject postParams, String params) throws ExecutionException, InterruptedException {
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
//                connection.setDoInput(true);
            connection.setRequestMethod(method);
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            connection.setConnectTimeout(5000);

            if (method.equals("POST")) {
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Length", String.valueOf(postParams.toString().getBytes().length));
                connection.setFixedLengthStreamingMode(postParams.toString().getBytes().length);

                OutputStream output = new BufferedOutputStream(connection.getOutputStream());
                output.write(postParams.toString().getBytes());
                output.flush();
                output.close();

                InputStreamReader in = new InputStreamReader(connection.getInputStream());
                BufferedReader br = new BufferedReader(in);
                String text = "";
                while ((text = br.readLine()) != null) {
                    resposta.append(text);
                }

                connection.connect();

            } else if (method.equals("GET")) {
                connection.connect();
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    resposta.append(scanner.next());
                }
            }

            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resposta.toString();
    }
}
