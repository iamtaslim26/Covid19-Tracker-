package com.kgec.covid19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.AlignmentSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AffectedCountryActivity extends AppCompatActivity {

    private EditText search_by_country;
    //private RecyclerView recyclerView;
    private SimpleArcLoader simpleArcLoader;

    private ListView listView;

    public static List<CountryModel> list_affected=new ArrayList<>();
    private CountryModel countryModel;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affected_country);

        search_by_country = findViewById(R.id.edtSearch);
        listView=findViewById(R.id.listView);
//        recyclerView=findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);

        getSupportActionBar().setTitle("Country");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowCustomEnabled(true);

        simpleArcLoader = findViewById(R.id.loader_1);

        simpleArcLoader.start();


      //  list_affected = new ArrayList<>();

        FetchData();

        search_by_country.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                customAdapter.getFilter().filter(s);
                customAdapter.notifyDataSetChanged();            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(getApplicationContext(),ShowCountryDataActivity.class);
                intent.putExtra("position",position);
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){

            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void FetchData() {

        RequestQueue requestQueue=Volley.newRequestQueue(this);
        String url="https://corona.lmao.ninja/v2/countries";

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONArray jsonArray=new JSONArray(response.toString());

                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject jsonObject=jsonArray.getJSONObject(i);

                        String countryName=jsonObject.getString("country");
                        String todayCases=jsonObject.getString("todayCases");
                        String deaths=jsonObject.getString("deaths");
                        String todayDeaths=jsonObject.getString("todayDeaths");
                        String todayRecovered=jsonObject.getString("todayRecovered");
                        String recovered=jsonObject.getString("recovered");
                        String active=jsonObject.getString("active");
                        String tests=jsonObject.getString("tests");
                        String critical=jsonObject.getString("critical");
                        String cases=jsonObject.getString("cases");

                        JSONObject object=jsonObject.getJSONObject("countryInfo");
                        String flag=object.getString("flag");

                        countryModel=new CountryModel(flag,countryName,todayCases,deaths,todayDeaths,todayRecovered,recovered,active,tests,critical,cases);
                        list_affected.add(countryModel);


                    }

                    customAdapter=new CustomAdapter(AffectedCountryActivity.this,list_affected);
                    listView.setAdapter(customAdapter);
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);


                } catch (JSONException e) {
                    e.printStackTrace();
                    simpleArcLoader.start();
                    simpleArcLoader.setVisibility(View.VISIBLE);

                }





            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(AffectedCountryActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }

}




//
//        JSONArray jsonArray=new JSONArray(response.toString());
//
//        for (int i=0;i<jsonArray.length();i++){
//
//        JSONObject jsonObject=jsonArray.getJSONObject(i);
//
//        String countryName=jsonObject.getString("country");
//        String todayCases=jsonObject.getString("todayCases");
//        String deaths=jsonObject.getString("deaths");
//        String todayDeaths=jsonObject.getString("todayDeaths");
//        String todayRecovered=jsonObject.getString("todayRecovered");
//        String recovered=jsonObject.getString("recovered");
//        String active=jsonObject.getString("active");
//        String tests=jsonObject.getString("tests");
//        String critical=jsonObject.getString("critical");
//
//
//        JSONObject object=jsonObject.getJSONObject("countryInfo");
//        String flag=object.getString("flag");
//
//        countryModel=new CountryModel(flag,countryName,todayCases,deaths,todayDeaths,todayRecovered,recovered,active,tests,critical);
//
//
