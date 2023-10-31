package lol.gggedr.simplefaq.managers.impl;

import lol.gggedr.simplefaq.cons.Faq;
import lol.gggedr.simplefaq.managers.Manager;

import java.util.ArrayList;
import java.util.List;

public class FaqManager implements Manager {

    private final List<Faq> faqs = new ArrayList<>();

    @Override
    public void onEnable() {
        ConfigurationManager configurationsManager = getManager(ConfigurationManager.class);
        faqs.clear();

        configurationsManager.getFaqConfig().getSection("content").getKeys().forEach(id -> {
            String question = configurationsManager.getFaqConfig().getString("content." + id + ".question");
            List<String> answer = configurationsManager.getFaqConfig().getStringList("content." + id + ".answer");
            faqs.add(new Faq(id, question, answer));
        });
    }

    public List<Faq> getFaqs() {
        return faqs;
    }

    public Faq getFaq(String id) {
        return faqs.stream().filter(faq -> faq.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
    }

}
