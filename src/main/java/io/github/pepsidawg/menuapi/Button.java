package io.github.pepsidawg.menuapi;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Button {
    private Material icon;
    private short modifier;
    private String title;
    private List<String> description;
    private ClickedCallback callback;

    public Button() {
        this(Material.STONE, "Button", null, null);
    }

    protected Button(Material icon, String title, List<String> description, ClickedCallback callback) {
        this.icon = icon;
        this.title = title;
        this.description = description;
        this.callback = callback;
        this.modifier = 0;
    }

    protected void clicked(Player player) {
        callback.clicked(player, this);
    }

    public Button setIcon(Material icon) {
        this.icon = icon;
        return this;
    }

    public Button setTitle(String title) {
        this.title = title;
        return this;
    }

    public Button setDescription(List<String> description) {
        this.description = description;
        return this;
    }

    public Button setModifier(short modifier) {
        this.modifier = modifier;
        return this;
    }

    public Button setCallback(ClickedCallback callback) {
        this.callback = callback;
        return this;
    }

    public Material getIcon() {
        return this.icon;
    }

    public String getTitle() {
        return this.title;
    }

    public List<String> getDescription() {
        return this.getDescription();
    }

    public short getModifier() {
        return this.modifier;
    }

    protected ItemStack build() {
        ItemStack item = new ItemStack(this.icon, 1);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(this.title);
        itemMeta.setLore(this.description);

        item.setItemMeta(itemMeta);
        item.setDurability(this.modifier);

        return item;
    }

    public Button copy() {
        return new Button(this.icon, this.title, this.description, this.callback).setModifier(this.modifier);
    }
}
