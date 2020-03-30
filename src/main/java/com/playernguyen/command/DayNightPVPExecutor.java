package com.playernguyen.command;

import com.playernguyen.DayNightPVP;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DayNightPVPExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (!commandSender.hasPermission("daynightpvp.reload")) {
            commandSender.sendMessage(ChatColor.RED + "Không có quyền thực hiện lệnh");
            return true;
        }

        if (args.length < 1) {
            commandSender.sendMessage(ChatColor.DARK_GRAY + "---------------------");
            commandSender.sendMessage(ChatColor.GOLD +      "reload: reload config");
            commandSender.sendMessage(ChatColor.DARK_GRAY + "---------------------");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            DayNightPVP.getInstance().getConfigManager().load();
            commandSender.sendMessage(ChatColor.YELLOW + "Đã hoàn tất việc tải lại cấu hình.");
            return true;
        }

        return true;
    }
}
