package com.tinyweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class TinyWeatherOpenHelper extends SQLiteOpenHelper{
	
	/**
	 * Province建表语句
	 * */
	public static final String CREATE_PROVINCE = "create table Province("
			+ "id integer primary key autoincrement,"
			+ "province_name text,"
			+ "province_code text)";
	/**
	 * City建表语句
	 */
	public static final String CREATE_CITY = "create table City("
			+ "id integer primary key autoincrement,"
			+ "city_name text,"
			+ "city_code text"
			+ "province_id integer)";
	/**
	 * County建表语句
	 */
	public static final String CREATE_COUNTY = "create table County("
			+ "id integer primary key autoincrement,"
			+ "county_name text,"
			+ "county_code text"
			+ "city_id integer)";
	
	public TinyWeatherOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO 自动生成的构造函数存根
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		//创建表
		db.execSQL(CREATE_PROVINCE);
		db.execSQL(CREATE_CITY);
		db.execSQL(CREATE_COUNTY);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		
	}
	

	
}
