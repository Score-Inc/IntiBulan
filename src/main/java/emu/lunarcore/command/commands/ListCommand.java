
package emu.lunarcore.command.commands;

import emu.lunarcore.LunarCore;
import emu.lunarcore.command.Command;
import emu.lunarcore.command.CommandArgs;
import emu.lunarcore.command.CommandHandler;
import emu.lunarcore.game.player.Player;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

@Command(label = "list", desc = "/list. Lists all players on the server.", permission = "player.list")
public final class ListCommand implements CommandHandler {

    @Override
    public void execute(Player sender, CommandArgs args) {
        // Get player list
        Int2ObjectMap<Player> playersMap = LunarCore.getGameServer().getOnlinePlayers();

        if (playersMap.size() != 0) {
            StringBuilder playerSet = new StringBuilder();

            playersMap
                    .values()
                    .forEach(
                            player -> {
                                playerSet.append(player.getName()).append(" - ").append(player.getUid());
                                playerSet.append(", ");
                            });
            String players = playerSet.toString();
            this.sendMessage(sender,
                    "Players online (" + playersMap.size() + ") :\n " + players.substring(0, players.length() - 2));
        }
    }
}