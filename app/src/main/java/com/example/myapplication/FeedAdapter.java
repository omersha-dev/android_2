package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Map;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedAdapterHolder> {

    LayoutInflater inflater;
    ArrayList<Map<String, Object>> posts;

    public FeedAdapter(Context context, ArrayList<Map<String, Object>> postData) {
        this.inflater = LayoutInflater.from(context);
        this.posts = postData;
    }

    @NonNull
    @Override
    public FeedAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_feed_item, parent, false);
        return new FeedAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedAdapterHolder holder, int position) {
        holder.petName.setText(posts.get(position).get("pet_name").toString());
        holder.postDesc.setText(posts.get(position).get("post_description").toString());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class FeedAdapterHolder extends RecyclerView.ViewHolder {

        TextView petName, postDesc;

        public FeedAdapterHolder(View itemView) {
            super(itemView);
            petName = (TextView) itemView.findViewById(R.id.feed_pet_name);
            postDesc = (TextView) itemView.findViewById(R.id.feed_post_desc);
        }
    }

}

//public class FeedAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
//
//    private Random random;
//
//    public FeedAdapter(int seed) {
//        this.random = new Random(seed);
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return R.layout.single_feed_item;
////        return super.getItemViewType(position);
//    }
//
//    @NonNull
//    @Override
//    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
//        return new RecyclerViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
//        holder.getView().setText(String.valueOf(random.nextInt()));
//    }
//
//    @Override
//    public int getItemCount() {
//        return 100;
//    }
//}
