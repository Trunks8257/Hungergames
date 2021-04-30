package me.minebuilders.hg.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import me.minebuilders.hg.HG;
import me.minebuilders.hg.Util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class RandomItems {

	private FileConfiguration item = null;
	private File customConfigFile = null;
	public int size = 0;
	private final HG plugin;

	public RandomItems(HG plugin) {
		this.plugin = plugin;
		reloadCustomConfig();
		load();
	}

	public void reloadCustomConfig() {
		if (customConfigFile == null) {
			customConfigFile = new File(plugin.getDataFolder(), "items.yml");
		}
		if (!customConfigFile.exists()) {
			try {
				customConfigFile.createNewFile();
			}
			catch (IOException e) {
				Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create items.yml!");
			}
			item = YamlConfiguration.loadConfiguration(customConfigFile);
			saveCustomConfig();
			Util.log("New items.yml file has been successfully generated!");
		} else {
			item = YamlConfiguration.loadConfiguration(customConfigFile);
		}
	}

	public FileConfiguration getCustomConfig() {
		if (item == null) {
			this.reloadCustomConfig();
		}
		return item;
	}

	public void saveCustomConfig() {
		if (item == null || customConfigFile == null) {
			return;
		}
		try {
			getCustomConfig().save(customConfigFile);
		} catch (IOException ex) {
			Util.log("Could not save config to " + customConfigFile);
		}
	}

	public void load() {
		size = 0;
		if (item.getStringList("items").isEmpty()) {
			setDefaultss();
			saveCustomConfig();
			reloadCustomConfig();
			Util.log("generating defaults for random items!");
		}
		for (String s : item.getStringList("items")) {
			String[] amount = s.split(" ");
			for (String p : amount)
				if (p.startsWith("x:")) {
					int c = Integer.parseInt(p.replace("x:", ""));
					while(c != 0) {
						c--;
						plugin.items.put(plugin.items.size() + 1, plugin.ism.getItem(s.replace("x:", ""), true));
						size++;
					}
				} else
					plugin.items.put(plugin.items.size() + 1, plugin.ism.getItem(s, true));
			size++;
		}
		Util.log(plugin.items.size() + " Random items have been loaded!");
	}

	public void setDefaultss() {
		ArrayList <String> s = new ArrayList <String>();
		s.add("272 1 x:5");
		s.add("283 1");
		s.add("282 1 x:2");
		s.add("291 1");
		s.add("298 1 x:2");
		s.add("299 1 x:2");
		s.add("300 1 x:2");
		s.add("306 1 x:2");
		s.add("307 1 x:2");
		s.add("308 1 x:2");
		s.add("309 1 x:2");//280
		s.add("261 1 x:3");
		s.add("262 20 x:2");
		s.add("335 1 x:2");
		s.add("346 1");
		s.add("345 1");
		s.add("280 1 name:&6TrackingStick_&aUses:_5");
		s.add("314 1");
		s.add("315 1");
		s.add("352 1 x:2");
		s.add("316 1");
		s.add("317 1");
		s.add("276 1 name:&6Death_Dealer");
		s.add("322 1");
		s.add("303 1 x:1");
		s.add("304 1 x:1");
		s.add("357 2 x:3");
		s.add("360 1 x:4");
		s.add("364 1 x:2");
		s.add("368 1 x:2");
		s.add("373:8194 1 x:2");
		s.add("373:8197 1 x:2");
		s.add("373:16420 1");
		s.add("373:16385 1 x:2");
		s.add("260 2 x:5");
		item.set("items", s);
	}
}