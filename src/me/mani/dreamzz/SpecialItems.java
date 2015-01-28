package me.mani.dreamzz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.mani.dreamzz.util.ParticleEffect;
import me.mani.dreamzz.util.ParticleEffect.Offset;
import me.mani.dreamzz.util.ParticleEffect.ParticleEffectType;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;


public class SpecialItems {
	
	private static HashMap<Player, List<Item>> activeGrenades = new HashMap<>();
	
	public static void castSpecialItem(Type type, Player p, Action action) {
		switch (type) {
		case FIRE_WAND:
			castFireWand(p, action);
			break;
		case GRENADE:
			castGrenade(p, action);
			break;
		case GRENADE_LAUNCHER:
			castGrenadeLauncher(p, action);
			break;
		}
	}
	
	private static void castFireWand(Player p, Action action) {
		if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK)
			return;
		Location center = p.getLocation().add(0, 1, 0);		
		Bukkit.getScheduler().runTaskTimer(Dreamzz.getInstance(), new Runnable() {
			
			int i = 0;
			
			@Override
			public void run() {
			if (i != 15)
				i++;
			else
				return;
			Location loc = center.getDirection().multiply(i * 1.1f).toLocation(p.getWorld()).add(center);
			ParticleEffect.broadcast(ParticleEffectType.FLAME, loc, new Offset(0.5F, 0.5F, 0.5F), 0, 25);
			if (i > 5)
				loc.getWorld().strikeLightning(loc);
			}
			
		}, 0L, 2L);	
	}
	
	private static void castGrenade(Player p, Action action) {
		if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK)
			return;
		Location center = p.getLocation().add(0, 1, 0);
		Item item = p.getWorld().dropItem(center, new ItemStack(Type.GRENADE.getMaterial()));
		item.setVelocity(p.getLocation().getDirection().multiply(0.2D));
		item.setPickupDelay(Integer.MAX_VALUE);
		Bukkit.getScheduler().runTaskLater(Dreamzz.getInstance(), () -> { 
			item.getWorld().createExplosion(item.getLocation().getX(), item.getLocation().getY(), item.getLocation().getZ(), 4F, false, false); 
			item.remove(); 
		}, 60L);
	}
	
	private static void castGrenadeLauncher(Player p, Action action) {
		if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
			Location center = p.getLocation().add(0, 1, 0);
			Item item = p.getWorld().dropItem(center, new ItemStack(Type.GRENADE.getMaterial()));	
			item.setVelocity(p.getLocation().getDirection().multiply(3.0D));
			item.setPickupDelay(Integer.MAX_VALUE);
			if (activeGrenades.get(p) == null)
				activeGrenades.put(p, new ArrayList<>());
			activeGrenades.get(p).add(item);
			Bukkit.getScheduler().runTaskLater(Dreamzz.getInstance(), () -> { 
				if (item.isDead())
					return;
				item.getWorld().createExplosion(item.getLocation().getX(), item.getLocation().getY(), item.getLocation().getZ(), 4F, false, false);
				item.remove(); 
			}, 60L);
		}
		else if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
			if (activeGrenades.get(p) == null)
				return;
			for (Item item : activeGrenades.get(p)) {
				item.getWorld().createExplosion(item.getLocation().getX(), item.getLocation().getY(), item.getLocation().getZ(), 4F, false, false);
				item.remove();
			}
			activeGrenades.get(p).clear();
		}
	}
	
	public static enum Type {
		FIRE_WAND (Material.BLAZE_ROD),
		GRENADE (Material.FIREBALL),
		GRENADE_LAUNCHER (Material.IRON_BARDING);
		
		private Material material;
		
		private Type(Material material) {
			this.material = material;
		}
		
		public Material getMaterial() {
			return material;
		}
		
		public static boolean hasType(Material specialItem) {
			return getType(specialItem) != null;
		}
		
		public static Type getType(Material specialItem) {
			for (Type type : Type.values())
				if (type.material.equals(specialItem))
					return type;
			return null;
		}
	}
}
