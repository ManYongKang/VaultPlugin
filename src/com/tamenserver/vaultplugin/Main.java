package com.tamenserver.vaultplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	@Override
	public void onEnable(){
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
	