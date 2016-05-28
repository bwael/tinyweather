package com.tinyweather.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinyweather.app.R;
import com.tinyweather.model.City;
import com.tinyweather.model.County;
import com.tinyweather.model.Province;
import com.tinyweather.model.TinyWeatherDB;
import com.tinyweather.util.HttpUtil;
import com.tinyweather.util.HttpCallbackListener;
import com.tinyweather.util.Utility;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseAreaActivity extends Activity{
	
	public static final int LEVEL_PROVINCE = 0;
	public static final int LEVEL_CITY = 1;
	public static final int LEVEL_COUNTY = 2;
	
	private ProgressDialog progressDialog;
	private TextView titleText;
	private ListView listView;
	private ArrayAdapter<String> adapter;
	private TinyWeatherDB tinyWeatherDB;
	private List<String> dataList = new ArrayList<String>();
	
	//省列表,市，县
	private List<Province> provinceList;
	private List<City> cityList;
	private List<County> countyList;
	
	//选中的省，市，级别
	private Province selectedProvince;
	private City selectedCity;
	private int currentLevel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.choose_area);
		listView = (ListView)findViewById(R.id.list_view);
		titleText = (TextView)findViewById(R.id.title_text);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList);
		listView.setAdapter(adapter);
		tinyWeatherDB = TinyWeatherDB.getInstance(this);
		
		listView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int index, long arg3){
				if(currentLevel == LEVEL_PROVINCE){
					selectedProvince = provinceList.get(index);
					queryCities();
				}
				else if(currentLevel == LEVEL_CITY){
					selectedCity = cityList.get(index);
					queryCounties();
				}
			}
			
		});
		queryProvinces();
	}
	/**
	 * 查询省，优先从数据库查询，如果没有再到数据库查询
	 */
	private void queryProvinces() {
		provinceList = tinyWeatherDB.loadProvinces();
		if(provinceList.size() > 0){
			dataList.clear();
			for(Province province : provinceList){
				dataList.add(province.getProvinceName());
			}
			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText("中国");
			currentLevel = LEVEL_PROVINCE;
		}
		else{
			queryFromSever(null, "province");
		}
		
		
		
	}

	/**
	 * 查询省内所有市
	 */
	private void queryCities() {
		cityList = tinyWeatherDB.loadCities(selectedProvince.getId());
		if(cityList.size() > 0){
			dataList.clear();
			for(City city : cityList){
				dataList.add(city.getCityName());
			}
			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText(selectedProvince.getProvinceName());
			currentLevel = LEVEL_CITY;
		}
		else{
			queryFromSever(selectedProvince.getProvinceCode(), "city");
		}
		
	}
	
	private void queryCounties() {
		countyList = tinyWeatherDB.loadCounties(selectedCity.getId());
		if(countyList.size() > 0){
			dataList.clear();
			for(County county : countyList){
				dataList.add(county.getCountyName());
			}
			adapter.notifyDataSetChanged();
			listView.setSelection(0);
			titleText.setText(selectedCity.getCityName());
			currentLevel = LEVEL_COUNTY;
		}
		else{
			queryFromSever(selectedCity.getCityCode(), "county");
		}
		
	}
	/**
	 * 从服务器查询数据
	 * @param code 代号
	 * @param type 类型
	 */
	private void queryFromSever(final String code, final String type) {
		String address;
		if(!TextUtils.isEmpty(code)){
			address = "http://www.weather.com.cn/data/list3/city" + code + ".xml";
		}
		else{
			address = "http://www.weather.com.cn/data/list3/city.xml";
		}
		
		showProgressDialog();
		HttpUtil.sendHttpRequest(address, new HttpCallbackListener(){
			@Override
			public void onFinish(String response){
				boolean result = false;
				if("province".equals(type)){
					result = Utility.handleProvincesResponse(tinyWeatherDB, response);
				}else if("city".equals(type)){
					result = Utility.handleCitiesResponse(tinyWeatherDB, response, selectedProvince.getId());
				}else if("county".equals(type)){
					result = Utility.handleCountiesResponse(tinyWeatherDB, response, selectedCity.getId());
				}
				
				if(result){
					//通过该方法回到主线程逻辑
					runOnUiThread(new Runnable(){
						@Override
						public void run(){
							closeProgressDialog();
							if("province".equals(type)){
								queryProvinces();
							}else if("city".equals(type)){
								queryCities();
							}else if("county".equals(type)){
								queryCounties();
							}
						}

					});
				}
			}
			
			@Override
			public void onError(Exception e){
				//通过runOnUiThread()方法回到主线程处理逻辑
				runOnUiThread(new Runnable(){
					@Override
					public void run(){
						closeProgressDialog();
						Toast.makeText(ChooseAreaActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
					}
				});
		    }
		});
		
	}
	
	/**
	 * 显示进度对话框
	 */
	private void showProgressDialog() {
		if(progressDialog == null){
			progressDialog = new ProgressDialog(this);
			progressDialog.setMessage("正在加载...");
			progressDialog.setCanceledOnTouchOutside(false);
		}
		progressDialog.show();
	}
	
	/**
	 * 关闭进度对话框
	 */
	private void closeProgressDialog() {
		if(progressDialog != null ){
			progressDialog.dismiss();
		}
	}
	
	/**
	 * 捕获Back按键，根据当前所在页面确认此时该返回省列表，市列表，还是直接退出
	 */
	@Override
	public void onBackPressed(){
		if(currentLevel == LEVEL_COUNTY){
			queryCities();
		}else if(currentLevel == LEVEL_CITY){
			queryProvinces();
		}else{
			finish();
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
