package com.example.admin.randomuserhw;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.admin.randomuserhw.model.RandomUser;
import com.example.admin.randomuserhw.model.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class RandomMaleUsersActivity extends AppCompatActivity {
    List<RandomUser> userList = new ArrayList<>();
    private RecyclerView recyclerView;
    List<Result> r = new ArrayList<>();
    public static final String TAG = "RandomUsersActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_male_users);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.rvMainMale);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        recyclerView.setLayoutManager(layoutManager); //need layoutManager
        recyclerView.setItemAnimator(itemAnimator); //don't need this but it allows animation for each item

        final Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                RandomUser ru  = (RandomUser) message.getData().get("list");
                r = ru.getResults();
                RecyclerAdapter ra = new RecyclerAdapter(r);
                recyclerView.setAdapter(ra);
                return true;
            }
        }){

        };
        RecyclerAdapter ra = new RecyclerAdapter(r); //get this from the handler
        new Thread(new Runnable() {
            @Override
            public void run() {
                RetrofitHelper.getRandomMaleUsers()
                        .enqueue(new retrofit2.Callback<RandomUser>() {
                            @Override
                            public void onResponse(Call<RandomUser> call, Response<RandomUser> response) {
                                if (response != null)
                                {
                                    Log.d(TAG, "onResponse: response isn't null, going to set userList to response.body()");
                                    //userList = response.body().getResults();
                                    RandomUser r = response.body();
                                    Bundle b = new Bundle();
                                    b.putSerializable("list", r);
                                    Message m = new Message();
                                    m.setData(b);
                                    handler.sendMessage(m);

                                    //RecyclerAdapter ra = new RecyclerAdapter(r);
                                    //userList = response.body();
                                    //RandomUser ru = userList.get(0);
                                    //Log.d(TAG, "onResponse: first name is: " + ru.getResults().get(0).getName().getFirst());
                                }

                                // RecyclerAdapter ra = new RecyclerAdapter(userList);
                                //recyclerView.setAdapter(ra); //need adapter
                            }

                            @Override
                            public void onFailure(Call<RandomUser> call, Throwable t) {
                                Log.d(TAG, "onFailure: we failed :(");
                                t.printStackTrace();
                            }

                        });
            }
        }).start();

    }

}
