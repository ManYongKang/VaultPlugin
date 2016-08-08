package com.tamenserver.vaultplugin.command;

import org.bukkit.command.CommandExecutor;

import com.tamenserver.vaultplugin.file.DataBase;

public abstract class VaultPluginCommand implements CommandExecutor{
	protected static DataBase db;
	public static void setDataBase(DataBase db){
		VaultPluginCommand.db=db;
	}
	public static DataBase getDataBase(){
		return db;
	}
}
