package io.github.pepsidawg.menuapi;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public class ToggleButton extends Button {
    private boolean state;

    public ToggleButton() {
        this(Material.STONE, "Button", null, null, false);
    }

    protected ToggleButton(Material icon, String title, List<String> description, ClickedCallback callback, boolean state) {
        super(icon, title, description, callback);
        this.state = state;
    }

    public ToggleButton setState(boolean state) {
        this.state = state;
        return this;
    }

    public boolean getState() {
        return this.state;
    }

    @Override
    protected void clicked(Player player) {
        this.state = !this.state;
        super.clicked(player);
    }

    @Override
    public Button copy() {
        return ((ToggleButton)super.copy()).setState(this.state);
    }
}
