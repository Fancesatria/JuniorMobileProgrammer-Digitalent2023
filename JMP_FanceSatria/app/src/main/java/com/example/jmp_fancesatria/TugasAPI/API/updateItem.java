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

import org.json.JSONObject;

public class updateItem extends AsyncTask<String, Void, Response> {
    ActivityInsertAndUpdateApiactivityBinding bind;
    Context context;
    String url = Global.BASE_URL+"item/";

    public updateItem(ActivityInsertAndUpdateApiactivityBinding bind, Context context) {
        this.bind = bind;
        this.context = context;
    }

    @Override
    protected Response doInBackground(String... strings) {
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("name", strings[1]);
            jsonObject.put("brand", strings[2]);
            jsonObject.put("price", Integer.parseInt(strings[3]));

        } catch (Exception e){
            e.printStackTrace();
        }
        url += Integer.parseInt(strings[0])+"/update";
        ConnHelper ch = new ConnHelper(url, context);
        return ch.putData(jsonObject);
    }

    @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);
        Log.d("update", "onPostExecute: "+response.data);
        try {
            JSONObject jo = new JSONObject(response.data);
            if(jo.getInt("code") != 200){
                Toast.makeText(context, jo.getString("message"), Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(context, "Data berhasil diubah", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, APIActivityActivity.class));
            }

        } catch (Exception e){
            Toast.makeText(context, e.getMessage()+"", Toast.LENGTH_SHORT).show();
        }
    }
}
