package com.BoMC.API.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.Plugin;

import com.BoMC.API.API;

public class InitRecipes {
	
	public static void initAll(Plugin plugin) {
		
		plugin.getServer().resetRecipes();
		
		initShapelessRecipes(plugin);
		initShapedRecipes(plugin);
		initFurnaceRecipes(plugin);
		initGrindstoneRecipes();
		removeRecipes(plugin);
		
	}
	
	public static void initShapelessRecipes(Plugin plugin) {
		
		ShapelessRecipe ironInCrucible = new ShapelessRecipe(new NamespacedKey(plugin, "ironInCrucible"), new ItemStack(Material.BLAZE_ROD));
		ironInCrucible.addIngredient(Material.RAW_IRON);
		ironInCrucible.addIngredient(Material.NETHER_WART);
		
		ShapelessRecipe steelAlloy = new ShapelessRecipe(new NamespacedKey(plugin, "steelAlloy"), new ItemStack(Material.GLOWSTONE_DUST));
		steelAlloy.addIngredient(8, Material.RAW_IRON);
		steelAlloy.addIngredient(new RecipeChoice.MaterialChoice(Material.COAL, Material.CHARCOAL));
		
		ShapelessRecipe steelInCrucible = new ShapelessRecipe(new NamespacedKey(plugin, "steelInCrucible"), new ItemStack(Material.MAGMA_CREAM));
		steelInCrucible.addIngredient(Material.GLOWSTONE_DUST);
		steelInCrucible.addIngredient(Material.GHAST_TEAR);
		
		plugin.getServer().addRecipe(ironInCrucible);
		plugin.getServer().addRecipe(steelAlloy);
		plugin.getServer().addRecipe(steelInCrucible);
		
	}
	
	public static void initShapedRecipes(Plugin plugin) {
		
		ShapedRecipe macuahuitl = new ShapedRecipe(new NamespacedKey(plugin, "macuahuitl"), new ItemStack(Material.IRON_SWORD));
		macuahuitl.shape("OPO", "OPO", "*S*");
		macuahuitl.setIngredient('O', Material.GOLD_INGOT);
		macuahuitl.setIngredient('P', new RecipeChoice.MaterialChoice(Material.OAK_PLANKS, Material.ACACIA_PLANKS,
				Material.BIRCH_PLANKS, Material.DARK_OAK_PLANKS, Material.JUNGLE_PLANKS, Material.SPRUCE_PLANKS));
		macuahuitl.setIngredient('S', Material.STICK);
		
		ShapedRecipe crucible = new ShapedRecipe(new NamespacedKey(plugin, "crucible"), new ItemStack(Material.NETHER_WART));
		crucible.shape("S*S", "S*S", "*S*");
		crucible.setIngredient('S', Material.COBBLED_DEEPSLATE);
		
		ShapedRecipe refinedCrucible = new ShapedRecipe(new NamespacedKey(plugin, "refinedCrucible"), new ItemStack(Material.GHAST_TEAR));
		refinedCrucible.shape("S*S", "I*I", "*S*");
		refinedCrucible.setIngredient('S', Material.COBBLED_DEEPSLATE);
		refinedCrucible.setIngredient('I', Material.IRON_INGOT);
		
		ShapedRecipe newIronPick = new ShapedRecipe(new NamespacedKey(plugin, "ironPickaxe"), new ItemStack(Material.DIAMOND_PICKAXE));
		newIronPick.shape("III", "*S*", "*S*");
		newIronPick.setIngredient('I', Material.IRON_INGOT);
		newIronPick.setIngredient('S', Material.STICK);
		
		ShapedRecipe newIronAxe = new ShapedRecipe(new NamespacedKey(plugin, "ironPickaxe"), new ItemStack(Material.DIAMOND_AXE));
		newIronAxe.shape("II*", "IS*", "*S*");
		newIronAxe.setIngredient('I', Material.IRON_INGOT);
		newIronAxe.setIngredient('S', Material.STICK);
		
		ShapedRecipe newIronSword = new ShapedRecipe(new NamespacedKey(plugin, "ironPickaxe"), new ItemStack(Material.DIAMOND_SWORD));
		newIronSword.shape("*I*", "*I*", "*S*");
		newIronSword.setIngredient('I', Material.IRON_INGOT);
		newIronSword.setIngredient('S', Material.STICK);
		
		ShapedRecipe newIronHoe = new ShapedRecipe(new NamespacedKey(plugin, "ironPickaxe"), new ItemStack(Material.DIAMOND_HOE));
		newIronHoe.shape("II*", "*S*", "*S*");
		newIronHoe.setIngredient('I', Material.IRON_INGOT);
		newIronHoe.setIngredient('S', Material.STICK);		newIronSword.setIngredient('S', Material.STICK);
		
		ShapedRecipe newIronShovel = new ShapedRecipe(new NamespacedKey(plugin, "ironPickaxe"), new ItemStack(Material.DIAMOND_SHOVEL));
		newIronShovel.shape("*I*", "*S*", "*S*");
		newIronShovel.setIngredient('I', Material.IRON_INGOT);
		newIronShovel.setIngredient('S', Material.STICK);

		
		plugin.getServer().addRecipe(macuahuitl);
		plugin.getServer().addRecipe(crucible);
		plugin.getServer().addRecipe(refinedCrucible);
		plugin.getServer().addRecipe(newIronPick);
		plugin.getServer().addRecipe(newIronAxe);
		plugin.getServer().addRecipe(newIronSword);
		plugin.getServer().addRecipe(newIronHoe);
		plugin.getServer().addRecipe(newIronShovel);
		
		
	}
	
