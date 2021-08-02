package com.BoMC.API;

import static com.BoMC.API.clans.Clans.initClans;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.BoMC.API.clans.data.Clan;
import com.BoMC.API.drops.OreDropsEvent;
import com.BoMC.API.events.MoldBlockEvent;
import com.BoMC.API.recipes.GrindstoneCraftEvent;
import com.BoMC.API.recipes.InitRecipes;
import com.BoMC.API.recipes.MoldOreEvent;

public class API extends JavaPlugin {
	
	private static List<Clan> CLANS;
	
	// Files
	public static File CONFIG_FILE;
	public static File CLANS_FILE;
	
	// FileConfigurations
	public static FileConfiguration CONFIG;
	public static FileConfiguration CLANS_YML;
	
	// Logger
	public static Logger LOGGER;
	
	// Grindstone Recipe Sets
	public static Map<Material, Material> GRINDSTONE_RECIPES = new HashMap<Material, Material>();
	
	@Override
	public void onEnable() {
		
		LOGGER = Bukkit.getLogger();
		
		saveDefaultConfig();
		
		// Get Files
		CONFIG_FILE = new File(getDataFolder(), "config.yml");
		CLANS_FILE = new File(getDataFolder(), "clans.yml");
		
		// Create missing files
		if(!CLANS_FILE.exists()) {
			
			try {
				
				CLANS_FILE.mkdirs();
				CLANS_FILE.createNewFile();
				
			} catch (IOException e) {

				e.printStackTrace();
				
			}
			
		}
		
		// Initialize Recipes
		InitRecipes.initAll(this);
		
		// Register grindstone crafting system
		getServer().getPluginManager().registerEvents(new GrindstoneCraftEvent(), this);
		
		// Register ore drop switcher
		getServer().getPluginManager().registerEvents(new OreDropsEvent(), this);
		
		// Register molten ore molding
		getServer().getPluginManager().registerEvents(new MoldOreEvent(), this);
		
		// Register placing mold block
		getServer().getPluginManager().registerEvents(new MoldBlockEvent(), this);
		
		// Get FileConfigurations
		CONFIG = getConfig();
		CLANS_YML = YamlConfiguration.loadConfiguration(CLANS_FILE);
		
		// Initialize clans
		CLANS = initClans();
		
	}
	
	@Override
	public void onDisable() {
		
		
		
	}
	
	/*
	 *  @return The clan with given ID, or null if one is not found.
	 */
	public static Clan getClan(String id) {
		
		for(Clan c : CLANS) {
			
			if(c.getClanID().equalsIgnoreCase(id)) {
				
				return c;
				
			}
			
		}
		
		return null;
		
	}
	
	public static void addGrindstoneRecipe(Material m1, Material m2) {
		
		GRINDSTONE_RECIPES.put(m1, m2);
		
	}

}
