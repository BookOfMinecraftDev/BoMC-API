package com.BoMC.API.drops;

import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class OreDropsEvent implements Listener {
	
	//Swaps ore and gold drops
	@EventHandler
	public static void onBlockDrop(BlockBreakEvent event) {
		
		if(event.getBlock().getType().equals(Material.GOLD_ORE)) {
			
			Location loc = event.getBlock().getLocation();
			
			Collection<ItemStack> drops = event.getBlock().getDrops(event.getPlayer().getInventory().getItemInMainHand(), event.getPlayer());
			
			event.setDropItems(false);
			
			for(ItemStack i : drops) {
				
				if(i.getType().equals(Material.RAW_GOLD)) {
					
					loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.RAW_IRON, i.getAmount()));
					
				} else {
					
					loc.getWorld().dropItemNaturally(loc, i);
					
				}
				
			}
			
		} else if(event.getBlock().getType().equals(Material.IRON_ORE)) {
			
			Location loc = event.getBlock().getLocation();
			
			Collection<ItemStack> drops = event.getBlock().getDrops(event.getPlayer().getInventory().getItemInMainHand(), event.getPlayer());
			
			event.setDropItems(false);
			
			for(ItemStack i : drops) {
				
				if(i.getType().equals(Material.RAW_IRON)) {
					
					loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.RAW_GOLD, i.getAmount()));
					
				} else {
					
					loc.getWorld().dropItemNaturally(loc, i);
					
				}
				
			}
			
		}
		
	}

}