	public static void initFurnaceRecipes(Plugin plugin) {
		
		FurnaceRecipe moltenIron = new FurnaceRecipe(new NamespacedKey(plugin, "moltenIron"), new ItemStack(Material.BLAZE_POWDER, 1), Material.BLAZE_ROD, 0.4F, 1600);
		FurnaceRecipe moltenSteel = new FurnaceRecipe(new NamespacedKey(plugin, "moltenSteel"), new ItemStack(Material.NETHERITE_SCRAP, 1), Material.MAGMA_CREAM, 0.4F, 1600);
		
		plugin.getServer().addRecipe(moltenIron);
		plugin.getServer().addRecipe(moltenSteel);
		
	}
	
	public static void initGrindstoneRecipes() {
		
		API.addGrindstoneRecipe(Material.RAW_GOLD, Material.GOLD_INGOT);
		
	}
	
	public static void removeRecipes(Plugin plugin) {
		
		// Diamond Tools
		plugin.getServer().removeRecipe(NamespacedKey.minecraft("diamond_sword"));
		plugin.getServer().removeRecipe(NamespacedKey.minecraft("diamond_axe"));
		plugin.getServer().removeRecipe(NamespacedKey.minecraft("diamond_pickaxe"));
		plugin.getServer().removeRecipe(NamespacedKey.minecraft("diamond_hoe"));
		plugin.getServer().removeRecipe(NamespacedKey.minecraft("diamond_shovel"));

		// Gold Tools and Armor
		plugin.getServer().removeRecipe(NamespacedKey.minecraft("gold_sword"));
		plugin.getServer().removeRecipe(NamespacedKey.minecraft("gold_axe"));
		plugin.getServer().removeRecipe(NamespacedKey.minecraft("gold_pickaxe"));
		plugin.getServer().removeRecipe(NamespacedKey.minecraft("gold_hoe"));
		plugin.getServer().removeRecipe(NamespacedKey.minecraft("gold_shovel"));
		plugin.getServer().removeRecipe(NamespacedKey.minecraft("golden_helmet"));
		plugin.getServer().removeRecipe(NamespacedKey.minecraft("golden_chestplate"));
		plugin.getServer().removeRecipe(NamespacedKey.minecraft("golden_leggings"));
		plugin.getServer().removeRecipe(NamespacedKey.minecraft("golden_boots"));
		
		// Ore Progression
		plugin.getServer().removeRecipe(NamespacedKey.minecraft("blaze_powder"));
		plugin.getServer().removeRecipe(NamespacedKey.minecraft("iron_sword"));
		plugin.getServer().removeRecipe(NamespacedKey.minecraft("gold_nugget"));
		plugin.getServer().removeRecipe(NamespacedKey.minecraft("brewing_stand"));
		plugin.getServer().removeRecipe(NamespacedKey.minecraft("iron_ingot"));
		plugin.getServer().removeRecipe(NamespacedKey.minecraft("gold_ingot"));
		
	}

}
