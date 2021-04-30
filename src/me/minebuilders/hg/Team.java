package me.minebuilders.hg;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Team {

	private final UUID leader;
	private final List<UUID> players = new ArrayList<>();
	private final List<UUID> pending = new ArrayList<>();
	
	public Team(UUID leader) {
		this.leader = leader;
		players.add(leader);
	}
	
	
	public void invite(Player p) {
		Util.scm(p, "&6*&b&m                                                                             &6*");
		Util.scm(p, "| &f" + Bukkit.getPlayer(leader).getDisplayName() + " &3Just invited you to a &fTeam&3!");
		Util.scm(p, "| &3Type &f/hg team accept &3To join!");
		Util.scm(p, "&6*&b&m                                                                             &6*");
		pending.add(p.getUniqueId());
	}
	
	public void acceptInvite(Player p) {
		pending.remove(p.getUniqueId());
		players.add(p.getUniqueId());
		Util.msg(p, "&3You successfully joined this team!");
	}
	
	public boolean isOnTeam(UUID p) {
		return (players.contains(p));
	}
	
	public boolean isPending(UUID p) {
		return (pending.contains(p));
	}
	
	public List<UUID> getPlayers() {
		return players;
	}
	
	public List<UUID> getPenders() {
		return pending;
	}
	
	public UUID getLeader() {
		return leader;
	}
}
