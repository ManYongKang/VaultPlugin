package com.tamenserver.vaultplugin.file;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.scheduler.BukkitRunnable;

import com.tamenserver.vaultplugin.Main;

public class DataBase {
	private Connection conn;
	private Statement st;
	private Main plugin;
	public DataBase(Main main,final String database,String table,final String url,final String user,final String password){
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
		}
	}
	public float getBalance(String playername){
		return 0.1F;
	}
}
