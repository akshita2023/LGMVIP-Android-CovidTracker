package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private RecyclerView stateslist;
    private ArrayList<Model> modelList;
    private com.example.covidtracker.Adapter Adapter;
    private EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stateslist=findViewById(R.id.RVstates);
        search=findViewById(R.id.searching);
        modelList= new ArrayList<>();
        Adapter= new Adapter(modelList, this);
        stateslist.setLayoutManager(new LinearLayoutManager(this));
        stateslist.setAdapter(Adapter);
        getStateData();
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterState(editable.toString());
            }
        });

    }

    private void filterState(String state) {
        ArrayList<Model> filteredlist= new ArrayList<>();
        for(Model item: modelList)
        {
            if(item.getSname().toLowerCase().contains(state.toLowerCase())){
                filteredlist.add(item);
            }
        }
        if(filteredlist.isEmpty())
        {
            Toast.makeText(this,"No such state found", Toast.LENGTH_SHORT).show();

        }
        else
        {
            Adapter.filterList(filteredlist);
        }
    }

    private void getStateData() {

        String url= "https://data.covid19india.org/state_district_wise.json";
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{
                    Iterator<String> keys = response.keys();
                    keys.next();
                    while(keys.hasNext())
                    {
                        String stateName = keys.next();
                        JSONObject jsonObject= response.getJSONObject(stateName);
                        JSONObject districtdata= jsonObject.getJSONObject("districtData");
                        Iterator<String> districts =districtdata.keys();
                        while(districts.hasNext()) {
                            String districtName = districts.next();
                            JSONObject district = districtdata.getJSONObject(districtName);
                            long active = district.getLong("active");
                            long confirmed = district.getLong("confirmed");
                            long decreased = district.getLong("deceased");
                            long recovered = district.getLong("recovered");
                            JSONObject delta = district.getJSONObject("delta");
                            long confirm = delta.getLong("confirmed");
                            long decrease = delta.getLong("deceased");
                            long recover = delta.getLong("recovered");

//
                            modelList.add(new Model(stateName,districtName,active,recovered,decreased,confirmed,confirm,decrease,recover));

                        }
                        Adapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this,"Fail to extract json data!!",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this,"Fail to extract json data!!",Toast.LENGTH_SHORT).show();

            }
        }){

        };
        requestQueue.add(jsonObjectRequest);
    }
}