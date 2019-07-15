package com.example.retrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.retrofit.api.MyRetrofit;
import com.example.retrofit.model.Followers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.core.content.res.TypedArrayUtils.getDrawable;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Followers> list = new ArrayList<Followers>();
    DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getFollowers();
    }

    public void getFollowers() {
        MyRetrofit.api.getFollowers("cmpundhir").enqueue(new Callback<List<Followers>>() {
            @Override
            public void onResponse(Call<List<Followers>> call, Response<List<Followers>> response) {
                List<Followers> list = response.body();
                // tv.setText(list+" ");
                // adapter.notifyDataSetChanged();
                DataAdapter adapter = new DataAdapter(list);
                recyclerView.setAdapter(adapter);
                Toast.makeText(MainActivity.this, "Running", Toast.LENGTH_SHORT).show();
                Log.d("main_activity", list.size() + "");


            }

            @Override
            public void onFailure(Call<List<Followers>> call, Throwable t) {
                //tv.setText("error");
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

        List<Followers> list;

        public DataAdapter(List<Followers> list) {
            this.list = list;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.listview_design, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Followers data = list.get(position);
            holder.title.setText(data.getLogin());
            holder.subTitle.setText(data.getId()+"");
            Glide.with(MainActivity.this).load(data.getAvatarUrl()).into(holder.img);
            //holder.img.setImageDrawable(getDrawable(Integer.parseInt(data.getAvatarUrl())));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title, subTitle;
            ImageView img;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.title);
                subTitle = itemView.findViewById(R.id.subtitle);
                img = itemView.findViewById(R.id.imageView);
            }
        }
    }

}




