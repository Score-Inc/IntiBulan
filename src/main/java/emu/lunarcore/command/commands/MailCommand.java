package emu.lunarcore.command.commands;

import java.util.ArrayList;
import java.util.List;

import emu.lunarcore.command.Command;
import emu.lunarcore.command.CommandArgs;
import emu.lunarcore.command.CommandHandler;
import emu.lunarcore.game.inventory.GameItem;
import emu.lunarcore.game.mail.Mail;

@Command(label = "mail", aliases = {"m"}, permission = "admin.mail", requireTarget = true, desc = "/mail [content]. Sends the targeted player a system mail.")
public class MailCommand implements CommandHandler {

    @Override
    public void execute(CommandArgs args) {
        // Get attachments
        List<GameItem> attachments = new ArrayList<>();

        var it = args.getList().iterator();
        while (it.hasNext()) {
            try {
                String str = it.next();

                if (str.contains(":")) {
                    String[] split = str.split(":");

                    int itemId = Integer.parseInt(split[0]);
                    int count = Integer.parseInt(split[1]);

                    attachments.add(new GameItem(itemId, count));

                    it.remove();
                }
            } catch (Exception e) {

            }
        }

        // Build mail
        String content = String.join(" ", args.getList());
        Mail mail = new Mail("Test", "System Mail", content);

        for (GameItem item : attachments) {
            mail.addAttachment(item);
        }

        // Send to target
        args.getTarget().getMailbox().sendMail(mail);

        args.sendMessage("Sending mail to " + args.getTarget().getName());
    }
}
