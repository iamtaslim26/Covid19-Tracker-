package com.kgec.covid19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

public class ShowCountryDataActivity extends AppCompatActivity {

    private TextView tvCases,tvRecovered,tvCritical,tvActive,tvTodayDeaths,tvTodayCases,tvTests,tvCountry,tvDeaths;
    private int positionCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_country_data);


        getSupportActionBar().setTitle("Details");
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        positionCountry=getIntent().getIntExtra("position",0);

        Initialize();

        tvCountry.setText(AffectedCountryActivity.list_affected.get(positionCountry).getCountry());
        tvCases.setText(AffectedCountryActivity.list_affected.get(positionCountry).getCases());
        tvRecovered.setText(AffectedCountryActivity.list_affected.get(positionCountry).getRecovered());
        tvTests.setText(AffectedCountryActivity.list_affected.get(positionCountry).getTests());
        tvTodayCases.setText(AffectedCountryActivity.list_affected.get(positionCountry).getTodayCases());
        tvTodayDeaths.setText(AffectedCountryActivity.list_affected.get(positionCountry).getTodayDeaths());
        tvRecovered.setText(AffectedCountryActivity.list_affected.get(positionCountry).getRecovered());
        tvActive.setText(AffectedCountryActivity.list_affected.get(positionCountry).getActive());
        tvCritical.setText(AffectedCountryActivity.list_affected.get(positionCountry).getCritical());
        tvTodayDeaths.setText(AffectedCountryActivity.list_affected.get(positionCountry).getTodayDeaths());
        tvDeaths.setText(AffectedCountryActivity.list_affected.get(positionCountry).getDeaths());




    }



    private void Initialize() {
        tvCases=findViewById(R.id.tvCases1);
        tvRecovered=findViewById(R.id.tvRecovered1);
        tvCritical=findViewById(R.id.tvCritical1);
        tvActive=findViewById(R.id.tvActive1);
        tvTodayDeaths=findViewById(R.id.tvTodayDeaths1);
        //tvTotalDeaths=findViewById(R.id.tvtotalDeaths1);
        tvTodayCases=findViewById(R.id.tvTodayCases1);
        tvTests=findViewById(R.id.tvtests1);
        tvCountry=findViewById(R.id.tvCountry1);
        tvDeaths=findViewById(R.id.tvDeaths1);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}