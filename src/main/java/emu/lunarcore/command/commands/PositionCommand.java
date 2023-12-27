package emu.lunarcore.command.commands;

import emu.lunarcore.command.Command;
import emu.lunarcore.command.CommandArgs;
import emu.lunarcore.command.CommandHandler;
import emu.lunarcore.util.Position;

@Command(
    label = "position",
    desc = "/position. Show your current position.",
    aliases = {"pos"}
)
public final class PositionCommand implements CommandHandler {

    @Override
    public void execute(CommandArgs args) {
        Position pos = args.getSender().getPos();
        Position rot = args.getSender().getRot();
        args.sendMessage("Position: " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + " Rotation: " + rot.getX() + ", " + rot.getY() + ", " + rot.getZ() + " Scene[Floor:" + args.getSender().getScene().getFloorId() + ",Plane:" + args.getSender().getScene().getPlaneId() + ",Entry:" + args.getSender().getScene().getEntryId() + "]");
    }
}