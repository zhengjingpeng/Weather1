package com.example.test.weather;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.test.weather.R;



public class SelectCity extends Activity implements View.OnClickListener {
    private ImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);

        backBtn=(ImageView)findViewById(R.id.title_selectCity_back);
        backBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.title_selectCity_back:
            finish();
            break;
            default:
                break;
        }
    }
}


