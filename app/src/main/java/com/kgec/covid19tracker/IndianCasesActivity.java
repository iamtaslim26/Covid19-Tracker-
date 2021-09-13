package com.kgec.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class IndianCasesActivity extends AppCompatActivity {

    private TextView cases,recovered,deaths,discharged;
    private RecyclerView recyclerView;
    private PieChart mPieChart;
    private IndianModelAdapter indianModelAdapter;
    public  List<IndiaModel>list;
  //  public  List<String>list2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indian_cases);

        cases=findViewById(R.id.no_of_cases);
        recovered=findViewById(R.id.no_of_recovered);
        deaths=findViewById(R.id.no_of_deaths);
      //  discharged=findViewById(R.id.no_of_discharged);
        recyclerView=findViewById(R.id.state_wise_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);



        mPieChart =findViewById(R.id.piechart1);
        list=new ArrayList<>();
       // list2=new ArrayList<>();

        //list2.add("name");
        //list2.add("Taslim");

        indianModelAdapter=new IndianModelAdapter(IndianCasesActivity.this,list);

        recyclerView.setAdapter(indianModelAdapter);


        FetchTotalIndianCases();
        FetchStateWiseData();
    }

    private void FetchStateWiseData() {

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        String url="https://api.rootnet.in/covid19-in/stats/latest";

     /*   StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    JSONArray jsonArray=jsonObject.getJSONArray("regional");

                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject object=jsonArray.getJSONObject(i);

                        String state_name=object.getString("loc");
                        String no_recovered=object.getString("discharged");
                        String no_deaths=object.getString("deaths");
                        String no_cases=object.getString("totalConfirmed");

//                        IndiaModel indiaModel= new IndiaModel(state_name,no_deaths,no_recovered,no_cases);
//                        list.add(indiaModel);

                        list.add(new IndiaModel(state_name,no_deaths,no_recovered,no_cases));


                    }


                    indianModelAdapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(IndianCasesActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

      */

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.w("response", response.toString());

                try {
                  //  JSONObject jsonObject=new JSONObject(response.toString());
                    JSONObject jsonObject=response.getJSONObject("data");

                    JSONArray jsonArray=jsonObject.getJSONArray("regional");
                    Log.w("jsonArray", jsonArray.toString());
                    //JSONArray jsonArray=jsonObject.getJSONArray("regional");

                    for (int i=0;i<jsonArray.length();i++){

                        JSONObject object=jsonArray.getJSONObject(i);

                        String state_name=object.getString("loc");
                        String no_recovered=object.getString("discharged");
                        String no_deaths=object.getString("deaths");
                        String no_cases=object.getString("totalConfirmed");

//                        IndiaModel indiaModel= new IndiaModel(state_name,no_deaths,no_recovered,no_cases);
//                        list.add(indiaModel);

                        list.add(new IndiaModel(state_name,no_deaths,no_recovered,no_cases));


                    }
                    Log.w("for loop", String.valueOf(list.size()));


                    indianModelAdapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.w("error", error.toString());

                Toast.makeText(IndianCasesActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
       requestQueue.add(jsonObjectRequest);


    }

    private void FetchTotalIndianCases() {

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        String url="https://api.rootnet.in/covid19-in/stats/latest";

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);

                    String total_cases=jsonObject.getJSONObject("data").getJSONObject("summary").getString("total");
                    String total_recovered=jsonObject.getJSONObject("data").getJSONObject("summary").getString("discharged");
                    String total_deaths=jsonObject.getJSONObject("data").getJSONObject("summary").getString("deaths");

                    cases.setText(total_cases);
                    recovered.setText(total_recovered);
                    deaths.setText(total_deaths);


                    mPieChart.addPieSlice(new PieModel("cases", Integer.parseInt(total_cases), Color.parseColor("#FFA726")));
                    mPieChart.addPieSlice(new PieModel("recovered", Integer.parseInt(total_recovered), Color.parseColor("#66BB6A")));
                    mPieChart.addPieSlice(new PieModel("deaths", Integer.parseInt(total_deaths), Color.parseColor("#EF5350")));


                    mPieChart.startAnimation();



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(IndianCasesActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }
}