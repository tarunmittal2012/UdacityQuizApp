package com.example.tarunmittal.project3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;

public class FrontPage extends AppCompatActivity {

    @BindView(R.id.startPage)
    Button nextpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_page);

    }

    @OnClick(R.id.startPage)
    public void setNextpage(View view) {
        Intent move = new Intent(FrontPage.this, MainActivity.class);
        startActivity(move);
    }


}
