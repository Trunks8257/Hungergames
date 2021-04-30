package me.minebuilders.hg.commands;

import me.minebuilders.hg.HG;
import me.minebuilders.hg.PlayerData;
import me.minebuilders.hg.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class FreezeCmd extends BaseCmd {

	public FreezeCmd() {
		forcePlayer = false;
		cmdName = "freeze";
		forceInGame = false;
		argLength = 2;
		usage = "<player>";
	}

	@Override
	public boolean run() {
		Player p = Bukkit.getPlayer(args[1]);

		if (p == null) {
			Util.msg(sender, "&e" + args[2] + "&c Is not online!");
			return true;
		}

		PlayerData pd = HG.plugin.players.get(p.getUniqueId());

		if (pd == null || pd.getGame() == null) {
			Util.msg(sender, "&e" + args[2] + "&c Is not in a game!");
			return true;
		}

		pd.getGame().freeze(p);
		Util.scm(sender, "&6" + p.getDisplayName() + "&a Has been frozen!");
		Util.scm(p, "&cYou have been frozen by &6" + sender.getName() + "&a!");
		return true;
	}
}