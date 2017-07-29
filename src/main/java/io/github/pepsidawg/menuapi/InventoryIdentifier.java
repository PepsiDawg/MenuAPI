package io.github.pepsidawg.menuapi;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.UUID;

public class InventoryIdentifier implements InventoryHolder {
    private UUID uuid;

    @Override
    public Inventory getInventory() {
        return null;
    }

    public void genUUID() {
        this.uuid = UUID.randomUUID();
    }

    public UUID getUID() {
        return this.uuid;
    }
}
