package com.example.test.weather;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.util.List;

public class Location extends AppCompatActivity {
    public LocationClient mLocationClient = null;
    private MyLocationListener myLocationListener;

    Button locateBtn;

    private String mLocCityCode;
    private List<City> mCityList;
    private MyApplication mApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        locateBtn = findViewById(R.id.bdmap_cityname);

        mLocationClient = new LocationClient(getApplicationContext());
        myLocationListener = new MyLocationListener(locateBtn);
        mLocationClient.registerLocationListener(myLocationListener);
        initLocation();
        mLocationClient.start();


        final Intent intent = new Intent(this,MainActivity.class).setFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        locateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mApplication = (MyApplication)getApplication();
                mCityList = mApplication.getCityList();
                for(City city:mCityList)
                {
                    String locateCityName = locateBtn.getText().toString();
                    if(city.getCity().equals(locateCityName.substring(0,locateCityName.length()-1))) {
                        mLocCityCode = city.getNumber();
                        Log.d("locate", locateCityName.substring(0,locateCityName.length()-1));
                    }
                }
                //用Shareperference 存储最近一次的citycode
                SharedPreferences sharedPreferences = getSharedPreferences("CityCodePreference",Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("citycode",mLocCityCode);
                editor.commit();

                intent.putExtra("citycode",mLocCityCode);
                startActivity(intent);
                mLocationClient.stop();
            }
        });
    }
    void initLocation()
    {
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        mLocationClient.setLocOption(option);

    }

}


