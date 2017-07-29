package io.github.pepsidawg.menuapi;

import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class MenuAPI {
    private Plugin host;

    public MenuAPI(Plugin plugin) {
        this.host = plugin;
    }

    public Plugin getHostPlugin() {
        return this.host;
    }

    public Menu createMenu() {
        return new Menu(this.host);
    }

    public Menu createMenu(String title, int size) {
        return new Menu(title, size, this.host);
    }

    public Button createButton() {
        return new Button();
    }

    public Button createButton(Material icon, String title, List<String> description, ClickedCallback callback) {
        return new Button(icon, title, description, callback);
    }

    public ToggleButton createToggleButton() {
        return new ToggleButton();
    }
}
