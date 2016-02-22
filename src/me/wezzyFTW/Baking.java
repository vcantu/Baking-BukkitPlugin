package me.wezzyFTW;

import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Baking extends JavaPlugin {
	
	public Permission craft = new Permission("baking.craft");
	
	@SuppressWarnings("deprecation")
	@Override
	public void onEnable() {
		new bakingListener(this);
		
		PluginManager pm = getServer().getPluginManager();
		pm.addPermission(craft);
		//Recipe remover
		Iterator<Recipe> it = getServer().recipeIterator();
		Recipe recipe;
		while(it.hasNext())
		{
			recipe = it.next();
			if (recipe != null && recipe.getResult().getType() == Material.BREAD)
			{
				it.remove();
			}
			else if (recipe != null && recipe.getResult().getType() == Material.CAKE)
			{
				it.remove();
			}
			else if (recipe != null && recipe.getResult().getType() == Material.COOKIE)
			{
				it.remove();
			}
			else if (recipe != null && recipe.getResult().getType() == Material.PUMPKIN_PIE)
			{
				it.remove();
			}
		}
		//Recipe remover^^
		
		//New Items
		//by Crafting
		//Yeast------------------------------
		ItemStack yeast = new ItemStack(Material.PUMPKIN_SEEDS, 2);
		ItemMeta yeastMeta = yeast.getItemMeta();
		yeastMeta.setDisplayName(ChatColor.GOLD + "Yeast");
		yeast.setItemMeta(yeastMeta);
		//Dough-----------------------------
		ItemStack dough = new ItemStack(Material.INK_SACK, 1, (byte) 7);
		ItemMeta doughMeta = dough.getItemMeta();
		doughMeta.setDisplayName(ChatColor.GRAY + "Bread Dough");
		dough.setItemMeta(doughMeta);
		//Unbaked Cake----------------
		ItemStack uCake = new ItemStack(Material.CAKE, 1);
		ItemMeta uCakeMeta = uCake.getItemMeta();
		uCakeMeta.setDisplayName(ChatColor.GOLD + "Unbaked Cake");
		uCake.setItemMeta(uCakeMeta);
		//Cookie Dough-----------------
		ItemStack cookieDough = new ItemStack(Material.QUARTZ, 1);
		ItemMeta cookieDoughMeta = cookieDough.getItemMeta();
		cookieDoughMeta.setDisplayName(ChatColor.GRAY + "Cookie Dough");
		cookieDough.setItemMeta(cookieDoughMeta);
		//Unbaked Cookie-------------
		ItemStack uCookie = new ItemStack(Material.INK_SACK, 8, (byte) 8);
		ItemMeta uCookieMeta = uCookie.getItemMeta();
		uCookieMeta.setDisplayName(ChatColor.GOLD + "Unbaked Cookie");
		uCookie.setItemMeta(uCookieMeta);
		//Unbaked Pie-------------------
		ItemStack uPie = new ItemStack(Material.PUMPKIN_PIE, 1);
		ItemMeta uPieMeta = uPie.getItemMeta();
		uPieMeta.setDisplayName(ChatColor.GOLD + "Unbaked Pie");
		uPie.setItemMeta(uPieMeta);
		//not Crafting
		//Flour------------------------------
		ItemStack flour = new ItemStack(Material.SUGAR, 1);
		ItemMeta flourMeta = flour.getItemMeta();
		flourMeta.setDisplayName(ChatColor.GRAY + "Flour");
		flour.setItemMeta(flourMeta);
		//Butter-----------------------------
		ItemStack butter = new ItemStack(Material.GOLD_INGOT, 1);
		ItemMeta butterMeta = butter.getItemMeta();
		butterMeta.setDisplayName(ChatColor.YELLOW + "Butter");
		butter.setItemMeta(butterMeta);
		
		//Recipes
		//yeast
		ShapelessRecipe yeast1 = new ShapelessRecipe(yeast);
		yeast1.addIngredient(Material.WHEAT);
		yeast1.addIngredient(Material.RED_MUSHROOM);
		this.getServer().addRecipe(yeast1);
		ShapelessRecipe yeast2 = new ShapelessRecipe(yeast);
		yeast2.addIngredient(Material.WHEAT);
		yeast2.addIngredient(Material.BROWN_MUSHROOM);
		this.getServer().addRecipe(yeast2);
		//dough
		ShapelessRecipe dough1 = new ShapelessRecipe(dough);
		dough1.addIngredient(Material.WATER_BUCKET);
		dough1.addIngredient(2, Material.SUGAR);
		dough1.addIngredient(Material.PUMPKIN_SEEDS);
		this.getServer().addRecipe(dough1);
		//unbaked cake
		ShapedRecipe uCake1 = new ShapedRecipe(uCake);
		uCake1.shape("MSM", "SBS", "ESE");
		uCake1.setIngredient('M', Material.MILK_BUCKET);
		uCake1.setIngredient('S', Material.SUGAR);
		uCake1.setIngredient('B',Material.GOLD_INGOT);
		uCake1.setIngredient('E', Material.EGG);
		this.getServer().addRecipe(uCake1);
		//cookie dough
		ShapelessRecipe cDough1 = new ShapelessRecipe(cookieDough);
		cDough1.addIngredient(Material.GOLD_INGOT);
		cDough1.addIngredient(3, Material.SUGAR);
		cDough1.addIngredient(new MaterialData(Material.INK_SACK, (byte) 3));
		this.getServer().addRecipe(cDough1);
		//unbaked pie
		ShapelessRecipe uPie1 = new ShapelessRecipe(uPie);
		uPie1.addIngredient(Material.GOLD_INGOT);
		uPie1.addIngredient(3, Material.SUGAR);
		uPie1.addIngredient(Material.EGG);
		uPie1.addIngredient(Material.PUMPKIN);
		this.getServer().addRecipe(uPie1);
		//cookies
		ShapelessRecipe uCookie1 = new ShapelessRecipe(uCookie);
		uCookie1.addIngredient(Material.QUARTZ);
		this.getServer().addRecipe(uCookie1);
		
		//for now
		ShapelessRecipe flour1 = new ShapelessRecipe(flour);
		flour1.addIngredient(Material.WHEAT);
		this.getServer().addRecipe(flour1);
		ShapelessRecipe butter1 = new ShapelessRecipe(butter);
		butter1.addIngredient(Material.MILK_BUCKET);
		this.getServer().addRecipe(butter1);
		
		//Furnace Recipes
		this.getServer().addRecipe(new FurnaceRecipe(new ItemStack(Material.BREAD), Material.INK_SACK, 7));
		this.getServer().addRecipe(new FurnaceRecipe(new ItemStack(Material.CAKE), Material.CAKE));
		this.getServer().addRecipe(new FurnaceRecipe(new ItemStack(Material.COOKIE), Material.INK_SACK, 8));
		this.getServer().addRecipe( new FurnaceRecipe(new ItemStack(Material.PUMPKIN_PIE), Material.PUMPKIN_PIE));
	}
	
	@Override
	public void onDisable() {
		
	}
}
