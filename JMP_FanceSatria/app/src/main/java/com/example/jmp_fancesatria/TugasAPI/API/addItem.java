package com.example.jmp_fancesatria.TugasAPI.API;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.jmp_fancesatria.Global;
import com.example.jmp_fancesatria.TugasAPI.APIActivityActivity;
import com.example.jmp_fancesatria.TugasAPI.Helper.ConnHelper;
import com.example.jmp_fancesatria.TugasAPI.ModelAndResponse.Response;
import com.example.jmp_fancesatria.databinding.ActivityInsertAndUpdateApiactivityBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class addItem extends AsyncTask<String, Void, Response> {
    Context context;
    ActivityInsertAndUpdateApiactivityBinding bind;
    String url = Global.BASE_URL+"item/create";

    public addItem(Context context, ActivityInsertAndUpdateApiactivityBinding bind) {
        this.context = context;
        this.bind = bind;
    }

    @Override
    protected Response doInBackground(String... strings) {
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("name", strings[0]);
            jsonObject.put("brand", strings[1]);
            jsonObject.put("price", Integer.parseInt(strings[2]));

        } catch (Exception e){
            e.printStackTrace();
        }
        ConnHelper ch = new ConnHelper(url, context);
        try {
            return ch.postDataB(jsonObject);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);
        Log.d("post", "onPostExecute: "+response.data);
        try{
            JSONObject jo = new JSONObject(response.data);
            if(response.data.isEmpty()){
                Toast.makeText(context, jo.getString("message"), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Data ditambahkan", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, APIActivityActivity.class));
            }
            
        } catch (JSONException e){
            Toast.makeText(context, e.getMessage()+"", Toast.LENGTH_SHORT).show();
        }
        
    }
}
