package com.example.devcolibri.taskv;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
    public UserAdapter(Context context, List<User> lessonsList) {
        super(context, 0, lessonsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User obj = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        tvName.setText("Name : " + obj.name);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
        tvDescription.setText("Description : " + obj.description);
        TextView tvFork = (TextView) convertView.findViewById(R.id.tvFork);
        if (obj.getFork().equals("true")) {
            tvFork.setText("F");
        }
        else {
            tvFork.setText("");
        }
        return convertView;
    }
}

