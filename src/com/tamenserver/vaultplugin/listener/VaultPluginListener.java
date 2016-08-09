package com.tamenserver.vaultplugin.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.tamenserver.vaultplugin.file.DataBase;

public class VaultPluginListener implements Listener{
	private DataBase db;
	public VaultPluginListener(DataBase db){
		this.db=db;
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent evt){
		String name=evt.getPlayer().getName();
		float result=db.getBalance(name);
		if(result==-1F){
			db.addBalance(name, 0F);
		}
	}
}
