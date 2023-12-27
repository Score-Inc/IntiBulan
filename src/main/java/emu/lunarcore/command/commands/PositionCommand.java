package emu.lunarcore.command.commands;

import emu.lunarcore.command.Command;
import emu.lunarcore.command.CommandArgs;
import emu.lunarcore.command.CommandHandler;
import emu.lunarcore.game.player.Player;
import emu.lunarcore.util.Position;

@Command(
    label = "position",
    desc = "/position. Show your current position.",
    aliases = {"pos"}
)
public final class PositionCommand implements CommandHandler {

    @Override
    public void execute(Player sender, CommandArgs args) {
        Position pos = sender.getPos();
        Position rot = sender.getRot();
        this.sendMessage(sender, "Position: " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + " Rotation: " + rot.getX() + ", " + rot.getY() + ", " + rot.getZ() + " Scene[Floor:" + sender.getScene().getFloorId() + ",Plane:" + sender.getScene().getPlaneId() + ",Entry:" + sender.getScene().getEntryId() + "]");
    }
}