package com.BoMC.API.tasks;

import java.util.Collection;
import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.BoMC.API.API;

public class SetFrameContentsTask extends BukkitRunnable {
	
	World world;
	Location loc;
	Material item;
	
	public SetFrameContentsTask (World world, Location frameLocation, Material itemToSet) {
		
		this.world = world;
		loc = frameLocation;
		item = itemToSet;
		
	}
	
	@Override
	public void run() {
		
		Collection<Entity> entities = world.getNearbyEntities(loc, 1, 1, 1);
		
		if(entities.size() >= 1) {
			
			for(Entity e : entities) {
				
				if(e.getType().equals(EntityType.ITEM_FRAME)) {
					
					((ItemFrame) e).setItem(new ItemStack(item));
					
				}
				
			}
			
		} else {
			
			API.LOGGER.log(Level.WARNING, "Unable to detect item frame, the server seems broken. Send log to developer!");
			return;
			
		}
		
	}

}
