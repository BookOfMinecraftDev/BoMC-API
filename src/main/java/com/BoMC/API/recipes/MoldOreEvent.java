package com.BoMC.API.recipes;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import com.BoMC.API.tasks.MoldOreTask;

import net.md_5.bungee.api.ChatColor;

public class MoldOreEvent implements Listener {
	
	@EventHandler
	public static void onClickToMold(PlayerInteractEvent event) {
		
		if(!event.getHand().equals(EquipmentSlot.HAND)) return;
		
		if(event.getClickedBlock() == null) return;
		
		if(event.getClickedBlock().getType().equals(Material.FLETCHING_TABLE) && event.getItem() != null) {
			
			if(event.getItem().getType().equals(Material.BLAZE_POWDER)) {
			
				// Play dump lava bucket sound for pouring in molten ore
				Location loc = event.getClickedBlock().getLocation();
				loc.getWorld().playSound(loc, Sound.ITEM_BUCKET_EMPTY_LAVA, 1.0F, 0);
				
				// Let player know the process has begun
				event.getPlayer().sendMessage(ChatColor.GREEN + "You pour the molten iron into the mold and it begins to cool...");
				
				//Replace molten iron with empty crucible
				event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
				event.getPlayer().getInventory().addItem(new ItemStack(Material.NETHER_WART));
				
				new MoldOreTask(event.getPlayer(), Material.IRON_INGOT, event.getClickedBlock()).runTaskLaterAsynchronously(Bukkit.getPluginManager().getPlugin("BoMC-API"), 20);
				
			} else if(event.getItem().getType().equals(Material.NETHERITE_SCRAP)) {
			
				// Play dump lava bucket sound for pouring in molten ore
				Location loc = event.getClickedBlock().getLocation();
				loc.getWorld().playSound(loc, Sound.ITEM_BUCKET_EMPTY_LAVA, 1.0F, 0);
				
				// Let player know the process has begun
				event.getPlayer().sendMessage(ChatColor.GREEN + "You pour the molten steel into the mold and it begins to cool...");
				
				//Replace molten iron with empty crucible
				event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
				event.getPlayer().getInventory().addItem(new ItemStack(Material.MAGMA_CREAM));
				
				new MoldOreTask(event.getPlayer(), Material.NETHERITE_INGOT, event.getClickedBlock()).runTaskLaterAsynchronously(Bukkit.getPluginManager().getPlugin("BoMC-API"), 20);
				
			}
			
		}
		
	}

}
