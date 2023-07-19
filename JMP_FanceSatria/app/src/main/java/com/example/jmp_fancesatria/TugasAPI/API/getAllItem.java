package com.example.jmp_fancesatria.TugasAPI.API;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.jmp_fancesatria.Global;
import com.example.jmp_fancesatria.TugasAPI.Adapter.ItemAdapter;
import com.example.jmp_fancesatria.TugasAPI.Helper.ConnHelper;
import com.example.jmp_fancesatria.TugasAPI.ModelAndResponse.ModelItem;
import com.example.jmp_fancesatria.TugasAPI.ModelAndResponse.Response;
import com.example.jmp_fancesatria.databinding.ActivityApiactivityBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class getAllItem extends AsyncTask<String, Void, Response> {
    Context context;
    String url = Global.BASE_URL+"item";
    ActivityApiactivityBinding bind;
    List<ModelItem> data = new ArrayList<>();

    public getAllItem(Context context, ActivityApiactivityBinding bind) {
        this.context = context;
        this.bind = bind;
    }

    @Override
    protected Response doInBackground(String... strings) {
        ConnHelper ch = new ConnHelper(url, context);
        return ch.getData();
    }

    @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);
        Log.d("data", "onPostExecute: "+response.data);
        bind.rv.setHasFixedSize(true);
        bind.rv.setLayoutManager(new LinearLayoutManager(context));

        if(response.data == null || response.data == ""){
            Toast.makeText(context, "null", Toast.LENGTH_SHORT).show();
        } else {
            try{
                JSONObject object = new JSONObject(response.data);

                JSONArray ja = object.getJSONArray("items");
                for(int i = 0; i<ja.length();i++){
                    JSONObject jo = ja.getJSONObject(i);

                    data.add(new ModelItem(jo.getInt("id"), jo.getString("name"), jo.getString("brand"), jo.getInt("price")));
                }

            } catch (JSONException e){
                throw new RuntimeException(e);
            }
        }
        bind.rv.setAdapter(new ItemAdapter(context, data));
    }
}
