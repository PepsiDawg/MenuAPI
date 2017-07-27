package io.github.pepsidawg.menuapi.components;

import io.github.pepsidawg.menuapi.MenuAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class Menu implements Listener {
    private int size;
    private String title;
    private Map<Integer, Button> items;
    private boolean opened;
    private Player player;
    private Inventory inventory;

    public Menu() {
        this("Menu", 9);
    }

    public Menu(String title, int size) {
        this.title = title;
        this.size = size;
        this.items = new HashMap<>();
        this.opened = false;
        Bukkit.getServer().getPluginManager().registerEvents(this, MenuAPI.getInstance());
    }

    public void open(Player player) {
        this.inventory = buildInventory();
        player.openInventory(this.inventory);
        this.opened = true;
        this.player = player;
    }

    public String getTitle() {
        return this.title;
    }

    public int getSize() {
        return this.size;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isOpened() {
        return this.opened;
    }

    private Inventory buildInventory() {
        InventoryIdentifier holder = new InventoryIdentifier();
        holder.genUUID();

        Inventory temp = Bukkit.createInventory(holder, this.size, this.title);

        for(int key : this.items.keySet()) {
            temp.setItem(key, this.items.get(key).build());
        }

        return temp;
    }

    public void close(Player player) {
        player.closeInventory();
        this.opened = false;
        this.player = null;
    }

    public void refresh(Player player) {
        this.inventory = buildInventory();
    }

    public void addButton(Button button, int slot) {
        this.items.put(slot, button);
    }

    @EventHandler
    public void inventoryClosed(InventoryCloseEvent event) {
        if(validateInventory(event.getInventory())) {
            this.player = null;
            this.opened = false;
        }
    }

    @EventHandler
    public void clicked(InventoryClickEvent event) {
        Inventory topInv = event.getView().getTopInventory();
        String type = event.getClickedInventory().getType().name();

        if(validateInventory(topInv)) {
            int slot = event.getSlot();
            event.setCancelled(true);
            if(this.items.containsKey(slot) && !type.equalsIgnoreCase("player")) {
                this.items.get(slot).clicked(this.player);
            }
        }
    }

    @EventHandler
    public void itemMove(InventoryMoveItemEvent event) {
        if(validateInventory(event.getDestination())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void itemDrag(InventoryDragEvent event) {
        Bukkit.broadcastMessage("test");
        if(this.player == event.getWhoClicked() && this.isOpened()) {
            event.setCancelled(true);
        }
    }

    private boolean validateInventory(Inventory inv) {
        if(inv != null && inv.getHolder() instanceof  InventoryIdentifier && this.inventory != null) {
            InventoryIdentifier self = (InventoryIdentifier) this.inventory.getHolder();
            InventoryIdentifier other = (InventoryIdentifier) inv.getHolder();

            return (self.getUID() == other.getUID()) && this.opened;
        }
        return false;
    }
}
