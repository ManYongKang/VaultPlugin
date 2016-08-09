package com.tamenserver.vaultplugin.file;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.scheduler.BukkitRunnable;

import com.tamenserver.vaultplugin.Main;

public class DataBase {
	private Connection conn;
	private Statement st;
	private Main plugin;
	private String url;
	private String user;
	private String password;
	private String database;
	private String table;
	public DataBase(Main main,String databasea,String tablea,String urla,String usera,String passworda){
		this.url=urla;
		this.user=usera;
		this.password=passworda;
		this.database=databasea;
		this.table=tablea;
		plugin=main;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,password);
			st=conn.createStatement();
			st.execute("create database if not exists "+database+";");
			st.execute("use "+database);
			st.execute("create table if not exists "+table+"(playername tinytext not null,money float not null);");
			BukkitRunnable br=new BukkitRunnable(){
				public void run(){
					try {
						if(conn!=null){
							conn.close();
						}
						if(st!=null){
							st.close();
						}
						conn = DriverManager.getConnection(url,user,password);
						st=conn.createStatement();
						st.execute("use "+database);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			};
			br.runTaskTimer(plugin,12000L,12000L);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			plugin.disable();
			return;
		}
	}
	public float getBalance(String playername){
		ResultSet rs=null;
		float result=-1F;
		try {
			rs=st.executeQuery("select money from "+table+" where playername=binary '"+playername+"';");
			boolean isexists=rs.next();
			if(isexists){
				result=rs.getFloat("money");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public void setBalance(String playername,float money){
		try {
			st.execute("update "+table+" set money="+money+" while playername='"+playername+"';");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void addBalance(String playername,float money){
		try {
			st.execute("insert into "+table+" vaules('"+playername+"',"+money+");");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Map<String,Float> getAllBalance(){
		Map<String,Float> map=new HashMap<String,Float>();
		ResultSet rs=null;
		try {
			rs=st.executeQuery("select * from "+table+";");
			while(rs.next()){
				map.put(rs.getString("playername"),rs.getFloat("money"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return map;
	}
}
