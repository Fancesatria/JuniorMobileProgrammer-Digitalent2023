package com.example.jmp_fancesatria.TugasAPI.API;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.jmp_fancesatria.Global;
import com.example.jmp_fancesatria.TugasAPI.Helper.ConnHelper;
import com.example.jmp_fancesatria.TugasAPI.ModelAndResponse.Response;
import com.example.jmp_fancesatria.databinding.ActivityApiactivityBinding;

import org.json.JSONObject;

public class deleteItem extends AsyncTask<String, Void, Response> {
    ActivityApiactivityBinding bind;
    Context context;
    String url = Global.BASE_URL+"item/";

    public deleteItem(ActivityApiactivityBinding bind, Context context) {
        this.bind = bind;
        this.context = context;
    }

    @Override
    protected Response doInBackground(String... strings) {
        url += Integer.parseInt(strings[0])+"/delete";
        ConnHelper ch = new ConnHelper(url, context);
        return ch.getData();
    }

    @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);
        Log.d("delete", "onPostExecute: "+response.data);
        try{
            JSONObject jo = new JSONObject(response.data);
            if(jo.getInt("code") != 200){
                Toast.makeText(context, jo.getString("message"), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e){
            Toast.makeText(context, e.getMessage()+"", Toast.LENGTH_SHORT).show();
        }
    }
}
