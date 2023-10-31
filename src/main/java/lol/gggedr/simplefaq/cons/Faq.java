package lol.gggedr.simplefaq.cons;

import lol.gggedr.simplefaq.managers.Managers;
import lol.gggedr.simplefaq.managers.impl.ConfigurationManager;
import lol.gggedr.simplefaq.utils.MessageUtils;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.config.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Faq {

    private final String id;
    private final String question;
    private final List<String> answer;

    public Faq(String id, String question, List<String> answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public List<BaseComponent[]> formatQuestion(int number) {
        Configuration faqConfig = Managers.getManager(ConfigurationManager.class).getFaqConfig();

        List<String> format = faqConfig.getStringList("question");
        format.replaceAll(s -> s.replace("%number%", String.valueOf(number)));
        format.replaceAll(s -> s.replace("%question%", question));

        String command = "/faq " + id;
        format.replaceAll(s -> s.replace("%command%", command));

        List<BaseComponent[]> components = new ArrayList<>();
        for(String s : format) {
            String colored = MessageUtils.color(s);

            BaseComponent[] component = TextComponent.fromLegacyText(colored);
            ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.RUN_COMMAND, command);

            for(BaseComponent baseComponent : component) {
                baseComponent.setClickEvent(clickEvent);
            }

            components.add(component);
        }

        return components;
    }

    public String formatAnswer() {
        Configuration faqConfig = Managers.getManager(ConfigurationManager.class).getFaqConfig();

        List<String> format = faqConfig.getStringList("answer");

        format.replaceAll(s -> s.replace("%question%", question));
        format.replaceAll(s -> s.replace("%answer%", MessageUtils.color(answer)));

        return MessageUtils.color(format);
    }
}
