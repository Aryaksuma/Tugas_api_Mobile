package com.example.tugas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.tugas.model.DataWebFremwork;

public class DetailWebFremwork extends AppCompatActivity {
    DataWebFremwork FrameworkSelected;
    ImageView imgProfile;
    TextView titleProfile, authorProfile, officialWeb, descProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_fremwork);

        Intent intent = getIntent();
        FrameworkSelected = (DataWebFremwork) intent.getSerializableExtra("FRAMEWORK_SELECTED");
        inisialisasi();
    }

    void inisialisasi() {
        imgProfile = findViewById(R.id.imgProfile);
        titleProfile = findViewById(R.id.titleProfile);
        authorProfile = findViewById(R.id.authorProfile);
        descProfile = findViewById(R.id.descProfile);
        officialWeb = findViewById(R.id.officialWeb);

        titleProfile.setText(FrameworkSelected.getNameFramework());
        authorProfile.setText("~  By "+FrameworkSelected.getOriginal_author()+"  ~");
        officialWeb.setText(FrameworkSelected.getOfficial_web());
        descProfile.setText(FrameworkSelected.getDescription());
        Glide
                .with(this)
                .load(FrameworkSelected.getImage())
                .placeholder(R.drawable.ic_refres_foreground)
                .into(imgProfile);
    }

    public void actionToLink(View v) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(FrameworkSelected.getOfficial_web()));
        startActivity(intent);
    }
}
