package dev.tbm00.spigot.z64help.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;

import dev.tbm00.spigot.z64help.z64Help;

public class HelpGui {
    private z64Help javaPlugin;
    private Gui gui;
    private String label;
    private Player player;
    private int currentFilterIndex = 0;
    public final static String[] FILTER_TYPES = {"All","Server","PVP","Economy","Claims","Misc"};
    private Set<String> usedTipTitles = new HashSet<>();
    
    public HelpGui(z64Help javaPlugin, Player player, int filterIndex) {
        this.player = player;
        this.javaPlugin = javaPlugin;
        label = "MC64 Help Menu";
        gui = new Gui(6, label);
        currentFilterIndex = filterIndex;
        
        setupItems();

        gui.disableAllInteractions();
        gui.disableItemDrop();
        gui.disableItemPlace();
        gui.disableItemSwap();
        gui.disableItemTake();
        gui.disableOtherActions();

        gui.open(player);
    }

    /**
     * Sets up the footer of the GUI with categories & all other buttons.
     */
    private void setupItems() {

        //////
        /// Random Item Section
        //////
        for (int i = 1; i < 7; ++i) {
            setupRandomItem(i);
        }

        ItemStack item = new ItemStack(Material.GLASS);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        // Filter button
        /*
            int next = (currentFilterIndex==5) ? 0 : currentFilterIndex+1;
            lore.add("&8-----------------------");
            lore.add("&6Click to change tip filter");
            lore.add("&6("+ FILTER_TYPES[currentFilterIndex] + " -> " + FILTER_TYPES[next] + ")");
            meta.setLore(lore.stream().map(l -> ChatColor.translateAlternateColorCodes('&', l)).toList());
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fFilter Tips"));
            item.setItemMeta(meta);
            item.setType(Material.HOPPER);
            gui.setItem(2, 8, ItemBuilder.from(item).asGuiItem(event -> {
                event.setCancelled(true);
                new HelpGui(javaPlugin, player, next);
            }));
            lore.clear();
        */


        //////
        /// Main Help Section
        //////

        // # - Help button: Discord
        lore.add("&8-----------------------");
        lore.add("&fJoin our Discord to keep updated with our");
        lore.add("&fannoucements, get support, and engage with");
        lore.add("&fthe community!");
        lore.add("");
        lore.add("&7Link your Discord<->Minecarft by directly sending");
        lore.add("&7@Linker64#6172 your 4 digit code after running");
        lore.add("&7&o/discord link &r&7to get access to all our channels!");
        lore.add("");
        lore.add("  &7/discord &7");
        lore.add("  &7/discord link &7");
        lore.add("&8-----------------------");
        lore.add("&6Click to get our discord invite link");
        meta.setLore(lore.stream().map(l -> ChatColor.translateAlternateColorCodes('&', l)).toList());
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eServer Discord"));
        item.setItemMeta(meta);
        item.setType(Material.LIGHT_BLUE_DYE);
        gui.setItem(2, 2, ItemBuilder.from(item).asGuiItem(event -> {
            event.setCancelled(true);
            gui.close(player);
            sudoCommand(player, "discord");
        }));
        lore.clear();

        // # - Help button: Jobs Commands
        lore.add("&8-----------------------");
        lore.add("&fJoin jobs to earn money as");
        lore.add("&fyou mine, farm, kill, etc.");
        lore.add("");
        lore.add("  &7/job &7");
        lore.add("  &7/jobs join <job> &7");
        lore.add("  &7/jobs stats &7");
        lore.add("&8-----------------------");
        lore.add("&6Click to find more job commands");
        meta.setLore(lore.stream().map(l -> ChatColor.translateAlternateColorCodes('&', l)).toList());
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eJob Commands"));
        item.setItemMeta(meta);
        item.setType(Material.FISHING_ROD);
        gui.setItem(2, 3, ItemBuilder.from(item).asGuiItem(event -> {
            event.setCancelled(true);
            gui.close(player);
            sudoCommand(player, "help jobs");
        }));
        lore.clear();

        // # - Help button: Gangs Commands
        lore.add("&8-----------------------");
        lore.add("&fTeam up with your friends, form alliances,");
        lore.add("&fand fight other gangs in our arenas!");
        lore.add("");
        lore.add("  &7/gangs &7");
        lore.add("  &7/gang join <gang> &7");
        lore.add("  &7/gang invite <player> &7");
        lore.add("  &7/gang create <name> &c(Rank 8+)");
        lore.add("  &7/help fight &7");
        lore.add("&8-----------------------");
        lore.add("&6Click to find more gang commands");
        meta.setLore(lore.stream().map(l -> ChatColor.translateAlternateColorCodes('&', l)).toList());
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eGang Commands"));
        hideItemFlags(meta);
        item.setItemMeta(meta);
        item.setType(Material.CROSSBOW);
        gui.setItem(2, 4, ItemBuilder.from(item).asGuiItem(event -> {
            event.setCancelled(true);
            gui.close(player);
            sudoCommand(player, "help gangs");
        }));
        lore.clear();

        // # - Help button: Warp Commands
        lore.add("&8-----------------------");
        lore.add("&fTeleport to and create ");
        lore.add("&fpublic warps");
        lore.add("");
        lore.add("  &7/warp &7");
        lore.add("  &7/warp <name> &7");
        lore.add("  &7/warp set <name> &7");
        lore.add("&8-----------------------");
        lore.add("&6Click to find more warp commands");
        meta.setLore(lore.stream().map(l -> ChatColor.translateAlternateColorCodes('&', l)).toList());
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eWarp Commands"));
        item.setItemMeta(meta);
        item.setType(Material.COMPASS);
        gui.setItem(2, 5, ItemBuilder.from(item).asGuiItem(event -> {
            event.setCancelled(true);
            gui.close(player);
            sudoCommand(player, "help warps");
        }));
        lore.clear();

        // # - Help button: Pet Guide
        lore.add("&8-----------------------");
        lore.add("&fTo claim &acamels, cats, donkeys, horses");
        lore.add("&allamas, mules, parrots, and wolves &fas a pet,");
        lore.add("&fhit one with a lead after taming it");
        lore.add("");
        lore.add("&fTo claim &aother mob types &fas a pet,");
        lore.add("&fhit one with a lead when it has less than");
        lore.add("&f25% health. There is a 25% chance it will work");
        lore.add("");
        lore.add("  &7/pet &7");
        lore.add("  &7/petswitch &7");
        lore.add("  &7/petsendaway &7");
        lore.add("  &7/petcall &7");
        lore.add("&8-----------------------");
        lore.add("&6Click to find more pet commands");
        meta.setLore(lore.stream().map(l -> ChatColor.translateAlternateColorCodes('&', l)).toList());
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&ePet Help"));
        item.setItemMeta(meta);
        item.setType(Material.AXOLOTL_BUCKET);
        gui.setItem(2, 6, ItemBuilder.from(item).asGuiItem(event -> {
            event.setCancelled(true);
            gui.close(player);
            sudoCommand(player, "help pets");
        }));
        lore.clear();

        // # - Help button: Voting Guide
        item.setType(Material.TRIPWIRE_HOOK);
        item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
        meta = item.getItemMeta();
        lore.add("&8-----------------------");
        lore.add("&fVoting for the server daily helps grow the server,");
        lore.add("&fand you get crate keys!");
        lore.add("");
        lore.add("&eCrate32 Key&f: first 6 votes of the day");
        lore.add("&bCrate64 Key&f: 8th and 9th votes of the day");
        lore.add("&dCrateX Key&f: 7th vote of the day");
        lore.add("");
        lore.add("  &7/vote list&7");
        lore.add("  &7/vote next &7");
        lore.add("  &7/vote party &7");
        lore.add("&8-----------------------");
        lore.add("&6Click to find more voting commands");
        meta.setLore(lore.stream().map(l -> ChatColor.translateAlternateColorCodes('&', l)).toList());
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eVoting Help"));
        hideItemFlags(meta);
        item.setItemMeta(meta);
        gui.setItem(2, 7, ItemBuilder.from(item).asGuiItem(event -> {
            event.setCancelled(true);
            gui.close(player);
            sudoCommand(player, "help vote");
        }));
        lore.clear();
        item = new ItemStack(Material.GLASS);
        meta = item.getItemMeta();

        // # - Toggle Button
        lore.add("&8-----------------------");
        lore.add("&fIn our Toggle GUI, you can control VeinMiner");
        lore.add("&fAutoPickup, ChestSort, and more");
        lore.add("");
        lore.add("  &7/toggle &7");
        lore.add("&8-----------------------");
        lore.add("&6Click to open the Toggle GUI");
        meta.setLore(lore.stream().map(l -> ChatColor.translateAlternateColorCodes('&', l)).toList());
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eToggle GUI"));
        item.setItemMeta(meta);
        item.setType(Material.COMMAND_BLOCK);
        gui.setItem(2, 8, ItemBuilder.from(item).asGuiItem(event -> {
            event.setCancelled(true);
            sudoCommand(player, "commandpanel togglegui");
        }));
        lore.clear();
        
        // # - Help button: Rules
        lore.add("&8-----------------------");
        lore.add("&fThis cracked semi-anarchy server");
        lore.add("&fis welcome to everyone across the");
        lore.add("&fglobe... beware and Enjoy!");
        lore.add("");
        lore.add("  &a1) No racism. No pedophilia. No bigotry.");
        lore.add("  &a2) No dupes. No exploits. No alts. No bots.");
        lore.add("  &a3) Avoid creating lag.");
        lore.add("  &a4) Be decent.");
        lore.add("  &a5) Report genuine concerns to staff privately.");
        lore.add("&8-----------------------");
        lore.add("&6Click to get our detailed rule list");
        meta.setLore(lore.stream().map(l -> ChatColor.translateAlternateColorCodes('&', l)).toList());
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eServer Rules"));
        item.setItemMeta(meta);
        item.setType(Material.BEDROCK);
        gui.setItem(3, 2, ItemBuilder.from(item).asGuiItem(event -> {
            event.setCancelled(true);
            gui.close(player);
            sudoCommand(player, "help rules");
        }));
        lore.clear();

        // # - Help button: General Commands
        lore.add("&8-----------------------");
        lore.add("&fMain commands to use on the server");
        lore.add("");
        lore.add("  &a/menu &7");
        lore.add("  &7/tpa, /back&7");
        lore.add("  &7/warp, /home&7");
        lore.add("  &7/shop, /ah&7");
        lore.add("  &7/crates &7");
        lore.add("  &7/bank &7");
        lore.add("  &7/quests &7");
        lore.add("  &c/togglepvp &7");
        lore.add("&8-----------------------");
        lore.add("&6Click to find more general commands");
        meta.setLore(lore.stream().map(l -> ChatColor.translateAlternateColorCodes('&', l)).toList());
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eGeneral Commands"));
        item.setItemMeta(meta);
        item.setType(Material.PAPER);
        gui.setItem(3, 3, ItemBuilder.from(item).asGuiItem(event -> {
            event.setCancelled(true);
            gui.close(player);
            sudoCommand(player, "help general");
        }));
        lore.clear();

        // # - Help button: PVP Guide
        lore.add("&8-----------------------");
        lore.add("&6Use /togglepvp to control your PVP!");
        lore.add("");
        lore.add("&cPVP is force-enabled after connecting");
        lore.add("&cand dying!");
        lore.add("");
        lore.add("&cKeepINV &o(rank 15+)&r&c does not apply to PVP deaths!");
        lore.add("");
        lore.add("&cWhen you die without KeepINV, you drop 25% of");
        lore.add("&cyour pocket balance... Store money in /bank!");
        lore.add("");
        lore.add("  &7/togglepvp");
        lore.add("  &7/arena");
        lore.add("  &7/stats &7");
        lore.add("  &7/killrecaps ");
        lore.add("&8-----------------------");
        lore.add("&6Click to find more PVP commands");
        meta.setLore(lore.stream().map(l -> ChatColor.translateAlternateColorCodes('&', l)).toList());
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&ePVP Help"));
        item.setItemMeta(meta);
        item.setType(Material.NETHERITE_SWORD);
        gui.setItem(3, 4, ItemBuilder.from(item).asGuiItem(event -> {
            event.setCancelled(true);
            gui.close(player);
            sudoCommand(player, "help pvp");
        }));
        lore.clear();

        // # - Help button: Economy Commands
        lore.add("&8-----------------------");
        lore.add("&fMake money by selling items to");
        lore.add("&fplayers, getting a /job, and more");
        lore.add("");
        lore.add("  &7/bank &7");
        lore.add("  &7/shop &7");
        lore.add("  &7/ah &7");
        lore.add("  &7/ah sell <#> &7");
        lore.add("  &7/sellgui");
        lore.add("  &7/jobs join <job> &7");
        lore.add("&8-----------------------");
        lore.add("&6Click to find more economy commands");
        meta.setLore(lore.stream().map(l -> ChatColor.translateAlternateColorCodes('&', l)).toList());
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eEconomy Commands"));
        item.setItemMeta(meta);
        item.setType(Material.GOLD_INGOT);
        gui.setItem(3, 5, ItemBuilder.from(item).asGuiItem(event -> {
            event.setCancelled(true);
            gui.close(player);
            sudoCommand(player, "help economy");
        }));
        lore.clear();

        // # - Help button: Claim Guide
        lore.add("&8-----------------------");
        lore.add("&aTo make a claim:");
        lore.add("&fright-click two opposite corners");
        lore.add("&f  - with any golden shovel, OR");
        lore.add("&f  - after using /claim");
        lore.add("");
        lore.add("&aTo visualize nearby claims:");
        lore.add("&fright-click any block with a sick");
        lore.add("");
        lore.add("&cClaims automatically expire if/when the");
        lore.add("&cowner is offline for 32+ days in a row!");
        lore.add("");
        lore.add("  &7/claimlist &7");
        lore.add("  &7/claimflags &7");
        lore.add("  &7/trust <player> &7");
        lore.add("  &7/trustlist &7");
        lore.add("&8-----------------------");
        lore.add("&6Click to find more claim commands");
        meta.setLore(lore.stream().map(l -> ChatColor.translateAlternateColorCodes('&', l)).toList());
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eClaiming Help"));
        item.setItemMeta(meta);
        item.setType(Material.GOLDEN_SHOVEL);
        gui.setItem(3, 6, ItemBuilder.from(item).asGuiItem(event -> {
            event.setCancelled(true);
            gui.close(player);
            sudoCommand(player, "help claims");
        }));
        lore.clear();


        //////
        /// Control Section
        //////

        // Randomize button
        lore.add("&8-----------------------");
        lore.add("&6Click to refresh tips");
        meta.setLore(lore.stream().map(l -> ChatColor.translateAlternateColorCodes('&', l)).toList());
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fRandomize Tips"));
        item.setItemMeta(meta);
        item.setType(Material.NETHER_STAR);
        gui.setItem(5, 8, ItemBuilder.from(item).asGuiItem(event -> {
            event.setCancelled(true);
            new HelpGui(javaPlugin, player, currentFilterIndex);
        }));
        lore.clear();

        // Back button
        meta.setLore(lore.stream().map(l -> ChatColor.translateAlternateColorCodes('&', l)).toList());
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&fGo to Main Menu"));
        item.setItemMeta(meta);
        item.setType(Material.STONE_BUTTON);
        gui.setItem(6, 8, ItemBuilder.from(item).asGuiItem(event -> {
            event.setCancelled(true);
            sudoCommand(player, "commandpanel menu");
        }));
        lore.clear();


        //////
        /// Exterior Section
        //////

        gui.setItem(1, 1, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));
        gui.setItem(1, 2, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));
        gui.setItem(1, 3, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));
        gui.setItem(1, 4, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));
        gui.setItem(1, 5, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));
        gui.setItem(1, 6, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));
        gui.setItem(1, 7, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));
        gui.setItem(1, 8, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));
        gui.setItem(1, 9, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));

        gui.setItem(2, 1, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));
        gui.setItem(2, 9, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));

        gui.setItem(3, 1, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));
        gui.setItem(3, 9, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));

        gui.setItem(4, 1, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));
        gui.setItem(4, 9, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));

        gui.setItem(5, 1, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));
        gui.setItem(5, 9, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));

        gui.setItem(6, 1, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));
        gui.setItem(6, 2, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));
        gui.setItem(6, 3, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));
        gui.setItem(6, 4, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));
        gui.setItem(6, 5, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));
        gui.setItem(6, 6, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));
        gui.setItem(6, 7, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));
        gui.setItem(6, 9, ItemBuilder.from(Material.BLACK_STAINED_GLASS_PANE).setName(" ").asGuiItem(event -> event.setCancelled(true)));
    }

    private void setupRandomItem(int index) {
        ItemEntry entry = null; 

        while (entry == null) {
            entry = getRandomTipItem(javaPlugin.getConfigHandler().getRandomItems());
        } usedTipTitles.add(entry.getName().toLowerCase());

        ItemStack item = new ItemStack(entry.getMaterial());
        if (entry.getGlowing()) item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);

        List<String> lore = new ArrayList<>();
        for (String line : entry.getLore()) {
            lore.add(line);
        }

        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore.stream().map(l -> ChatColor.translateAlternateColorCodes('&', l)).toList());
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&d"+entry.getType()+" "+entry.getName()));
        hideItemFlags(meta);

        item.setItemMeta(meta);

        gui.setItem(5, index+1, ItemBuilder.from(item).asGuiItem(event -> {event.setCancelled(true);}));
    }

    private ItemEntry getRandomTipItem(List<ItemEntry> items) {
        String currentFilter = FILTER_TYPES[currentFilterIndex];
        
        List<ItemEntry> filteredItems = new ArrayList<>();
        if (currentFilter.equalsIgnoreCase("All")) {
            filteredItems.addAll(items);
        } else {
            for (ItemEntry item : items) {
                if (item.getType().equalsIgnoreCase(currentFilter) && !usedTipTitles.contains(item.getName().toLowerCase())) {
                    filteredItems.add(item);
                }
            }
        }
        
        if (filteredItems.isEmpty() && !items.isEmpty()) {
            filteredItems.addAll(items);
        } if (filteredItems.isEmpty()) return null;
        
        int randomIndex = new java.util.Random().nextInt(filteredItems.size());
        return filteredItems.get(randomIndex);
    }

    private void hideItemFlags(ItemMeta meta) {
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        meta.addItemFlags(ItemFlag.HIDE_DYE);
        meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
    }

    /**
     * Executes a command as a specific human entity.
     * 
     * @param target the player to execute the command as
     * @param command the command to execute
     * @return true if the command was successfully executed, false otherwise
     */
    public boolean sudoCommand(HumanEntity target, String command) {
        try {
            return Bukkit.dispatchCommand(target, command);
        } catch (Exception e) {
            javaPlugin.log(ChatColor.RED, "Caught exception sudoing command: " + target.getName() + " : /" + command + ": " + e.getMessage());
            return false;
        }
    }
}