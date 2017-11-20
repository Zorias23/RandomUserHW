package com.example.admin.randomuserhw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.admin.randomuserhw.model.RandomUser;
import com.example.admin.randomuserhw.model.Result;

public class RandomUserActivity extends AppCompatActivity {
    TextView tvFirstName, tvLastName, tvState, tvZipcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_user);
        tvFirstName = findViewById(R.id.tvFirstName);
        tvLastName = findViewById(R.id.tvLastName);
        tvState = findViewById(R.id.tvState);
        tvZipcode = findViewById(R.id.tvZipcode);
        Result r = null;
        if (getIntent().getExtras() != null)
        {
            r = (Result) getIntent().getExtras().get("randomPerson");
        }

        if (r == null)
        {
            RetrofitHelper.getRandomUser()
                    .enqueue(new retrofit2.Callback<RandomUser>() {
                        @Override
                        public void onResponse(retrofit2.Call<RandomUser> call, retrofit2.Response<RandomUser> response) {
                            if (response.body() != null)
                            {
                                tvFirstName.setText("First Name: " + response.body().getResults().get(0).getName().getFirst().toString());
                                tvLastName.setText("Last Name: " + response.body().getResults().get(0).getName().getLast().toString());
                                tvState.setText("State: " + response.body().getResults().get(0).getLocation().getState().toString());
                                tvZipcode.setText("Zipcode: " + response.body().getResults().get(0).getLocation().getPostcode().toString());
                                Log.d("RandomUserActivity", "onResponse: success and first name is " + response.body().getResults().get(0).getName().getFirst());
                            }

                        }

                        @Override
                        public void onFailure(retrofit2.Call<RandomUser> call, Throwable t) {
                            Log.d("RandomUserActivity", "onFailure: onFailure is called :(");
                        }
                    });
        }
        else
        {
            tvFirstName.setText("First Name: " + r.getName().getFirst().toString());
            tvLastName.setText("Last Name: " + r.getName().getLast().toString());
            tvState.setText("State: " + r.getLocation().getState().toString());
            tvZipcode.setText("Zipcode: " + r.getLocation().getPostcode().toString());
        }


    }
}
