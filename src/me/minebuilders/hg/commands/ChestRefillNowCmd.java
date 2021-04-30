package me.minebuilders.hg.commands;

import me.minebuilders.hg.Game;
import me.minebuilders.hg.HG;
import me.minebuilders.hg.Status;
import me.minebuilders.hg.Util;
import org.bukkit.Bukkit;

public class ChestRefillNowCmd extends BaseCmd {

    public ChestRefillNowCmd() {
        forcePlayer = true;
        cmdName = "chestrefillnow";
        forceInGame = false;
        argLength = 2;
        usage = "<arena-name>";
    }

    @Override
    public boolean run() {
        Game game =  HG.plugin.players.get(player.getUniqueId()).getGame();
        if (game != null) {
            Status st = game.getStatus();
            if (st != Status.RUNNING) {
                Util.msg(player, "That game is not running");
                return true;
            }
            game.restoreChests(game);
            Util.msg(player, "Chests were refilled.");
        } else {
            Util.msg(player, "That game is not running or doesn't exist");
        }
        return true;
    }

}
