package com.BoMC.API.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class MoldOreTask extends BukkitRunnable { 
	
	Player player;
	Material ore;
	Block mold;
	
	public MoldOreTask(Player p, Material oreToReturn, Block mold) {
		
		player = p;
		ore = oreToReturn;
		this.mold = mold;
		
	}
	
	@Override
	public void run() {
		
		int repeats = 2;
		
		do {
			
			player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8F, 0);
			
			try {
				
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
				
			}
			
		} while ( repeats-- > 0);
		
		player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8F, 0);
		player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE_FAR, 0.8F, 2);
		
		player.sendMessage(ChatColor.GREEN + "The ore is cooled!");
		
		Location aboveMold = mold.getLocation().add(0, 1, 0);
		
		new SetFrameContentsTask(mold.getWorld(), aboveMold, Material.IRON_INGOT).runTask(Bukkit.getPluginManager().getPlugin("BoMC-API"));
		
	}

}
