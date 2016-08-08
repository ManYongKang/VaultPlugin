package com.tamenserver.vaultplugin.api;

import java.util.Map;

import com.tamenserver.vaultplugin.file.DataBase;

public class Economy {
	private DataBase db;
	public Economy(DataBase db){
		this.db=db;
		
	}
	public void subBalance(String playername,float change){
		db.setBalance(playername, db.getBalance(playername)-change);
	}
	public void addBalance(String playername,float change){
		db.setBalance(playername, db.getBalance(playername)+change);
	}
	public void setBalance(String playername,float money){
		db.setBalance(playername, money);
	}
	public float getBalance(String playername){
		return db.getBalance(playername);
	}
	public Map<String,Float> getAllBalance(){ 
		return db.getAllBalance();
	}
}
