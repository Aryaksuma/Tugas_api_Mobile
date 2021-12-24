package com.example.tugas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tugas.model.DataWebFremwork;

import java.util.ArrayList;

public class AdapterWebFremwork extends ArrayAdapter {
    ArrayList<DataWebFremwork> dataFrameworks= new ArrayList();
    LayoutInflater layoutInflater;
    Context context;

    public AdapterWebFremwork(Context context, ArrayList<DataWebFremwork> dataFrameworks) {
        super(context, R.layout.list_fremwork, dataFrameworks);
        this.dataFrameworks = dataFrameworks;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.list_fremwork, parent, false);
        DataWebFremwork dataFramework = this.dataFrameworks.get(position);

        TextView titleListview = convertView.findViewById(R.id.titleListview);
        TextView authoListview = convertView.findViewById(R.id.authorListview);
        ImageView imageView = convertView.findViewById(R.id.imgListview);

        Glide
                .with(context)
                .load(dataFramework.getImage())
                .placeholder(R.drawable.ic_refres_foreground)
                .into(imageView);

        titleListview.setText(dataFramework.getNameFramework());
        authoListview.setText(""+dataFramework.getOriginal_author());

        return convertView;
    }
}
