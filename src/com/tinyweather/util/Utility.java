package com.tinyweather.util;

import com.tinyweather.model.City;
import com.tinyweather.model.County;
import com.tinyweather.model.Province;
import com.tinyweather.model.TinyWeatherDB;

import android.text.TextUtils;

public class Utility {
	/**
	 * ��������       01|����,02|�Ϻ�,03|���,04|����,05|������
	 * 			 0801|���ͺ���,0802|��ͷ,0803|�ں�,0804|�����첼,0805|ͨ��,0806|���
	 * 			 080601|���,080602|��彼��վ,080603|��³�ƶ�����,080604|�ƶ���
	 */
	
	/**
	 * �����ʹ�����������ص�ʡ������
	 */
	public synchronized static boolean handleProvincesResponse(TinyWeatherDB tinyWeatherDB, 
			String response){
		if(!TextUtils.isEmpty(response)){
			String[] allProvinces = response.split(",");
			if(allProvinces != null && allProvinces.length > 0){
				for(String p : allProvinces){
					//\\���ڴ�������???
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
	 * �����ʹ�����������ص��м�����
	 */
	public synchronized static boolean handleCitiesResponse(TinyWeatherDB tinyWeatherDB, 
			String response, int provinceId){
		if(!TextUtils.isEmpty(response)){
			String[] allCities = response.split(",");
			if(allCities != null && allCities.length > 0){
				for(String p : allCities){
					//\\���ڴ�������???
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
	 * �����ʹ�����������ص��ؼ�����
	 */
	public synchronized static boolean handleCountiesResponse(TinyWeatherDB tinyWeatherDB, 
			String response, int cityId){
		if(!TextUtils.isEmpty(response)){
			String[] allCounties = response.split(",");
			if(allCounties != null && allCounties.length > 0){
				for(String p : allCounties){
					//\\���ڴ�������???
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
