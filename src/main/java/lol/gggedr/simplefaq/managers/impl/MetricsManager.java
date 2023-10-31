package lol.gggedr.simplefaq.managers.impl;

import lol.gggedr.simplefaq.managers.Manager;
import lol.gggedr.simplefaq.metrics.BStats;

public class MetricsManager implements Manager {

    private final int pluginId = 20182;

    @Override
    public void onEnable() {
        new BStats(getPlugin(), pluginId);
    }
}
