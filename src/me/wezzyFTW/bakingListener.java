package me.wezzyFTW;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class bakingListener implements Listener  {

	public bakingListener(Baking plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onItemPrepare(PrepareItemCraftEvent ev) {
		//Yeast------------------------------
		ItemStack yeast = new ItemStack(Material.PUMPKIN_SEEDS, 1);
		ItemMeta yeastMeta = yeast.getItemMeta();
		yeastMeta.setDisplayName(ChatColor.GOLD + "Yeast");
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
		
		if (ev.getRecipe().getResult().equals(dough))
		{
			if (!ev.getInventory().containsAtLeast(flour, 2) && !ev.getInventory().contains(yeast))
				ev.getInventory().setResult(null); return;
		} else if (ev.getRecipe().getResult().equals(uCake))
		{
			if (!ev.getInventory().containsAtLeast(flour, 3) && !ev.getInventory().contains(butter))
				ev.getInventory().setResult(null); return;
		} else if (ev.getRecipe().getResult().equals(cookieDough))
		{
			if (!ev.getInventory().containsAtLeast(flour, 2) && !ev.getInventory().contains(butter))
				ev.getInventory().setResult(null); return;
		} else if (ev.getRecipe().getResult().equals(uPie))
		{
			if (!ev.getInventory().containsAtLeast(flour, 2) && !ev.getInventory().contains(butter))
				ev.getInventory().setResult(null); return;
		} else if (ev.getRecipe().getResult().equals(uCookie))
		{
			if (!ev.getInventory().contains(cookieDough))
				ev.getInventory().setResult(null); return;
		}
		//disabling butter, uCookies, cookieDough and dough
		if (ev.getInventory().contains(butter)) {
			if (!ev.getRecipe().getResult().equals(uCake) && !ev.getRecipe().getResult().equals(cookieDough) && !ev.getRecipe().getResult().equals(uPie) && !ev.getRecipe().getResult().equals(butter))
				ev.getInventory().setResult(null); return;
		} if (ev.getInventory().contains(uCookie)) {
			ev.getInventory().setResult(null); return;
		} if (ev.getInventory().contains(dough)) {
			ev.getInventory().setResult(null); return;
		} if (ev.getInventory().contains(cookieDough)) {
			if (!ev.getRecipe().getResult().equals(uCookie))
				ev.getInventory().setResult(null); return;
		}
	}
	
	@EventHandler
	public void onSmelt(FurnaceSmeltEvent ev)
	{
		if (ev.getSource().getType() == Material.INK_SACK && !ev.getSource().hasItemMeta())
		{
			ev.setCancelled(true);
		}
		else if (ev.getSource().getType() == Material.CAKE && !ev.getSource().hasItemMeta())
		{
			ev.setCancelled(true);
		}
		else if (ev.getSource().getType() == Material.PUMPKIN_PIE && !ev.getSource().hasItemMeta())
		{
			ev.setCancelled(true);
		}		
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent ev) {
		Player p = ev.getPlayer();
		if (p.getItemInHand().getType() == Material.CAKE && p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Unbaked Cake"))
		{
			ev.setCancelled(true);
			p.sendMessage("You cannot place " + ChatColor.GOLD + "Unbaked Cake");
		}
		if (p.getItemInHand().getType() == Material.PUMPKIN_SEEDS && p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Yeast"))
		{
			ev.setCancelled(true);
			p.sendMessage("You cannot place " + ChatColor.GOLD + "Yeast");
		}
	}
	
	@EventHandler
	public void onCraft(CraftItemEvent ev) {
		ItemStack i = ev.getCurrentItem();
		Player p = (Player)ev.getWhoClicked();
		if (!p.hasPermission("baking.craft") &&
			            ((i.getType() == Material.PUMPKIN_SEEDS && i.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Yeast")) ||
				(i.getType() == Material.INK_SACK && i.getItemMeta().getDisplayName().equals(ChatColor.GRAY + "Bread Dough")) ||
				(i.getType() == Material.CAKE && i.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Unbaked Caket")) ||
				(i.getType() == Material.QUARTZ && i.getItemMeta().getDisplayName().equals(ChatColor.GRAY + "Cookie Dough")) ||
				(i.getType() == Material.INK_SACK && i.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Unbaked Cookie")) ||
				(i.getType() == Material.SUGAR && i.getItemMeta().getDisplayName().equals(ChatColor.GRAY + "Flour")) ||
				(i.getType() == Material.GOLD_INGOT && i.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Butter")) ))
		{
			ev.setCancelled(true);
			p.sendMessage("You cannot craft that");
		}
	}
}
