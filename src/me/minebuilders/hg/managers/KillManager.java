package me.minebuilders.hg.managers;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class KillManager {

	public String getDeathString(DamageCause dc, String name) {
		switch (dc) {
		case ENTITY_EXPLOSION: return (name + " was blown to bits!");
		case BLOCK_EXPLOSION: return (name + " was blown to bits!");
		case CUSTOM: return (name + " was killed by an unknown cause!");
		case FALL: return (name + " hit the ground too hard!");
		case FALLING_BLOCK: return (name + " Was smashed by a block!");
		case FIRE: return (name + " was Burned alive!");
		case FIRE_TICK: return (name + " was Burned alive!");
		case PROJECTILE: return (name + " got hit by a projectile!");
		case LAVA: return (name + " fell into a pit of lava!");
		case MAGIC: return (name + " was destroyed by magic!");
		case SUICIDE: return (name + " couldn't handle hungergames!");
		default: return (name + " was killed by " + dc.toString().toLowerCase());
		}
	}

	public String getKillString(String name, Entity e) {
		switch (e.getType()) {
		case PLAYER: return ("&6" + name + " &c&lwas killed by &6" + e.getName() + " &cusing a(n) &6" + ((Player)e).getItemInHand().getType().name().toLowerCase() + "&c!");
		case ZOMBIE: return (name + " was ripped to bits by a Zombie!");
		case SKELETON: return (name + " was shot in the face by a skeleton");
		case ARROW: return (name + " was shot in the face by a skeleton");
		case SPIDER: return (name + " was eaten alive by a Spider!");
		default: return (name + " spontaneously died!");
		}
	}
}
