package com.tinyweather.util;

import com.tinyweather.model.City;
import com.tinyweather.model.County;
import com.tinyweather.model.Province;
import com.tinyweather.model.TinyWeatherDB;

import android.text.TextUtils;

public class Utility {
	/**
	 * 样例数据       01|北京,02|上海,03|天津,04|重庆,05|黑龙江
	 * 			 0801|呼和浩特,0802|包头,0803|乌海,0804|乌兰察布,0805|通辽,0806|赤峰
	 * 			 080601|赤峰,080602|赤峰郊区站,080603|阿鲁科尔沁旗,080604|浩尔吐
	 */
	
	/**
	 * 解析和处理服务器返回的省级数据
	 */
	public synchronized static boolean handleProvincesResponse(TinyWeatherDB tinyWeatherDB, 
			String response){
		if(!TextUtils.isEmpty(response)){
			String[] allProvinces = response.split(",");
			if(allProvinces != null && allProvinces.length > 0){
				for(String p : allProvinces){
					//\\用于代替数字???
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					//Store in Province table
					tinyWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 解析和处理服务器返回的市级数据
	 */
	public synchronized static boolean handleCitiesResponse(TinyWeatherDB tinyWeatherDB, 
			String response, int provinceId){
		if(!TextUtils.isEmpty(response)){
			String[] allCities = response.split(",");
			if(allCities != null && allCities.length > 0){
				for(String p : allCities){
					//\\用于代替数字???
					String[] array = p.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					//Store in City table
					tinyWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 解析和处理服务器返回的县级数据
	 */
	public synchronized static boolean handleCountiesResponse(TinyWeatherDB tinyWeatherDB, 
			String response, int cityId){
		if(!TextUtils.isEmpty(response)){
			String[] allCounties = response.split(",");
			if(allCounties != null && allCounties.length > 0){
				for(String p : allCounties){
					//\\用于代替数字???
					String[] array = p.split("\\|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					//Store in County table
					tinyWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}
}
