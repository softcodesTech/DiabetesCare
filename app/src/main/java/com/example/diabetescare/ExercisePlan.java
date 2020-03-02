package com.example.diabetescare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExercisePlan extends AppCompatActivity {
    EditText nametxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.diet_plan);
        nametxt = findViewById(R.id.restult);
        fetchData();
    }

    private void fetchData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<SugarLevelsPicked>> call = api.getstatus();

        call.enqueue(new Callback<List<SugarLevelsPicked>>() {
            @Override
            public void onResponse(@NonNull Call<List<SugarLevelsPicked>> call, @NonNull Response<List<SugarLevelsPicked>> response) {
                List<SugarLevelsPicked> adslist = response.body();


                Integer name = adslist.get(0).getName();

                nametxt.setText(name + " mg/dL");
                if (name <= 4) {
                    Intent lowdiet = new Intent(ExercisePlan.this, LowExercisePlan.class);
                    startActivity(lowdiet);
                    finish();
                }
                if (name >= 5 && name <= 7) {
                    Intent lowdiet = new Intent(ExercisePlan.this, NormalExercisePlan.class);
                    startActivity(lowdiet);
                    finish();
                } else {
                    Intent lowdiet = new Intent(ExercisePlan.this, HighExercisePlan.class);
                    startActivity(lowdiet);
                    finish();
                }


            }

            @Override
            public void onFailure(@NonNull Call<List<SugarLevelsPicked>> call, @NonNull Throwable t) {

                Toast.makeText(ExercisePlan.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}

