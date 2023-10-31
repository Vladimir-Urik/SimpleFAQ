package lol.gggedr.simplefaq.managers.impl;

import lol.gggedr.simplefaq.managers.Manager;
import lol.gggedr.simplefaq.utils.FileUtils;
import net.md_5.bungee.config.Configuration;

import java.io.File;

public class ConfigurationManager implements Manager {

    private Configuration mainConfig;
    private Configuration faqConfig;

    public void onEnable() {
        getPlugin().getDataFolder().mkdirs();

        File mainConfigFile = new File(getPlugin().getDataFolder(), "config.yml");
        FileUtils.copyFileFromJar(mainConfigFile.getName(), mainConfigFile);
        mainConfig = FileUtils.loadConfiguration(mainConfigFile);

        File faqFile = new File(getPlugin().getDataFolder(), "faq.yml");
        FileUtils.copyFileFromJar(faqFile.getName(), faqFile);
        faqConfig = FileUtils.loadConfiguration(faqFile);
    }

    public Configuration getMainConfig() {
        return mainConfig;
    }

    public Configuration getFaqConfig() {
        return faqConfig;
    }

}
