package com.example.task2_perkanttech.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task2_perkanttech.R;
import com.example.task2_perkanttech.models.StudentModel;

import java.util.List;

public class ScoreBoard_Adapter extends RecyclerView.Adapter<ScoreBoard_Adapter.MyViewHolder>{

    private Context context;
    private List<StudentModel> list;

    public ScoreBoard_Adapter(Context context, List<StudentModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.student_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.code.setText(list.get(position).getCode());
        holder.score.setText(list.get(position).getScore());
        holder.rank.setText(String.valueOf(position+1));

        //---( Logic for Colors for Score )--//
        int score = Integer.parseInt(list.get(position).getScore());
        if (score>=70){
            holder.score.setTextColor(ContextCompat.getColor(context, R.color.green_points));
        }
        else if (score >=40){
            holder.score.setTextColor(ContextCompat.getColor(context, R.color.yellow_points));
        }
        else {
            holder.score.setTextColor(ContextCompat.getColor(context, R.color.red_points));
        }

        //---( Setting Position )---//
        if (position == 0) {
            holder.rank.setVisibility(View.GONE);
            holder.firstPosition.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if (list != null && !list.isEmpty()) {
            return list.size();
        }
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView rank;
        TextView name;
        TextView score;
        TextView code;
        ImageView firstPosition;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            rank = itemView.findViewById(R.id.student_rank);
            name = itemView.findViewById(R.id.student_name);
            score = itemView.findViewById(R.id.student_score);
            code = itemView.findViewById(R.id.student_code);
            firstPosition = itemView.findViewById(R.id.first_position);
        }
    }
}
