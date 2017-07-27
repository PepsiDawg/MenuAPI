package io.github.pepsidawg.menuapi;

import org.bukkit.plugin.java.JavaPlugin;

public class MenuAPI extends JavaPlugin {
    static MenuAPI self;

    @Override
    public void onEnable() {
        self = this;
    }

    @Override
    public void onDisable() {

    }

    public static MenuAPI getInstance() {
        return self;
    }
}
