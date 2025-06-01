package dev.tbm00.spigot.help64;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;

import dev.tbm00.spigot.help64.model.ItemEntry;

public class ConfigHandler {
    private final Help64 javaPlugin;
    private String chatPrefix;
    private boolean featureEnabled = false;
    private final List<ItemEntry> randomItems = new ArrayList<>();

    /**
     * Constructs a ConfigHandler instance.
     *
     * @param javaPlugin the main plugin instance
     */
    public ConfigHandler(Help64 javaPlugin) {
        this.javaPlugin = javaPlugin;
        try {
            loadLanguageSection();
            loadFeatureSection();
            if (featureEnabled) loadRandomItemSection();
        } catch (Exception e) {
            javaPlugin.log(ChatColor.RED, "Caught exception loading config: ");
            javaPlugin.getLogger().warning(e.getMessage());
        }
    }

    /**
     * Loads the "lang" section of the configuration.
     */
    private void loadLanguageSection() {
        ConfigurationSection section = javaPlugin.getConfig().getConfigurationSection("lang");
        if (section!=null)
            chatPrefix = section.contains("prefix") ? section.getString("prefix") : null;
    }

    /**
     * Loads the "feature" section of the configuration.
     */
    private void loadFeatureSection() {
        ConfigurationSection section = javaPlugin.getConfig().getConfigurationSection("feature");
        if (section!=null)
            featureEnabled = section.contains("enabled") ? section.getBoolean("enabled") : false;
    }

    /**
     * Loads the "randomItems" section of the configuration.
     */
    private void loadRandomItemSection() {
        ConfigurationSection section = javaPlugin.getConfig().getConfigurationSection("randomItems");

        if (section!=null) {
            for (String key : section.getKeys(false)) {
                ConfigurationSection configItemEntry = section.getConfigurationSection(key);
                if (configItemEntry == null || !configItemEntry.getBoolean("enabled")) continue;
                
                String type = configItemEntry.getString("type");
                String name = configItemEntry.getString("name");
                String material = configItemEntry.getString("mat");
                Material mat = Material.getMaterial(material);
                Boolean glowing = configItemEntry.getBoolean("glowing");
                List<String> lore = configItemEntry.getStringList("lore");
    
                if (type !=null && name != null && mat != null && glowing != null && lore != null) {
                    ItemEntry entry = new ItemEntry(type, name, mat, glowing, lore);
                    randomItems.add(entry);
                    javaPlugin.getLogger().info("Loaded randomItem: " + name + " " + mat + " " + glowing + " " + lore + " " + type);
                } else 
                    javaPlugin.getLogger().warning("Error: Poorly defined configItemEntry: " + name + " " + mat + " " + glowing + " " + lore + " " + type);
            }
        }
    }

    public String getChatPrefix() {
        return chatPrefix;
    }

    public boolean isFeatureEnabled() {
        return featureEnabled;
    }

    public List<ItemEntry> getRandomItems() {
        return randomItems;
    }
}
