package net.Timbukitu.Slenderman;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Slenderman extends JavaPlugin{
	
	static String chatTag = ChatColor.BLACK + "[" + ChatColor.WHITE + "RAVE" + ChatColor.BLACK + "] "; 
    private static Slenderman slenderman;
 
    public Slenderman(){
    }
 
    //we want to get an instance of the manager to work with it statically
    public static Slenderman getInstance(){
        if(slenderman == null)
            slenderman = new Slenderman();
        return slenderman; // NOT THREAD SAFE!
    }
	
	@Override
	public void onEnable(){
		new ArenaManager();
        ArenaManager.getManager();
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        getServer().getPluginManager().registerEvents(new RegulatoryListeners(), this);
        Bukkit.broadcastMessage(ChatColor.GOLD + "SLENDERMAN SERVER STARTED");
	}
	
	@Override
	public void onDisable(){}
	
	public static String getChatTag(){
		return chatTag;
	}
	
}
