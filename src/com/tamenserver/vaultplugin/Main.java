package com.tamenserver.vaultplugin;

import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import com.tamenserver.vaultplugin.api.Economy;
import com.tamenserver.vaultplugin.command.Commandbalance;
import com.tamenserver.vaultplugin.command.VaultPluginCommand;
import com.tamenserver.vaultplugin.file.DataBase;
import com.tamenserver.vaultplugin.file.VaultPluginFile;
import com.tamenserver.vaultplugin.listener.VaultPluginListener;

public class Main extends JavaPlugin{
	@Override
	public void onEnable(){
		VaultPluginFile vpf=new VaultPluginFile(this);
		DataBase db=new DataBase(this,vpf.getConfig("DataBase"),vpf.getConfig("Table"),vpf.getConfig("URL"),vpf.getConfig("User"),vpf.getConfig("Password"));
		Economy e=new Economy(db);
		this.getServer().getServicesManager().register(com.tamenserver.vaultplugin.api.Economy.class,e,this,ServicePriority.Normal);
		VaultPluginCommand.setDataBase(db);
		this.getCommand("balance").setExecutor(new Commandbalance());
		this.getServer().getPluginManager().registerEvents(new VaultPluginListener(db), this);
		OfflinePlayer[] players=this.getServer().getOfflinePlayers();
		for(OfflinePlayer p:players){
			String name=p.getName();
			float result=db.getBalance(name);
			if(result==-1F){
				db.addBalance(name, 0F);
			}
		}
		getLogger().info("Vault plugin is enabled!");
	}
	@Override
	public void onDisable(){
		getLogger().info("Vault plugin is disabled!");
	}
	public void disable(){
		this.getServer().getPluginManager().disablePlugin(this);
	}
}
	