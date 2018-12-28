package com.example.test.weather;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
;import android.widget.ImageView;
import android.widget.ListView;

import com.example.test.weather.R;

import java.util.ArrayList;
import java.util.List;


public class SelectCity extends Activity implements View.OnClickListener {
    private ImageView backBtn;
    private ListView cityListLv;
    private List<City> mCityList;
    private MyApplication mApplication;
    private ArrayList<String> mArrayList;
    private String updateCityCode="-1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);
        backBtn=findViewById(R.id.title_selectCity_back);
        backBtn.setOnClickListener(this);
        mApplication = (MyApplication)getApplication();
        mCityList = mApplication.getCityList();
        mArrayList = new ArrayList<String>();//不new会指向空
        for(int i=0;i<mCityList.size();i++)
        {
            String cityName = mCityList.get(i).getCity();

            mArrayList.add(cityName);
        }
        cityListLv =findViewById(R.id.selectcity_lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SelectCity.this,android.R.layout.simple_list_item_1,mArrayList);
        cityListLv.setAdapter(adapter);
        AdapterView.OnItemClickListener itemClickListener=new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int updateCityCode=Integer.parseInt(mCityList.get(position).getNumber());
                Log.d("update city code",Integer.toString(updateCityCode));

            }
        };
        cityListLv.setOnItemClickListener(itemClickListener);

       /* String[] listDate={"1","2","3"};
        cityListLv=findViewById(R.id.selectcity_lv);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(SelectCity.this,android.R.layout.simple_list_item_1,listDate);
        cityListLv.setAdapter(adapter);*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.title_selectCity_back:
            /*finish();*/
                Intent intent=new Intent(this,MainActivity.class);
                intent.putExtra("citycode",updateCityCode);
                startActivity(intent);
            break;
            default:
                break;
        }
    }
}


