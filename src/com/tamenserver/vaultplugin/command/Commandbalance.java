package com.tamenserver.vaultplugin.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commandbalance extends VaultPluginCommand{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(args.length==0){
			if(sender instanceof Player){
				sender.sendMessage("Your balance:"+db.getBalance(sender.getName()));
			}else{
				sender.sendMessage("Only the online player can use this command like this!Please use /<balance> [playername]");
				return true;
			}
		}else if(args.length==1){
			float result=db.getBalance(args[0]);
			if(result!=-1F){
				sender.sendMessage("The player is not exists!Please check your command.");
				return true;
			}else{
				sender.sendMessage(args[0]+"'s balance:"+result);
			}
		}
		sender.sendMessage("Args wrong!");
		return false;
	}

}
