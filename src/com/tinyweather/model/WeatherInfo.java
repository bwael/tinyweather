package com.tinyweather.model;
/**
 * �ݲ�ʹ�ã��Ա��������书��
 * @author bwael
 *
 */
public class WeatherInfo {
	private String cityName;
	private String weatherCode;
	private String temp1;
	private String temp2;
	private String weatherDesp;
	private String publishTime;
	/**
	 * @return cityName
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * @param cityName Ҫ���õ� cityName
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * @return weatherCode
	 */
	public String getWeatherCode() {
		return weatherCode;
	}
	/**
	 * @param weatherCode Ҫ���õ� weatherCode
	 */
	public void setWeatherCode(String weatherCode) {
		this.weatherCode = weatherCode;
	}
	/**
	 * @return temp1
	 */
	public String getTemp1() {
		return temp1;
	}
	/**
	 * @param temp1 Ҫ���õ� temp1
	 */
	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}
	/**
	 * @return temp2
	 */
	public String getTemp2() {
		return temp2;
	}
	/**
	 * @param temp2 Ҫ���õ� temp2
	 */
	public void setTemp2(String temp2) {
		this.temp2 = temp2;
	}
	/**
	 * @return weatherDesp
	 */
	public String getWeatherDesp() {
		return weatherDesp;
	}
	/**
	 * @param weatherDesp Ҫ���õ� weatherDesp
	 */
	public void setWeatherDesp(String weatherDesp) {
		this.weatherDesp = weatherDesp;
	}
	/**
	 * @return publishTime
	 */
	public String getPublishTime() {
		return publishTime;
	}
	/**
	 * @param publishTime Ҫ���õ� publishTime
	 */
	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}
	
	
}
