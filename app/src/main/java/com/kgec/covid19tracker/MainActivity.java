package com.kgec.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextClassification;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView tvCases,tvRecovered,tvCritical,tvActive,tvTodayDeaths,tvTotalDeaths,tvTodayCases,tvAffectedCountry;
    private PieChart pieChart;
    private ScrollView scrollView;
    private SimpleArcLoader simpleArcLoader;
    private Button btn_country,btn_india;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Initialize();

       simpleArcLoader.start();
        FetchData();

        btn_india.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),IndianCasesActivity.class));
            }
        });

        btn_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),AffectedCountryActivity.class));
            }
        });
    }

    private void FetchData() {

        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        String url="https://corona.lmao.ninja/v2/all";



        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject jsonObject=new JSONObject(response.toString());
                    tvCases.setText(jsonObject.getString("cases"));
                    tvRecovered.setText(jsonObject.getString("recovered"));
                    tvCritical.setText(jsonObject.getString("critical"));
                    tvActive.setText(jsonObject.getString("active"));
                    tvTodayCases.setText(jsonObject.getString("todayCases"));
                    tvTotalDeaths.setText(jsonObject.getString("deaths"));
                    tvTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                    tvAffectedCountry.setText(jsonObject.getString("affectedCountries"));


                    // Time to show the Pie chart

                    pieChart.addPieSlice(new PieModel("cases", Integer.parseInt(jsonObject.getString("cases")), Color.parseColor("#FFA726")));
                    pieChart.addPieSlice(new PieModel("recovered", Integer.parseInt(jsonObject.getString("recovered")), Color.parseColor("#66BB6A")));
                    pieChart.addPieSlice(new PieModel("deaths", Integer.parseInt(jsonObject.getString("deaths")), Color.parseColor("#EF5350")));
                    pieChart.addPieSlice(new PieModel("active", Integer.parseInt(jsonObject.getString("active")), Color.parseColor("#29B6F6")));

                    pieChart.startAnimation();


                   simpleArcLoader.stop();
                   simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);


                } catch (JSONException e) {
                    e.printStackTrace();

                    simpleArcLoader.start();
                    simpleArcLoader.setVisibility(View.VISIBLE);
                    scrollView.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        MySingleton.getInstance(this).addToRequestQueue(request);
    }

    private void Initialize() {

        tvCases=findViewById(R.id.tvCases);
        tvRecovered=findViewById(R.id.tvRecovered);
        tvCritical=findViewById(R.id.tvCritical);
        tvActive=findViewById(R.id.tvActive);
        tvTodayDeaths=findViewById(R.id.tvTodayDeaths);
        tvTotalDeaths=findViewById(R.id.tvTotalDeaths);
        tvTodayCases=findViewById(R.id.tvTodayCases);
        tvAffectedCountry=findViewById(R.id.tvAffectedCountries);
        pieChart=findViewById(R.id.piechart);
        scrollView=findViewById(R.id.scrollStats);
        simpleArcLoader=findViewById(R.id.loader);
        btn_country=findViewById(R.id.btnTrack);
        btn_india=findViewById(R.id.btnIndia);
    }
}