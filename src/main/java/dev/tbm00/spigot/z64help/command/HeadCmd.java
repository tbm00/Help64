

package dev.tbm00.spigot.z64help.command;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

public class HeadCmd implements TabExecutor {

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
     * Handles the base command for running sellhead.
     * 
     * @param sender the command sender
     * @return true after command was processed
     */
    private boolean handleBaseCommand(CommandSender sender) {
        Bukkit.dispatchCommand(sender, "sellhead");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}