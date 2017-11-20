package com.example.admin.randomuserhw;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.admin.randomuserhw.model.RandomUser;
import com.example.admin.randomuserhw.model.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 11/14/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public static List<Result> userList = new ArrayList<>();

    public List<Result> getRepoList() {
        return userList;
    }


    public static final String TAG = "RecyclerAdapter";

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, null);
        return new ViewHolder(view);
    }

    public RecyclerAdapter(List<Result> repoList) {
        this.userList = repoList;
    }


    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        Result gr = userList.get(position);

        if (gr != null) {
            holder.tvName.setText(gr.getName().getFirst().toString() + " " + gr.getName().getLast().toString() );

        }

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvName;


        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent r = new Intent(view.getContext(), RandomUserActivity.class);
                    Result result = userList.get(getPosition());
                    r.putExtra("randomPerson", result);
                    view.getContext().startActivity(r);
                }
            });

        }

        @Override
        public void onClick(View view) {
            /*
            ArrayList<Animal> animals = AnimalsRecyclerAdapter.animalsList;
            Log.d("AnimalsRecyclerAdapter", "onClick: you clicked position " + getPosition());
            Log.d(TAG, "onClick: this results in value from list: " + animals.get(getPosition()).getName());
            Intent intent = new Intent(view.getContext(), ViewAnimalActivity.class);
            intent.putExtra("animal", animals.get(getPosition()).getName());
            view.getContext().startActivity(intent); */
        }
    }
}