package com.tamenserver.vaultplugin.file;


import java.io.*;
import java.util.*;
import org.bukkit.configuration.file.YamlConfiguration;
import com.tamenserver.vaultplugin.Main;

public class VaultPluginFile {
    private Main plugin;
    private File folder;
    private File database;
    private YamlConfiguration databaseyaml;
    private HashMap<String,String> config=new HashMap<String,String>();
    public VaultPluginFile(Main main){
        plugin=main;
        folder=plugin.getDataFolder();
        database=new File(folder,"DataBase.yml");
        if(!database.exists()){
            plugin.saveResource("DataBase.yml",false);
        }
        databaseyaml=YamlConfiguration.loadConfiguration(database);
        Map<String,Object> map=databaseyaml.getConfigurationSection("").getValues(false);
        for(String key:map.keySet()){
            String value=map.get(key).toString();
            config.put(key,value);
        }
    }
    public String getConfig(String key){
        return config.get(key);
    }

}
