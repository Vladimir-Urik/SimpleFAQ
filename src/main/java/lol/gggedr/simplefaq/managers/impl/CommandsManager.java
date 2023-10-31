package lol.gggedr.simplefaq.managers.impl;

import lol.gggedr.simplefaq.commands.FaqCommand;
import lol.gggedr.simplefaq.managers.Manager;
import net.md_5.bungee.config.Configuration;

public class CommandsManager implements Manager {

    public void onEnable() {
        Configuration config = getManager(ConfigurationManager.class).getMainConfig();

        String name = config.getString("command.name");
        String permission = config.getString("command.permission");

        FaqCommand command = new FaqCommand(name, permission);
        getPlugin().getProxy().getPluginManager().registerCommand(getPlugin(), command);
    }

}
