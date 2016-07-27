package com.example.ywq9682.eyepetizer.author.authordetial.detialnext;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ywq9682.eyepetizer.R;

/**
 * Created by dllo on 16/7/26.
 */
public class DetialNextHeadAdapter extends RecyclerView.Adapter<DetialNextHeadAdapter.MyViewHolder> {

private DetialNextBean detialNextBean;
    private Context context;

    public DetialNextHeadAdapter(Context context) {
        this.context = context;
    }

    public void setDetialNextBean(DetialNextBean detialNextBean) {
        this.detialNextBean = detialNextBean;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_detialnext_headall,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        DetialNextRecyclerAdapter detialNextRecyclerAdapter = new DetialNextRecyclerAdapter(context);
        detialNextRecyclerAdapter.setDetialNextBean(detialNextBean.getItemList().get(0).getData());
        holder.recyclerView.setAdapter(detialNextRecyclerAdapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.recyclerView.setLayoutManager(linearLayoutManager);


    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class MyViewHolder extends  RecyclerView.ViewHolder{
RecyclerView recyclerView;

    public MyViewHolder(View itemView) {
        super(itemView);
        recyclerView= (RecyclerView) itemView.findViewById(R.id.item_detialnext_head_recycler);
    }
}
}