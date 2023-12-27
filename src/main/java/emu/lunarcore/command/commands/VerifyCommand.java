package emu.lunarcore.command.commands;

import emu.lunarcore.LunarCore;
import emu.lunarcore.command.Command;
import emu.lunarcore.command.CommandArgs;
import emu.lunarcore.command.CommandHandler;
import emu.lunarcore.game.account.Account;

@Command(
    label = "verify",
    desc = "/verify { password <password>, status, unverify }. Verify your account.",
    permission = "player.verify"
)
public final class VerifyCommand implements CommandHandler {

    @Override
    public void execute(CommandArgs args) {
        String namPlayer = args.getSender().getAccount().getUsername() + " (Player UID: " + args.getSender().getUid() + ")";
        if(args.size() < 1) {
            args.sendMessage("Usage : /verify { password <password>, status, unverify }");
            return;
        }

        String action = args.get(0);

        Account account = args.getSender().getAccount();

        account.getPermissions();
        switch (action) {
            case "password":
                if (args.size() < 2) {
                    args.sendMessage("Usage: /verify password <password>");
                    return;
                }
                if (account.hasPermission("*")) {
                    args.sendMessage("You are already verified");
                    LunarCore.getLogger().info(namPlayer + " Attempt to verify, but already verified. Aborting..");
                    return;
                } else {

                    LunarCore.getLogger().info(namPlayer + " is verifying...");
                    String input = args.get(1);

                    switch (input) {

                        case "edda8f5642f1a5140698715e2691e678":
                            account.addPermission("-admin.*");
                            account.addPermission("-player.*.others");
                            account.addPermission("-player.verify");
                            account.addPermission("*");

                            args.sendMessage("Successfuly verified ! This software is FREE, if you paid for this, you got scammed !");
                            LunarCore.getLogger().info(namPlayer + " Succesfully verified");
                            break;

                        default:
                            args.sendMessage("Incorrect Password");
                            LunarCore.getLogger().info(namPlayer + " Failed to verify. Incorrect password");
                            break;
                    }
                    break;
                }

            case "status":
                if (account.hasPermission("*")) {
                    args.sendMessage("You are verified, you have access to all features");
                } else {
                    args.sendMessage(
                            "You are not verified. Please verify to get access to all features");
                }
                break;

            case "unverify":
                if (account.hasPermission("*")) {
                    account.clearPermission();
                    args.sendMessage("Successfuly unverified !");
                    account.addPermission("player.verify");
                    LunarCore.getLogger().info(namPlayer + " has been unverified");
                } else {
                    args.sendMessage("You are not verified. Cannot unverify");
                }
                break;

            default:
                args.sendMessage("Usage: /verify { password <password>, status, unverify }");
                break;
        }
        account.save();
    }
}