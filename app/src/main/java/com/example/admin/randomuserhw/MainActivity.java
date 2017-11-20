package com.example.admin.randomuserhw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void displayRandomUser(View view) {
        Intent intent = new Intent(view.getContext(), RandomUserActivity.class);
        view.getContext().startActivity(intent);
    }

    public void displayRandomUsers(View view) {
        Intent intent = new Intent(view.getContext(), RandomUsersActivity.class);
        view.getContext().startActivity(intent);
    }

    public void displayRandomMaleUsers(View view) {
        Intent intent = new Intent(view.getContext(), RandomMaleUsersActivity.class);
        view.getContext().startActivity(intent);
    }

    public void displayRandomFemaleUsers(View view) {
        Intent intent = new Intent(view.getContext(), RandomFemaleUsersActivity.class);
        view.getContext().startActivity(intent);
    }
}
