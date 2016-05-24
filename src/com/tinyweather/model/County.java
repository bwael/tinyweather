package com.tinyweather.model;

public class County {
	private int id;
	private String countyName;
	private String countyCode;
	
	private int cityId;
	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id 要设置的 id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return countryName
	 */
	public String getCountyName() {
		return countyName;
	}
	/**
	 * @param countryName 要设置的 countryName
	 */
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	/**
	 * @return countryCode
	 */
	public String getCountyCode() {
		return countyCode;
	}
	/**
	 * @param countryCode 要设置的 countryCode
	 */
	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}
	/**
	 * @return cityId
	 */
	public int getCityId() {
		return cityId;
	}
	/**
	 * @param cityId 要设置的 cityId
	 */
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	
}
