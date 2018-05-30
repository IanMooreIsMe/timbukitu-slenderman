package net.Timbukitu.Slenderman;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class ArenaManager{
	
    private static ArenaManager am;
    static private boolean inGame = false;
    static private int minPlayers = 2;
    static private World lobbyWorld = Bukkit.getWorlds().get(0);
 
    public ArenaManager(){
    }
 
    //we want to get an instance of the manager to work with it statically
    public static ArenaManager getManager(){
        if(am == null)
            am = new ArenaManager();
        return am; // NOT THREAD SAFE!
    }

    public static void setInGame(){
    	inGame = true;
    }
    
    public static boolean isInGame(){
    	return inGame;
    }
    
    public static int getMinimumPlayersLevel(){
    	return minPlayers;
    }
    
    public static World getLobbyWorld(){
    	return lobbyWorld;
    }
    
    public static String gameStatus(){
    	if (inGame){
    		return "in game";
    	} else {
    		return "in lobby";
    	}
    		
    }
    
    public static void addPlayer(Player player){
    	
    }
    
    public static void removePlayer(Player p){
    	Slenderman.getInstance();
    	World w = p.getWorld();
    	Location l = p.getLocation();
    	Bukkit.broadcastMessage(String.format("%s %s was taken by Slenderman!", Slenderman.getChatTag(), p.getDisplayName()));
    	w.strikeLightningEffect(l);
    }
}
