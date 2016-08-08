package com.tamenserver.vaultplugin.api;

import java.util.Map;

import com.tamenserver.vaultplugin.file.DataBase;

public class Economy {
	private DataBase db;
	public Economy(DataBase db){
		this.db=db;
		
	}
	public float getBalance(String playername){
		return db.getBalance(playername);
	}
	public Map<String,Float> getAllBalance(){ 
		return db.getAllBalance();
	}
}
