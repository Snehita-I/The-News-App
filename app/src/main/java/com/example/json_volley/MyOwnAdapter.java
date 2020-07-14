package com.example.json_volley;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.ArrayList;

public class MyOwnAdapter extends RecyclerView.Adapter<MyOwnAdapter.MyOwnHolder> {
    String data1;
    String data2;

    Context ctx;
    ArrayList<ModelClass> items1;
    public MyOwnAdapter(Context ct, ArrayList<ModelClass> items) {
        ctx = ct;
        items1 = items;

    }

    @Override
    public MyOwnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflator = LayoutInflater.from((Context) ctx);
        View myview = myInflator.inflate(R.layout.my_row, parent, false);
        return new MyOwnHolder(myview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOwnAdapter.MyOwnHolder holder, int position) {
        final ModelClass Item = items1.get(position);
        String title = Item.getString();
        String url = Item.getUrl();
        holder.t1.setText(title);
        DownloadImageTask d1=new DownloadImageTask();
        d1.execute(url);


    }

    @Override
    public int getItemCount() {
        return items1.size();
    }

    public class MyOwnHolder extends RecyclerView.ViewHolder {
        TextView t1;
        ImageView i1;

        public MyOwnHolder(@NonNull View itemView) {
            super(itemView);
            t1 = (TextView) itemView.findViewById(R.id.text1);
            i1 = (ImageView) itemView.findViewById(R.id.myimage);

        }
    }
}