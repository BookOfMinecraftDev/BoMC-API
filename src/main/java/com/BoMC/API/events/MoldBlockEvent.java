package com.BoMC.API.events;

import java.util.Collection;
import java.util.logging.Level;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

import com.BoMC.API.API;

public class MoldBlockEvent implements Listener {
	
	@EventHandler
	public static void onPlaceMold(BlockPlaceEvent event) {
		
		if(event.getBlockPlaced().getType().equals(Material.FLETCHING_TABLE)) {
			
			ItemFrame frame = event.getBlock().getWorld().spawn(event.getBlock().getLocation().add(0, 1, 0), ItemFrame.class);
			frame.setFacingDirection(BlockFace.UP, true);
			frame.setInvulnerable(true);
			frame.setVisible(false);
			
		}
		
	}
	
	@EventHandler
	public static void onRemoveMold(BlockBreakEvent event) {
		
		if(!event.getBlock().getType().equals(Material.FLETCHING_TABLE)) return;
		
		Location aboveMold = event.getBlock().getLocation().add(0, 1, 0);
		
		Collection<Entity> entities = event.getBlock().getWorld().getNearbyEntities(aboveMold, 1, 1, 1);
		
		if(entities.size() >= 1) {
			
			for(Entity e : entities) {
				
				if(e.getType().equals(EntityType.ITEM_FRAME)) {
					
					ItemFrame frame = (ItemFrame) e;
					event.getBlock().getWorld().dropItemNaturally(aboveMold, frame.getItem());
					frame.remove();
					
				}
				
			}
			
		} else {
			
			API.LOGGER.log(Level.WARNING, "Unable to detect item frame, the server seems broken. Send log to developer!");
			return;
			
		}
		
	}

	@EventHandler
	public static void onExtractOre(PlayerInteractEntityEvent event) {
		
		if(event.getHand().equals(EquipmentSlot.OFF_HAND)) return;
		
		if(event.getRightClicked().getType().equals(EntityType.ITEM_FRAME)
				&& event.getRightClicked().getWorld().getBlockAt(event.getRightClicked().getLocation().subtract(0, 1, 0)).getType().equals(Material.FLETCHING_TABLE)
				&& event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
			
			ItemFrame frame = (ItemFrame) event.getRightClicked();
			
			if(frame.getItem().getType().equals(Material.IRON_INGOT) || frame.getItem().getType().equals(Material.NETHERITE_INGOT)) {
				
				event.getPlayer().getInventory().addItem(frame.getItem());
				event.getPlayer().playSound(event.getRightClicked().getLocation(), Sound.ENTITY_ITEM_FRAME_REMOVE_ITEM, 0.8F, 0);
				frame.setItem(null);
				
				event.setCancelled(true);
				
			}
			
		}
		
	}
	
}
