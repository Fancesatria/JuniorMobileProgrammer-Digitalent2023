package com.example.jmp_fancesatria.TugasAPI.Helper;
import android.content.Context;
import android.util.Log;


import com.example.jmp_fancesatria.TugasAPI.ModelAndResponse.Response;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ConnHelper {
    public String urll = "";
    public final int timeout = 15000;
    Context context;
    //public SPHelper sp;

    public ConnHelper(String urll, Context context) {
        this.urll = urll;
        this.context = context;
    }

    public ConnHelper(String urll) {
        this.urll = urll;
    }

    public Response getData(){
        //Default response
        Response response = new Response(404,"");
        try{
            //konfigurasi koneksi utk ambil respone
            //sp = new SPHelper(context);
            URL url = new URL(urll);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            //con.setRequestProperty("Authorization","Bearer "+sp.getToken());
            con.setReadTimeout(timeout);
            con.setConnectTimeout(timeout);
            con.connect();

            //Ubah response ke string
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;

            while  ((line = br.readLine()) != null){
                sb.append(line+"\n");
            }

            //ambil response
            response.data = sb.toString();
            response.statusCode = con.getResponseCode();
            br.close();

        } catch (Exception e){
            Log.d("errorcon", e.getMessage());
        }
        return response;
    }

    public Response getDataB() throws IOException {
        //Default response
        Response response = new Response(404,"");


        URL url = new URL(urll);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        //con.setRequestProperty("Authorization","Bearer "+ sp.getToken());
        con.setReadTimeout(timeout);
        con.setConnectTimeout(timeout);
        con.connect();

        //response ke string
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder();

        while ((line = bufferedReader.readLine()) != null){
            sb.append(line+"\n");
        }
        response.data = sb.toString();
        response.statusCode = con.getResponseCode();
        bufferedReader.close();

        Log.d("errorcon", "A:"+con.getResponseCode()+",  B:"+con.getErrorStream()+"");
        return response;
    }

    public Response getDataC(){
        //Default response
        Response response = new Response(404,"");
        try{
            //konfigurasi koneksi utk ambil respone

            URL url = new URL(urll);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            //con.setRequestProperty("Authorization","Bearer "+sp.getToken());
            con.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            con.setReadTimeout(timeout);
            con.setConnectTimeout(timeout);
            con.connect();

            //Ubah response ke string
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;

            while  ((line = br.readLine()) != null){
                sb.append(line+"\n");
            }

            //ambil response
            response.data = sb.toString();
            response.statusCode = con.getResponseCode();
            br.close();

        } catch (Exception e){
            Log.d("errorcon", e.getMessage());
        }
        return response;
    }


    public Response postData(JSONObject body){
        Response response = new Response(404, "");

        try{
            URL url = new URL(urll);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(body.toString());
            dos.flush();
            dos.close();

            conn.setReadTimeout(timeout);
            conn.connect();
//            Response to String

            Reader in;
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line+"\n");
            }
            response.data =sb.toString();
            response.statusCode = conn.getResponseCode();
            br.close();
        } catch (Exception e){
            Log.d("errorcon", e.getMessage());
        }

        return response;
    }

    public Response postDataB(JSONObject body) throws IOException {
        Response response = new Response(404, "");

        try {
            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body.toString());

            Request request = new Request.Builder()
                    .url(urll)
                    .post(requestBody)
                    .build();

            okhttp3.Response okHttpResponse = client.newCall(request).execute();

            if (okHttpResponse.isSuccessful()) {
                String responseData = okHttpResponse.body().string();
                JSONObject jsonResponse = new JSONObject(responseData); // Konversi responseData menjadi JSONObject
                response.data = jsonResponse.toString(); // Simpan sebagai String untuk tetap sesuai dengan struktur Response Anda
                response.statusCode = okHttpResponse.code();
            } else {
                // Ganti dengan penanganan kesalahan sesuai kebutuhan Anda
                response.statusCode = okHttpResponse.code();
                response.data = "Error: " + okHttpResponse.message();
            }
        } catch (Exception e) {
            // Ganti dengan penanganan kesalahan sesuai kebutuhan Anda
            e.printStackTrace();
            response.data = "Error: " + e.getMessage();
        }

        return response;
    }

    public Response putData(JSONObject body){
        Response response = new Response(404,"");

        try{
            //konfigurasi koneksi
            URL url = new URL(urll);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type","application/json; charset=UTF-8");

            DataOutputStream dos = new DataOutputStream(con.getOutputStream());
            dos.writeBytes(body.toString());
            dos.flush();
            dos.close();

            con.setReadTimeout(timeout);
            con.setConnectTimeout(timeout);
            con.connect();

            //uah ressponse ke sting
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;

            //load data dengan looping
            while((line = br.readLine()) != null){
                sb.append(line+"\n");
            }

            response.data = sb.toString();
            response.statusCode = con.getResponseCode();
            br.close();
        } catch (Exception e){
            Log.d("errorcon", e.getMessage());
        }

        return response;
    }



    public Response delData(){
        Response response = new Response(404, "");

        try {
            URL url = new URL(urll);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestMethod("DELETE");
            con.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            con.setReadTimeout(timeout);
            con.setConnectTimeout(timeout);
            con.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;

            while((line = br.readLine()) != null){
                sb.append(line+"\n");
            }

            response.data = sb.toString();
            response.statusCode = con.getResponseCode();
            br.close();

        } catch (Exception e){
            Log.d("errorcon", e.getMessage());
        }

        return response;
    }


    public Response getauth(String token){
        Response response = new Response(404,"");
        try {
            //ambil response
            URL url = new URL(urll);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization","Bearer "+token);
            con.setReadTimeout(timeout);
            con.setConnectTimeout(timeout);
            con.connect();

            //response ke string
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null){
                sb.append(line+"\n");
            }
            response.data = sb.toString();
            response.statusCode = con.getResponseCode();
            bufferedReader.close();
        }catch (Exception e) {
            Log.d("errorcon",e.getMessage());
        }
        return response;
    }
}






