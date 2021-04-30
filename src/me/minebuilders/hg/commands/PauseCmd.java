package me.minebuilders.hg.commands;

import me.minebuilders.hg.Game;
import me.minebuilders.hg.HG;
import me.minebuilders.hg.Status;
import me.minebuilders.hg.Util;
import org.bukkit.ChatColor;

public class PauseCmd extends BaseCmd {

	public PauseCmd() {
		forcePlayer = false;
		cmdName = "pause";
		forceInGame = false;
		argLength = 2;
		usage = "<game>";
	}

	@Override
	public boolean run() {
		Game g = HG.manager.getGame(args[1]);
		if (g != null) {
			if (g.getStatus() != Status.RUNNING && g.getStatus() != Status.PAUSED) {
				Util.scm(sender, "&6" + args[1] + "&c Cannot be paused because it is not running!");
				return true;
			}
			g.pause();
			Util.scm(sender, "&6" + args[1] + "&a Has been " + (g.getStatus() == Status.PAUSED ? "" : "un") + "paused!");
		} else sender.sendMessage(ChatColor.RED + "This game does not exist!");
		return true;
	}
}