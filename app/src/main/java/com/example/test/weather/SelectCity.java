package com.example.test.weather;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
;import android.widget.EditText;
import android.widget.ImageView;
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
    ArrayAdapter<String> adapter;
    private String updateCityCode="-1";
    private EditText searchEt;
    private ImageView searchBtn;
    private String selectNo;

    boolean searched = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_city);
        /*backBtn=findViewById(R.id.title_selectCity_back);
        backBtn.setOnClickListener(this);*/
        searchEt = findViewById(R.id.selectcity_search);
        searchBtn = findViewById(R.id.selectcity_search_button);
        searchBtn.setOnClickListener(this);
        mApplication = (MyApplication)getApplication();
        mCityList = mApplication.getCityList();
        mArrayList = new ArrayList<String>();//不new会指向空
        for(int i=0;i<mCityList.size();i++)
        {
            String No_ = Integer.toString(i+1);
            String number= mCityList.get(i).getNumber();
            String provinceName = mCityList.get(i).getProvince();
            String cityName = mCityList.get(i).getCity();
            mArrayList.add("NO."+No_+":"+number+"-"+provinceName+"-"+cityName);
        }
        cityListLv =findViewById(R.id.selectcity_lv);
        adapter = new ArrayAdapter<String>(SelectCity.this,android.R.layout.simple_list_item_1,mArrayList);
        adapter.notifyDataSetChanged();
        cityListLv.setAdapter(adapter);

        final Intent intent = new Intent(this,MainActivity.class).setFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        //添加ListView项的点击事件的动作
        AdapterView.OnItemClickListener itemClickListener=new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(searched)
                {
                    updateCityCode = mCityList.get(Integer.parseInt(selectNo)).getNumber();
                }else {
                    updateCityCode = mCityList.get(position).getNumber();
                }
                Log.d("update city code",updateCityCode);

                //用Shareperference 存储最近一次的citycode
                SharedPreferences sharedPreferences = getSharedPreferences("CityCodePreference",Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("citycode",updateCityCode);
                editor.commit();

                intent.putExtra("citycode",updateCityCode);
                startActivity(intent);
            }
        };
        //为组件绑定监听
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
            case R.id.selectcity_search_button:
                String cityKey = searchEt.getText().toString();
                Log.d("Search",cityKey);
                //ArrayList<String> mSearchList = new ArrayList<String>();
                for(int i=0;i<mCityList.size();i++)
                {
                    String No_ = Integer.toString(i+1);
                    String number= mCityList.get(i).getNumber();
                    String provinceName = mCityList.get(i).getProvince();
                    String cityName = mCityList.get(i).getCity();
                    if(number.equals(cityKey)||cityName.equals(cityKey)) {
                        searched = true;
                        selectNo = Integer.toString(i);
                        mArrayList.clear();
                        mArrayList.add("NO." + No_ + ":" + number + "-" + provinceName + "-" + cityName);
                        Log.d("changed adapter data","NO." + No_ + ":" + number + "-" + provinceName + "-" + cityName);
                    }

                    adapter = new ArrayAdapter<String>(SelectCity.this,android.R.layout.simple_list_item_1,mArrayList);
                    cityListLv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            /*finish();*/
               /* Intent intent=new Intent(this,MainActivity.class);
                intent.putExtra("citycode",updateCityCode);
                startActivity(intent);*/

            default:
                break;
        }
    }
}


