package lol.gggedr.simplefaq.commands;

import lol.gggedr.simplefaq.cons.Faq;
import lol.gggedr.simplefaq.managers.Managers;
import lol.gggedr.simplefaq.managers.impl.ConfigurationManager;
import lol.gggedr.simplefaq.managers.impl.FaqManager;
import lol.gggedr.simplefaq.utils.MessageUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;

import java.util.List;

public class FaqCommand extends Command {

    public FaqCommand(String name, String permission) {
        super(name, permission);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Configuration faqConfig = Managers.getManager(ConfigurationManager.class).getFaqConfig();

        List<Faq> faqs = Managers.getManager(FaqManager.class).getFaqs();

        if(args.length == 1) {
            String id = args[0];
            Faq faq = Managers.getManager(FaqManager.class).getFaq(id);

            if(faq == null) {
                sender.sendMessage(MessageUtils.color(faqConfig.getString("not-found")));
                return;
            }

            sender.sendMessage(faq.formatAnswer());
            return;
        }


        String headerMessage = MessageUtils.color(faqConfig.getStringList("header"));
        sender.sendMessage(MessageUtils.color(headerMessage));
        sender.sendMessage("§f ");

        for(int i = 0; i < faqs.size(); i++) {
            Faq faq = faqs.get(i);
            List<BaseComponent[]> questions = faq.formatQuestion(i + 1);

            for(BaseComponent[] question : questions) {
                sender.sendMessage(question);
            }

            sender.sendMessage("§f ");
        }

        String footerMessage = MessageUtils.color(faqConfig.getStringList("footer"));
        sender.sendMessage(MessageUtils.color(footerMessage));
    }
}
