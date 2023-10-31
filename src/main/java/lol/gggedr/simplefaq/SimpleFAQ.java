package lol.gggedr.simplefaq;

import lol.gggedr.simplefaq.managers.Manager;
import lol.gggedr.simplefaq.managers.Managers;
import lol.gggedr.simplefaq.managers.impl.CommandsManager;
import lol.gggedr.simplefaq.managers.impl.ConfigurationManager;
import lol.gggedr.simplefaq.managers.impl.FaqManager;
import lol.gggedr.simplefaq.managers.impl.MetricsManager;
import net.md_5.bungee.api.plugin.Plugin;

public final class SimpleFAQ extends Plugin {

    private static SimpleFAQ instance;

    @Override
    public void onEnable() {
        instance = this;

        Managers.register(ConfigurationManager.class);
        Managers.register(CommandsManager.class);
        Managers.register(FaqManager.class);
        Managers.register(MetricsManager.class);

        Managers.onEnable();
    }

    @Override
    public void onDisable() {
        Managers.onDisable();
    }

    public static SimpleFAQ getInstance() {
        return instance;
    }
}
