package com.william.plugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin implements Listener{

	@Override
	public void onEnable() {
		System.out.println("first plugin has worked (sorta)");
		
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		System.out.println("first plugin disabled");
	}
	
	//Damage is reduced by 50%:
	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent e) {
		
		double finalDamage = e.getFinalDamage();
		
		e.setDamage(finalDamage * 0.5);
		
	}
	
	//Sends message "Egg thrown" to player when they throw an egg:
	@EventHandler
	public void onThrow(PlayerEggThrowEvent e) {
		
		Player player = e.getPlayer();
		
		player.sendMessage("Egg thrown");
		
	}
	
	//Player will be given regeneration 2 and full hunger when they leave a bed:
	@EventHandler
	public void onPlayerLeaveBed(PlayerBedLeaveEvent e) {
		
		Player player = e.getPlayer();
		
		player.setFoodLevel(20);
		player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 2));
		
		player.sendMessage(ChatColor.GREEN + "Your health has been restored!");
		
	}
	
	//Player will be given potion effects when they eat certain foods:
	@EventHandler
	public void onEntityEat(PlayerItemConsumeEvent e) {
		ItemStack food = e.getItem();
		Player player = e.getPlayer();
		
		if (food.getType() == Material.MELON) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 2));
		}
		else if (food.getType() == Material.GOLDEN_CARROT) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 200, 2));
		}
		else if (food.getType() == Material.CARROT) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 200, 2));
		}
		else if (food.getType() == Material.MUSHROOM_SOUP || food.getType() == Material.BEETROOT_SOUP) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 5));
		}
		else if (food.getType() == Material.COOKED_FISH || food.getType() == Material.RAW_FISH) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 200, 1));
		}
	}
	
}
