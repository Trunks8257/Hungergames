package me.minebuilders.hg;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class SBDisplay {

	private final ScoreboardManager manager;
	private final Scoreboard board;
	private Objective ob;
	private final HashMap<String, Scoreboard> score = new HashMap<>();
	private final Game g;

	public SBDisplay(Game g) {
		this.manager = Bukkit.getScoreboardManager();
		this.board = manager.getNewScoreboard();
		this.ob = board.registerNewObjective("§7------------", "dummy");
		this.ob.setDisplaySlot(DisplaySlot.SIDEBAR);
		this.ob.setDisplayName("§6§l   HungerGames   ");
		this.g = g;
	}

	public void setAlive() {
		Score score = ob.getScore(ChatColor.GREEN + "Players-Alive:"); 
		
		score.setScore(g.getPlayers().size());
	}

	public void resetAlive() {
		board.resetScores(ChatColor.GREEN + "Players-Alive:");
		score.clear();
	}

	public void setSB(Player p) {
		score.put(p.getName(), p.getScoreboard());
		p.setScoreboard(board);
	}

	public void restoreSB(Player p) {
		if (score.get(p.getName()) == null) {
			p.setScoreboard(manager.getNewScoreboard());
		} else {
			p.setScoreboard(score.get(p.getName()));
			score.remove(p.getName());
		}
	}
}
