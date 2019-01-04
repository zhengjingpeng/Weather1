package com.example.test.weather;

import android.util.Log;
import android.widget.Button;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;

public class MyLocationListener extends BDAbstractLocationListener {
    Button locBtn;
    MyLocationListener(Button locBtn)
    {
        this.locBtn = locBtn;
    }
    String cityName;
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
          cityName = bdLocation.getCity();
        Log.d("Locate",cityName);
        locBtn.setText(cityName);
    }
}
