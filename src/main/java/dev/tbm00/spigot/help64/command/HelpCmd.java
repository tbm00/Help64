

package dev.tbm00.spigot.help64.command;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import dev.tbm00.spigot.help64.Help64;
import dev.tbm00.spigot.help64.model.HelpGui;

public class HelpCmd implements TabExecutor {
    private final Help64 javaPlugin;

    public HelpCmd(Help64 javaPlugin) {
        this.javaPlugin = javaPlugin;
    }

    /**
     * Handles the help command.
     * 
     * @param sender the command sender
     * @param consoleCommand the command being executed
     * @param label the label used for the command
     * @param args the arguments passed to the command
     * @return true if the command was handled successfully, false otherwise
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player)
            return handleBaseCommand(sender);
        else return false;
    }

    /**
     * Handles the base command for opening help menu.
     * 
     * @param sender the command sender
     * @param args the arguments passed to the command
     * @return true if command was processed successfully, false otherwise
     */
    private boolean handleBaseCommand(CommandSender sender) {
        new HelpGui(javaPlugin, (Player) sender, 0);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}