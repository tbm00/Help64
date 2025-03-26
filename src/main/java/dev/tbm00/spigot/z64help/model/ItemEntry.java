package dev.tbm00.spigot.z64help.model;

import java.util.List;

import org.bukkit.Material;

public class ItemEntry {
    private String type;
    private String name;
    private Material mat;
    private Boolean glowing;
    private List<String> lore;

    public ItemEntry(String type, String name, Material mat, Boolean glowing, List<String> lore) {
        this.type = type;
        this.name = name;
        this.mat = mat;
        this.glowing = glowing;
        this.lore = lore;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Material getMaterial() {
        return mat;
    }

    public void setMaterial(Material mat) {
        this.mat = mat;
    }

    public Boolean getGlowing() {
        return glowing;
    }

    public void setGlowing(Boolean glowing) {
        this.glowing = glowing;
    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }
}