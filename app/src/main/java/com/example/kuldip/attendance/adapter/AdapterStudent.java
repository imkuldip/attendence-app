package com.example.kuldip.attendance.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.kuldip.attendance.R;
import com.example.kuldip.attendance.model.Student;

import java.util.List;

/**
 * Created by Kuldip on 1/29/2018.
 */

public class AdapterStudent extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Student> list;

    public AdapterStudent(Context context, List<Student> list) {
        this.context = context;
        this.list = list;
    }

    public List<Student> getList() {
        return list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView  = LayoutInflater.from(context).inflate(R.layout.row_student, parent, false);
        return new ViewHolderStudent(convertView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ViewHolderStudent){
            ViewHolderStudent vh = (ViewHolderStudent) holder;
            Student s = list.get(position);
            vh.name.setText(s.name);
            vh.cb.setChecked(s.isPresent);
            vh.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    s.isPresent = b;
                    list.set(position, s);
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolderStudent extends RecyclerView.ViewHolder{
        TextView name;
        CheckBox cb;
        public ViewHolderStudent(View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.name);
            cb = itemView.findViewById(R.id.checkbox);
        }
    }
}
