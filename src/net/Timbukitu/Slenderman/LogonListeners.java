package net.Timbukitu.Slenderman;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.PlayerInventory;

public class LogonListeners implements Listener {
	
	private org.bukkit.event.player.PlayerLoginEvent.Result LoginResult;

    @SuppressWarnings("static-access")
	@EventHandler
    public void onLogin(PlayerLoginEvent event) {
    	Player player = event.getPlayer();
        ArenaManager.getManager();
        if(ArenaManager.isInGame()){
        	StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Sorry ");
			stringBuilder.append(player.getDisplayName());
			stringBuilder.append(", but a game is currently in progress!");
			event.disallow(LoginResult.KICK_OTHER, stringBuilder.toString());
        } else {
        	event.allow();
        }
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws InterruptedException{
    	ArenaManager.getManager();
    	String tag = Slenderman.getChatTag();
    	ItemStackEnchancements.getInstance();
    	Player p = event.getPlayer();
    	PlayerInventory inventory = p.getInventory();
    	
    	event.setJoinMessage(tag + p.getDisplayName() + " has joined the lobby! ");
    	p.sendMessage(tag + ChatColor.YELLOW + "Hello " + p.getDisplayName() + ", and welcome to Slenderman");
    	p.sendMessage(tag + ChatColor.YELLOW + "Don't Look Behind You");
    	p.sendMessage(tag + ChatColor.YELLOW + "The game is currently " + ArenaManager.gameStatus());
    	Bukkit.broadcastMessage(tag + Bukkit.getOnlinePlayers().length + "/" + ArenaManager.getMinimumPlayersLevel() + " required players for game to begin.");
    	p.teleport(new Location(ArenaManager.getLobbyWorld(), 0, 75, 0));
    	p.setMaximumAir(2);
    	p.setExp(.999f);
    	inventory.clear();
        inventory.setHeldItemSlot(0);
        inventory.setItem(0, ItemStackEnchancements.getGameBook());
        inventory.setItem(8, ItemStackEnchancements.setItemName(ItemStackEnchancements.makeDye(DyeColor.SILVER), ChatColor.RED + "Quit To HUB"));
        
        ArenaManager.addPlayer(p);
    }
    
    @EventHandler
    public void onQuit(PlayerQuitEvent event) throws InterruptedException {
    	Object e = event;
    	leaveActions(e);
    }
    
    @EventHandler
    public void onKick(PlayerKickEvent event) throws InterruptedException{
    	Object e = event;
    	leaveActions(e);
    }
	
    private static void leaveActions(Object e){
    	ArenaManager.getManager();
    	if(e instanceof PlayerQuitEvent){
    		PlayerQuitEvent event = (PlayerQuitEvent) e;
    		event.setQuitMessage(null);
    		ArenaManager.removePlayer(event.getPlayer());
    	} else {
    		PlayerKickEvent event = (PlayerKickEvent) e;
    		event.setLeaveMessage(null);
    		ArenaManager.removePlayer(event.getPlayer());
    	}
    }
}
