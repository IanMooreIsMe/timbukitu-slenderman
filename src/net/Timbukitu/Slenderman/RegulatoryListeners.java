package net.Timbukitu.Slenderman;

import net.Timbukitu.Slenderman.ItemStackEnchancements;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class RegulatoryListeners implements Listener {
	
	private org.bukkit.event.Event.Result EventResult;
	
	@EventHandler
	public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event){
		event.setFormat(ChatColor.BLUE + event.getPlayer().getDisplayName() + ChatColor.GRAY + " » " + ChatColor.WHITE + event.getMessage());
	}
	
	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent event){
		if(!event.getPlayer().hasPermission("build")){event.setCancelled(true);}
	}
	
	@EventHandler
	public void onHangingBreakByEntityEvent(HangingBreakByEntityEvent event){
		Player player = (Player) event.getRemover();
		if(!player.hasPermission("build")){event.setCancelled(true);}
	}
	
	@EventHandler
	public void onBlockPlaceEvent(BlockPlaceEvent event){
		if(!event.getPlayer().hasPermission("build")){event.setCancelled(true);}
	}
	
	@EventHandler
	public void onHangingPlaceEvent(HangingPlaceEvent event){
		if(!event.getPlayer().hasPermission("build")){event.setCancelled(true);}
	}
	
	@EventHandler
	public void onEntityDamageEvent(EntityDamageEvent event){
		Entity e = event.getEntity();
		DamageCause d = event.getCause();
		DamageCause l = DamageCause.LIGHTNING;
		if(e instanceof Player && !d.equals(l)) {
			event.setCancelled(true);
		} else if(e instanceof Player && d.equals(l)) {
			Player p = (Player) e;
			p.setHealth(0);
		}
	}
	
	@EventHandler
	public void onCreatureSpawnEvent(CreatureSpawnEvent event){
		if(event.getSpawnReason() == SpawnReason.NATURAL){
			event.setCancelled(true);
		}
	}
    
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (event.isCancelled())
            return;
        event.getItemDrop().remove();
        event.setCancelled(true);
    }
 
    @SuppressWarnings("static-access")
	@EventHandler
    public void onClickInventory(InventoryClickEvent event){
        event.setResult(EventResult.DENY);
    }
    
    @EventHandler
    public void onPlayerUse(PlayerInteractEvent event){
        Player p = event.getPlayer();
        new ItemStackEnchancements();
    	ItemStackEnchancements.getInstance();
        if(p.getItemInHand().getType().equals(Material.INK_SACK) && (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR))){
            p.kickPlayer("Kicked to HUB");
        }

    }
    
    @EventHandler
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent event){
    	event.setFoodLevel(20);
    }
}
